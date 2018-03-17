package ru.health.assistance.presentation.scan;

import javax.inject.Inject;

import ru.health.assistance.presentation.commons.BasePresenter;

/**
 * Created by sasha_merkulev on 17.03.2018.
 */

public class DefaultScanPresenter extends BasePresenter<ScanView>{

    @Inject
    public DefaultScanPresenter(){

    }

    void onQRCodeRead(String text) {
        view.openViewPagerScreen(text);
    }

    void onQRCodeUpdate(String text) {
        view.updateInfoPage(text);
    }
}
