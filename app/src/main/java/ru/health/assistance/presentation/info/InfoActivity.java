package ru.health.assistance.presentation.info;

import android.content.Context;
import android.content.Intent;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import ru.health.assistance.presentation.pagerview.PagerActivity;
import ru.health.assistance.presentation.scan.ScanFragment;

/**
 * Created by sasha_merkulev on 17.03.2018.
 */

public class InfoActivity extends AppCompatActivity implements HasSupportFragmentInjector {

    private static final String KEY_BUNDLE_ID = InfoActivity.class.getSimpleName() + ".id";

    @Inject DispatchingAndroidInjector<Fragment> fragmentInjector;

    @BindView(R.id.framlayout)
    FrameLayout frameLayout;

    private Unbinder binder;
    private String id;

    public static void show(Context context, String id) {
        Intent intent = new Intent(context, InfoActivity.class);
        intent.putExtra(KEY_BUNDLE_ID, id);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qreader);
        binder = ButterKnife.bind(this);

        Intent intent = getIntent();
        id = intent.getStringExtra(KEY_BUNDLE_ID);

        if (savedInstanceState == null) {
            Fragment fragment = InfoFragment.getInstance(InfoFragment.getArgs(id));
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.framlayout, fragment)
                    .commit();
        }

    }

    @Override
    protected void onDestroy() {
        binder.unbind();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        finish();
        PagerActivity.show(this, id, 1);
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentInjector;
    }

}