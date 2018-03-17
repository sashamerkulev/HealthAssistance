package ru.health.assistance.presentation.info;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import ru.health.assistance.domain.InfoInteractor;
import ru.health.assistance.presentation.commons.BasePresenter;

/**
 * Created by sasha_merkulev on 17.03.2018.
 */

public class DefaultInfoPresenter extends BasePresenter<InfoView>{

    private final InfoInteractor info;

    @Inject
    DefaultInfoPresenter(InfoInteractor info){
        this.info = info;
    }

    void setId(String id) {
        info.getInfo(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(infoDTO -> {
                    if (view == null) return;
                    view.showInfo(infoDTO);
                }, throwable -> {
                    if (view == null) return;
                    view.showErrorMessage(throwable.getMessage());
                });
    }
}
