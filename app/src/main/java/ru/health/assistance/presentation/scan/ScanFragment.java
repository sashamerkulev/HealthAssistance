package ru.health.assistance.presentation.scan;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;

import java.util.Date;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.AndroidSupportInjection;
import ru.health.assistance.App;
import ru.health.assistance.R;
import ru.health.assistance.presentation.pagerview.PagerActivity;
import ru.health.assistance.presentation.qrcontrol.QRControl;

/**
 * Created by sasha_merkulev on 17.03.2018.
 */

public class ScanFragment extends Fragment implements ScanView{

    private static final String KEY_BUNDLE_SHOW = ScanFragment.class.getSimpleName() + ".show";

    @Inject DefaultScanPresenter pres;

    @BindView(R.id.qrdecoderview) QRControl qrControl;
    @BindView(R.id.imageview_flash) ImageView flash;
    @BindView(R.id.seekbar) SeekBar seekBar;

    private View root;
    private Unbinder binder;

    private @DrawableRes int flashRes;

    private String text;
    private Date date;

    public static Fragment getInstance(Bundle args){
        ScanFragment fragment = new ScanFragment();
        fragment.setArguments(args==null? new Bundle(): args);
        return fragment;
    }

    public static Bundle getShowArgs(){
        Bundle args = new Bundle();
        args.putBoolean(ScanFragment.KEY_BUNDLE_SHOW, true);
        return args;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        AndroidSupportInjection.inject(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_scan, container, false);
        binder = ButterKnife.bind(this, root);
        pres.bindView(this);

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("Разрешения на камеру");
            builder.setCancelable(false);
            builder.setMessage("Приложению требуются разрешения на работу с камерой!");
            builder.setPositiveButton("ОК", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    getActivity().finish();
                }
            });
            builder.create().show();
        } else {

            final Bundle args = getArguments();
            final boolean show = args.getBoolean(KEY_BUNDLE_SHOW, false);

            qrControl.setOnQRCodeReadListener((text, points) -> {
                if (show) pres.onQRCodeRead(text);
                else pres.onQRCodeUpdate(text);

                App.ids.add(text);

            });
            qrControl.setQRDecodingEnabled(true);
            qrControl.setAutofocusInterval(2000L);
            qrControl.setBackCamera();
            qrControl.setVisibility(View.VISIBLE);
        }

        flashRes = R.drawable.ic_flash;
        flash.setOnClickListener(v -> {
            flashRes = flashRes == R.drawable.ic_flash
                    ? R.drawable.ic_flash_off
                    : R.drawable.ic_flash;
            flash.setImageDrawable(ContextCompat.getDrawable(getContext(), flashRes));
            qrControl.setTorchEnabled(flashRes == R.drawable.ic_flash);
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                qrControl.setZoom(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        qrControl.startCamera();
        text = null;
    }

    @Override
    public void onPause() {
        super.onPause();
        qrControl.stopCamera();
    }

    @Override
    public void onDestroy() {
        binder.unbind();
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        pres.unbindView();
    }

    @Override
    public void openViewPagerScreen(String text) {
        if (this.text == null || !this.text.equals(text)){
            this.text = text;
            this.date = new Date();
            PagerActivity.show(getContext(), text);
        }
    }

    @Override
    public void updateInfoPage(String text) {

    }

}
