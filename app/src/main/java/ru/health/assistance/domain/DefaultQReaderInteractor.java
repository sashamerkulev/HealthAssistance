package ru.health.assistance.domain;


import javax.inject.Inject;

import io.reactivex.Scheduler;
import ru.health.assistance.data.QReaderRepository;


public class DefaultQReaderInteractor implements QReaderInteractor {

    private QReaderRepository repo;
    private final Scheduler scheduler;

    @Inject
    public DefaultQReaderInteractor(Scheduler scheduler, QReaderRepository repo) {
        this.scheduler = scheduler;
        this.repo = repo;
    }

}