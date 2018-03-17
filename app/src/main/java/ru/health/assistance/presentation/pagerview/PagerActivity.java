package ru.health.assistance.presentation.pagerview;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import ru.health.assistance.R;
import ru.health.assistance.data.dto.InfoDTO;
import ru.health.assistance.presentation.history.HistoryFragment;
import ru.health.assistance.presentation.info.InfoActivity;
import ru.health.assistance.presentation.info.InfoFragment;


public class PagerActivity extends AppCompatActivity implements
        PagerView, HistoryFragment.OnShowInfoCallback, HasSupportFragmentInjector{

    private static final String KEY_BUNDLE_TEXT = PagerActivity.class.getSimpleName()+".text";
    private static final String KEY_BUNDLE_PAGE = PagerActivity.class.getSimpleName()+".page";

    @Inject DispatchingAndroidInjector<Fragment> fragmentInjector;
    @Inject DefaultPagerPresenter pres;

    @BindView(R.id.tablayout) TabLayout tabLayout;
    @BindView(R.id.pager) ViewPager pager;

    private String id;
    private PagerAdapter adapter;

    public static void show(Context context, String text){
        Intent intent = new Intent(context, PagerActivity.class);
        intent.putExtra(KEY_BUNDLE_TEXT, text);
        context.startActivity(intent);
    }

    public static void show(Context context, String text, int page){
        Intent intent = new Intent(context, PagerActivity.class);
        intent.putExtra(KEY_BUNDLE_TEXT, text);
        intent.putExtra(KEY_BUNDLE_PAGE, page);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);
        ButterKnife.bind(this);
        pres.bindView(this);

        Intent intent = getIntent();
        id = intent.getStringExtra(KEY_BUNDLE_TEXT);
        int page = intent.getIntExtra(KEY_BUNDLE_PAGE, 0);

        adapter = new PagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        pager.setCurrentItem(page);
        tabLayout.setupWithViewPager(pager);
    }

    @Override
    protected void onDestroy() {
        pres.unbindView();
        super.onDestroy();
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentInjector;
    }

    @Override
    public void showInfoCallback(InfoDTO item) {
        finish();
        InfoActivity.show(this, item.getId());
    }

    public class PagerAdapter extends FragmentPagerAdapter {

        private int NUM_PAGES = 2;

        PagerAdapter(FragmentManager fragmentManager){
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            switch(position) {
                case 0:
                    return InfoFragment.getInstance(InfoFragment.getArgs(id));
                case 1:
                    return HistoryFragment.getInstance(null);
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (position == 0)
                return getBaseContext().getResources().getString(R.string.info_fragment_title);
            return getBaseContext().getResources().getString(R.string.history_fragment_title);
        }
    }
}
