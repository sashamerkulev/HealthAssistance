package ru.health.assistance.domain;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import ru.health.assistance.data.HistoryRepository;
import ru.health.assistance.domain.dto.User;

/**
 * Created by sasha_merkulev on 17.03.2018.
 */

public class DefaultHistoryInteractor implements HistoryInteractor {

    private final HistoryRepository repo;
    private final Scheduler scheduler;

    @Inject
    DefaultHistoryInteractor(Scheduler scheduler, HistoryRepository repo){
        this.scheduler = scheduler;
        this.repo = repo;
    }

    @Override
    public Single<List<User>> getHistories() {
        return repo.getHistories()
                .subscribeOn(scheduler);
    }
}
