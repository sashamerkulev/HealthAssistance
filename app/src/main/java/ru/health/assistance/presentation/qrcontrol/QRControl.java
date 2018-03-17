package ru.health.assistance.presentation.qrcontrol;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.graphics.PointF;
import android.hardware.Camera;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.PlanarYUVLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;


import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Map;

import static android.hardware.Camera.getCameraInfo;

/**
 * Created by sasha_merkulev on 02.05.2017.
 */

public class QRControl extends SurfaceView implements SurfaceHolder.Callback, Camera.PreviewCallback {

    public interface OnQRCodeReadListener {

        void onQRCodeRead(String text, PointF[] points);
    }

    private OnQRCodeReadListener mOnQRCodeReadListener;

    private static final String TAG = QRControl.class.getName();

    private QRCodeReader mQRCodeReader;
    private int mPreviewWidth;
    private int mPreviewHeight;
    private CameraManager mCameraManager;
    private boolean mQrDecodingEnabled = true;
    private DecodeFrameTask decodeFrameTask;
    private Map<DecodeHintType, Object> decodeHints;

    public QRControl(Context context) {
        this(context, null);
    }

    public QRControl(Context context, AttributeSet attrs) {
        super(context, attrs);

        if (isInEditMode()) {
            return;
        }

        if (checkCameraHardware()) {
            mCameraManager = new CameraManager(context);
            mCameraManager.setPreviewCallback(this);
            getHolder().addCallback(this);
            setBackCamera();
        } else {
            throw new RuntimeException("Error: Camera not found");
        }
    }

    /**
     * Camera preview from device back camera
     */
    public void setBackCamera() {
        setPreviewCameraId(Camera.CameraInfo.CAMERA_FACING_BACK);
    }

    /**
     * Camera preview from device front camera
     */
    public void setFrontCamera() {
        setPreviewCameraId(Camera.CameraInfo.CAMERA_FACING_FRONT);
    }

    /**
     * Allows user to specify the camera ID, rather than determine
     * it automatically based on available cameras and their orientation.
     *
     * @param cameraId camera ID of the camera to use. A negative value means "no preference".
     */
    public void setPreviewCameraId(int cameraId) {
        mCameraManager.setPreviewCameraId(cameraId);
    }

    /**
     * Set the callback to return decoding result
     *
     * @param onQRCodeReadListener the listener
     */
    public void setOnQRCodeReadListener(OnQRCodeReadListener onQRCodeReadListener) {
        mOnQRCodeReadListener = onQRCodeReadListener;
    }

    /**
     * Set QR decoding enabled/disabled.
     * default value is true
     *
     * @param qrDecodingEnabled decoding enabled/disabled.
     */
    public void setQRDecodingEnabled(boolean qrDecodingEnabled) {
        this.mQrDecodingEnabled = qrDecodingEnabled;
    }

    /**
     * Starts camera preview and decoding
     */
    public void startCamera() {
        mCameraManager.startPreview();
    }

    /**
     * Stop camera preview and decoding
     */
    public void stopCamera() {
        mCameraManager.stopPreview();
    }

    /**
     * Set Camera autofocus interval value
     * default value is 5000 ms.
     *
     * @param autofocusIntervalInMs autofocus interval value
     */
    public void setAutofocusInterval(long autofocusIntervalInMs) {
        if (mCameraManager != null) {
            mCameraManager.setAutofocusInterval(autofocusIntervalInMs);
        }
    }

    /**
     * Trigger an auto focus
     */
    public void forceAutoFocus() {
        if (mCameraManager != null) {
            mCameraManager.forceAutoFocus();
        }
    }

    /**
     * Set Torch enabled/disabled.
     * default value is false
     *
     * @param enabled torch enabled/disabled.
     */
    public void setTorchEnabled(boolean enabled) {
        if (mCameraManager != null) {
            mCameraManager.setTorchEnabled(enabled);
        }
    }

    @Override public void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        if (decodeFrameTask != null) {
            decodeFrameTask.cancel(true);
            decodeFrameTask = null;
        }
    }

    /** Check if this device has a camera */
    private boolean checkCameraHardware() {
        if (getContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            // this device has a camera
            return true;
        } else if (getContext().getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FRONT)) {
            // this device has a front camera
            return true;
        } else {
            // this device has any camera
            return getContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
        }
    }


    /**
     * Fix for the camera Sensor on some devices (ex.: Nexus 5x)
     */
    @SuppressWarnings("deprecation") private int getCameraDisplayOrientation() {
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.GINGERBREAD) {
            return 90;
        }

        Camera.CameraInfo info = new Camera.CameraInfo();
        getCameraInfo(mCameraManager.getPreviewCameraId(), info);
        WindowManager windowManager =
                (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        int rotation = windowManager.getDefaultDisplay().getRotation();
        int degrees = 0;
        switch (rotation) {
            case Surface.ROTATION_0:
                degrees = 0;
                break;
            case Surface.ROTATION_90:
                degrees = 90;
                break;
            case Surface.ROTATION_180:
                degrees = 180;
                break;
            case Surface.ROTATION_270:
                degrees = 270;
                break;
            default:
                break;
        }

        int result;
        if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            result = (info.orientation + degrees) % 360;
            result = (360 - result) % 360;  // compensate the mirror
        } else {  // back-facing
            result = (info.orientation - degrees + 360) % 360;
        }
        return result;
    }

    @Override
    public void onPreviewFrame(byte[] data, Camera camera) {
        if (!mQrDecodingEnabled || decodeFrameTask != null
                && decodeFrameTask.getStatus() == AsyncTask.Status.RUNNING) {
            return;
        }

        decodeFrameTask = new DecodeFrameTask(this, decodeHints);
        decodeFrameTask.execute(data);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.d(TAG, "surfaceCreated");

        try {
            // Indicate camera, our View dimensions
            mCameraManager.openDriver(holder, this.getWidth(), this.getHeight());
        } catch (IOException e) {
            Log.w(TAG, "Can not openDriver: " + e.getMessage());
            mCameraManager.closeDriver();
        }

        try {
            mQRCodeReader = new QRCodeReader();
            mCameraManager.startPreview();
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
            mCameraManager.closeDriver();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.d(TAG, "surfaceChanged");

        if (holder.getSurface() == null) {
            Log.e(TAG, "Error: preview surface does not exist");
            return;
        }

        if (mCameraManager.getPreviewSize() == null) {
            Log.e(TAG, "Error: preview size does not exist");
            return;
        }

        mPreviewWidth = mCameraManager.getPreviewSize().x;
        mPreviewHeight = mCameraManager.getPreviewSize().y;

        mCameraManager.stopPreview();

        // Fix the camera sensor rotation
        mCameraManager.setPreviewCallback(this);
        mCameraManager.setDisplayOrientation(getCameraDisplayOrientation());

        mCameraManager.startPreview();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.d(TAG, "surfaceDestroyed");

        mCameraManager.setPreviewCallback(null);
        mCameraManager.stopPreview();
        mCameraManager.closeDriver();
    }


    private static class DecodeFrameTask extends AsyncTask<byte[], Void, Result> {

        private final WeakReference<QRControl> viewRef;
        private final WeakReference<Map<DecodeHintType, Object>> hintsRef;
        private final QRToViewPointTransformer qrToViewPointTransformer = new QRToViewPointTransformer();

        public DecodeFrameTask(QRControl view, Map<DecodeHintType, Object> hints) {
            viewRef = new WeakReference<>(view);
            hintsRef = new WeakReference<>(hints);
        }

        @Override protected Result doInBackground(byte[]... params) {
            final QRControl view = viewRef.get();
            if (view == null) {
                return null;
            }

            final PlanarYUVLuminanceSource source =
                    view.mCameraManager.buildLuminanceSource(params[0], view.mPreviewWidth,
                            view.mPreviewHeight);

            final HybridBinarizer hybBin = new HybridBinarizer(source);
            final BinaryBitmap bitmap = new BinaryBitmap(hybBin);

            try {
                return view.mQRCodeReader.decode(bitmap, hintsRef.get());
            } catch (ChecksumException e) {
                Log.d(TAG, "ChecksumException", e);
            } catch (NotFoundException e) {
                Log.d(TAG, "No QR Code found");
            } catch (FormatException e) {
                Log.d(TAG, "FormatException", e);
            } finally {
                view.mQRCodeReader.reset();
            }

            return null;
        }

        @Override protected void onPostExecute(Result result) {
            super.onPostExecute(result);

            final QRControl view = viewRef.get();

            // Notify we found a QRCode
            if (view != null && result != null && view.mOnQRCodeReadListener != null) {
                // Transform resultPoints to View coordinates
                final PointF[] transformedPoints = transformToViewCoordinates(view, result.getResultPoints());
                view.mOnQRCodeReadListener.onQRCodeRead(result.getText(), transformedPoints);
            }
        }

        /**
         * Transform result to surfaceView coordinates
         *
         * This method is needed because coordinates are given in landscape camera coordinates when
         * device is in portrait mode and different coordinates otherwise.
         *
         * @return a new PointF array with transformed points
         */
        private PointF[] transformToViewCoordinates(QRControl view, ResultPoint[] resultPoints) {
            int orientationDegrees = view.getCameraDisplayOrientation();
            Orientation orientation = orientationDegrees == 90 || orientationDegrees == 270
                    ? Orientation.PORTRAIT
                    : Orientation.LANDSCAPE;
            Point viewSize = new Point(view.getWidth(), view.getHeight());
            Point cameraPreviewSize = view.mCameraManager.getPreviewSize();
            boolean isMirrorCamera = view.mCameraManager.getPreviewCameraId() == Camera.CameraInfo.CAMERA_FACING_FRONT;
            return qrToViewPointTransformer.transform(resultPoints, isMirrorCamera, orientation, viewSize, cameraPreviewSize);
        }
    }

    public void setZoom(int zoom) {
        Camera camera = mCameraManager.getOpenCamera().getCamera();
        Camera.Parameters parameters = camera.getParameters();
        int maxZoom = parameters.getMaxZoom();
        if (parameters.isZoomSupported()) {
            if (zoom >=0 && zoom < maxZoom) {
                parameters.setZoom(zoom);
                try {
                    mCameraManager.getConfigManager().setDesiredCameraParameters(mCameraManager.getOpenCamera(), parameters);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

