package ru.health.assistance.presentation.pagerview;

import javax.inject.Inject;

import ru.health.assistance.domain.PagerInteractor;
import ru.health.assistance.presentation.commons.BasePresenter;


class DefaultPagerPresenter extends BasePresenter<PagerView>{

    private final PagerInteractor inter;

    @Inject
    DefaultPagerPresenter(PagerInteractor inter) {
        this.inter = inter;
    }

}