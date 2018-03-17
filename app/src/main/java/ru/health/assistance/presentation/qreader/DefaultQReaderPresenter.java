package ru.health.assistance.presentation.qreader;


import javax.inject.Inject;

import ru.health.assistance.domain.QReaderInteractor;
import ru.health.assistance.presentation.commons.BasePresenter;


class DefaultQReaderPresenter extends BasePresenter<QReaderView> {

    private final QReaderInteractor inter;

    @Inject
    DefaultQReaderPresenter(QReaderInteractor inter) {
        this.inter = inter;
    }

    void onQRCodeRead(String text) {
        view.openViewPagerScreen(text);
    }
}