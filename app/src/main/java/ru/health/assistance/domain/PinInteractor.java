package ru.health.assistance.domain;


public interface PinInteractor {

    interface MainCallback {
        void success();

        void failure(Exception e);
    }

}