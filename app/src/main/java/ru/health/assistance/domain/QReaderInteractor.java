package ru.health.assistance.domain;


public interface QReaderInteractor {

    interface QReaderCallback {
        void success();

        void failure(Exception e);
    }

}