package ru.health.assistance.presentation.pinview;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.ActivityCompat;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;
import android.support.v4.os.CancellationSignal;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import ru.health.assistance.R;
import ru.health.assistance.presentation.commons.ItemClickListener;
import ru.health.assistance.presentation.controls.NumberPinsView;
import ru.health.assistance.presentation.controls.PinsView;
import ru.health.assistance.presentation.qreader.QReaderActivity;
import ru.health.assistance.presentation.utils.CryptoUtils;
import ru.health.assistance.presentation.utils.FingerprintUtils;

public class PinActivity extends AppCompatActivity implements PinView{

    public static int PERMISSIONS_REQUEST = 2;

    @Inject DefaultPinPresenter pres;

    @BindView(R.id.pins) PinsView pins;
    @BindView(R.id.number_pins) NumberPinsView numberPins;

    @BindView(R.id.layout_call) View layoutCall;
    @BindView(R.id.imageview_delete) ImageView back;

    private boolean starting;
    private boolean requestPermission;
    private FingerprintHelper fingerprintHelper;

    public static void show(Context context){
        Intent intent = new Intent(context, PinActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin);
        ButterKnife.bind(this);
        pres.bindView(this);

        numberPins.setOnNumberClickListener(number -> pres.onNumberPressed(number));

        back.setOnClickListener(v -> pres.onBackPressed());

        layoutCall.setOnClickListener(v -> {
            View bottomSheetView = getLayoutInflater().inflate(R.layout.dialog_calls, null);
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(PinActivity.this);
            bottomSheetDialog.setContentView(bottomSheetView);
            BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from((View) bottomSheetView.getParent());
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            bottomSheetDialog.show();
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        pres.onStart();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermission = true;
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PERMISSIONS_REQUEST);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (fingerprintHelper != null) {
            fingerprintHelper.cancel();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        prepareSensor();
        starting = false;
    }

    @Override
    protected void onDestroy() {
        pres.unbindView();
        super.onDestroy();
    }

    private void prepareSensor() {
        if (FingerprintUtils.isSensorStateAt(FingerprintUtils.mSensorState.READY, this)) {
            FingerprintManagerCompat.CryptoObject cryptoObject = CryptoUtils.getCryptoObject();
            if (cryptoObject != null) {
                fingerprintHelper = new FingerprintHelper(this);
                fingerprintHelper.startAuth(cryptoObject);
            }
        }
    }

    @Override
    public void openQRScreen() {
        if (fingerprintHelper != null) {
            fingerprintHelper.cancel();
        }
        starting = true;
        QReaderActivity.show(this);
    }

    @Override
    public void showPinCode(int length) {
        pins.select(length);
    }

    private class FingerprintHelper extends FingerprintManagerCompat.AuthenticationCallback {
        private Context mContext;
        private CancellationSignal mCancellationSignal;

        FingerprintHelper(Context context) {
            mContext = context;
        }

        void startAuth(FingerprintManagerCompat.CryptoObject cryptoObject) {
            mCancellationSignal = new CancellationSignal();
            FingerprintManagerCompat manager = FingerprintManagerCompat.from(mContext);
            manager.authenticate(cryptoObject, 0, mCancellationSignal, this, null);
        }

        void cancel() {
            if (mCancellationSignal != null) {
                mCancellationSignal.cancel();
            }
        }

        private void starting(){
            if (!starting) {
                starting = true;
                pres.onFingerprintAuth();
            }
        }

        @Override
        public void onAuthenticationError(int errMsgId, CharSequence errString) {
            if (requestPermission){
                requestPermission = false;
            } else {
                starting();
            }
        }

        @Override
        public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
            starting();
        }

        @Override
        public void onAuthenticationSucceeded(FingerprintManagerCompat.AuthenticationResult result) {



            starting();
        }

        @Override
        public void onAuthenticationFailed() {
            starting();
        }

    }
}
