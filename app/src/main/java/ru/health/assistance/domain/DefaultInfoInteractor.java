package ru.health.assistance.domain;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import ru.health.assistance.data.InfoRepository;
import ru.health.assistance.data.dto.InfoDTO;

/**
 * Created by sasha_merkulev on 17.03.2018.
 */

public class DefaultInfoInteractor implements InfoInteractor {

    private final InfoRepository repo;
    private final Scheduler scheduler;

    @Inject
    DefaultInfoInteractor(Scheduler scheduler, InfoRepository repo){
        this.scheduler = scheduler;
        this.repo = repo;
    }

    @Override
    public Single<InfoDTO> getInfo(String id) {
        return repo.getInfo(id)
                .subscribeOn(scheduler);
    }
}
