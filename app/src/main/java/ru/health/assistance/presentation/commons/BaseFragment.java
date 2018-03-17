package ru.health.assistance.presentation.commons;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.Unbinder;
import dagger.android.support.AndroidSupportInjection;
import ru.health.assistance.R;

/**
 * Created by sasha_merkulev on 11.02.2018.
 */

public abstract class BaseFragment<T extends BaseView, P extends BasePresenter<T>> extends Fragment implements BaseView{

    protected @Inject P pres;
    protected @Inject FragmentRouter router;

//    protected @BindView(R.id.toolbar) Toolbar toolbar;
//    protected @BindView(R.id.appbar_layout) AppBarLayout appBarLayout;

    protected Unbinder binder;
    protected DrawerToolbarCombinator combinator;
    protected View root;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        AndroidSupportInjection.inject(this);
        if (context instanceof DrawerToolbarCombinator) {
            combinator = (DrawerToolbarCombinator) context;
        }
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        pres.bindView((T)this);
//    }
//
//    @Override
//    public void onStop() {
//        pres.unbindView();
//        super.onStop();
//    }

    @Override
    public void onDestroy() {
        pres.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        pres.unbindView();
        binder.unbind();
        super.onDestroyView();
    }

    @Override
    public void showErrorMessage(String message) {
        Snackbar.make(root, message, Snackbar.LENGTH_LONG).show();
    }

}
