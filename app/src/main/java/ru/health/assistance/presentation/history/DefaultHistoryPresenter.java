package ru.health.assistance.presentation.history;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import ru.health.assistance.data.dto.InfoDTO;
import ru.health.assistance.domain.HistoryInteractor;
import ru.health.assistance.presentation.commons.BasePresenter;

/**
 * Created by sasha_merkulev on 17.03.2018.
 */

public class DefaultHistoryPresenter extends BasePresenter<HistoryView>{

    private final HistoryInteractor hist;

    @Inject
    DefaultHistoryPresenter(HistoryInteractor hist){
        this.hist = hist;
    }

    void onItemClicked(InfoDTO item) {
        view.showInfo(item);
    }

    void onLoad() {
        hist.getHistories()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(infoDTO -> {
                    if (view == null) return;
                    view.showHistories(infoDTO);
                }, throwable -> {
                    if (view == null) return;
                    view.showErrorMessage(throwable.getMessage());
                });

    }
}
