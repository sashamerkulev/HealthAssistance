package ru.health.assistance.presentation.qreader;


import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import ru.health.assistance.R;
import ru.health.assistance.presentation.scan.ScanFragment;


public class QReaderActivity extends AppCompatActivity implements HasSupportFragmentInjector{

    @Inject DispatchingAndroidInjector<Fragment> fragmentInjector;

    @BindView(R.id.framlayout) FrameLayout frameLayout;

    private Unbinder binder;

    public static void show(Context context){
        Intent intent = new Intent(context, QReaderActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qreader);
        binder = ButterKnife.bind(this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Разрешения на камеру");
            builder.setCancelable(false);
            builder.setMessage("Приложению требуются разрешения на работу с камерой!");
            builder.setPositiveButton("ОК", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    finish();
                }
            });
            builder.create().show();
        } else {
            if (savedInstanceState == null) {
                Fragment fragment = ScanFragment.getInstance(ScanFragment.getShowArgs());
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.framlayout, fragment)
                        .commit();
            }
        }
    }

    @Override
    protected void onDestroy() {
        binder.unbind();
        super.onDestroy();
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentInjector;
    }
}
