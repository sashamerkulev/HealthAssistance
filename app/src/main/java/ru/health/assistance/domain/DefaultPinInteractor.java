package ru.health.assistance.domain;


import javax.inject.Inject;

import io.reactivex.Scheduler;
import ru.health.assistance.data.PinRepository;


public class DefaultPinInteractor implements PinInteractor {

    private PinRepository repo;
    private final Scheduler scheduler;

    @Inject
    public DefaultPinInteractor(Scheduler scheduler, PinRepository repo) {
        this.scheduler = scheduler;
        this.repo = repo;
    }

}