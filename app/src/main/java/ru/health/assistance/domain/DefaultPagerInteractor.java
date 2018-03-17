package ru.health.assistance.domain;


import javax.inject.Inject;

import io.reactivex.Scheduler;
import ru.health.assistance.data.PagerRepository;


public class DefaultPagerInteractor implements PagerInteractor {

    private PagerRepository repo;
    private final Scheduler scheduler;

    @Inject
    public DefaultPagerInteractor(Scheduler scheduler, PagerRepository repo) {
        this.scheduler = scheduler;
        this.repo = repo;
    }

}