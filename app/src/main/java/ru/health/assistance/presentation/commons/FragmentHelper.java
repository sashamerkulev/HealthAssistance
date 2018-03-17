package ru.health.assistance.presentation.commons;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import ru.health.assistance.R;


/**
 * Created by sasha_merkulev on 11.02.2018.
 */

public class FragmentHelper {

    final static String ACCOMODATION_TAG = "accomodation";
    final static String LIBRARY_TAG = "library";
    final static String MUSIC_TAG = "music";
    public final static String NOTICE_TAG = "notice";
    final static String PROGRAM_TAG = "program";
    final static String SHOP_TAG = "shop";

    private final FragmentManager fragmentManager;
    private String currentFragment;

    public FragmentHelper(FragmentManager fragmentManager, String currentFragment){
        this.fragmentManager = fragmentManager;
        this.currentFragment = currentFragment;
    }

    public String getCurrentFragmentTag() {
        return currentFragment;
    }

    public Fragment getCurrentFragment() {
        return getFragmentByTag(currentFragment);
    }

    public Fragment getFragmentByTag(String tag) {
        return getFragmentByTag(tag, null);
    }

    public String getTagFragmentByMenuItemId(int currentIdMenuItem) {
        String tag = "";
//        switch (currentIdMenuItem) {
//            case R.id.nav_accomodation:
//                return ACCOMODATION_TAG;
//        }
        return tag;
    }

    public void activateFragmentByTag() {
        activateFragmentByTag(currentFragment);
    }

    public void activateFragmentByTag(String tag) {
        activateFragmentByTag(tag, null);
    }

    public void activateFragmentByTag(String tag, Bundle args) {
        Fragment fragment = getFragmentByTag(tag, args);
        if (fragment != null && !fragment.isVisible()) {
            currentFragment = tag;
            replace(fragment, tag);
        }
    }

//    public int getBackStackEntryCount(){
//        return fragmentManager.getBackStackEntryCount();
//    }
//
//    public void popBackStack() {
//        fragmentManager.popBackStack();
//    }

    private Fragment getFragmentByTag(String tag, Bundle args){
        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        if (fragment == null){
//            switch (tag) {
//                case ACCOMODATION_TAG:
//                    fragment = AccommodationFragment.getInstance();
//                    break;
//            }
        } else {
            fragment.setArguments(args);
        }
        return fragment;
    }

    private void replace(Fragment fragment, String tag){
//        fragmentManager
//                .beginTransaction()
//                .replace(R.id.frame_container, fragment, tag)
////                .addToBackStack(tag)
//                .commit();
    }
}
