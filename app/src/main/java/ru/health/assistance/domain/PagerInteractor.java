package ru.health.assistance.domain;


public interface PagerInteractor {

    interface PagerCallback {
        void success();

        void failure(Exception e);
    }

}