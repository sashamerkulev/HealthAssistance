package ru.health.assistance.presentation.commons;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ru.health.assistance.R;

/**
 * Created by sasha_merkulev on 06.03.2018.
 */

public abstract class BaseActivity extends AppCompatActivity{

//    protected @BindView(R.id.toolbar) Toolbar toolbar;
//    protected @BindView(R.id.appbar_layout) AppBarLayout appBarLayout;

    protected Unbinder binder;

    protected abstract @LayoutRes int getContentView();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        binder = ButterKnife.bind(this);

//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeButtonEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onDestroy() {
        binder.unbind();
        super.onDestroy();
    }
}
