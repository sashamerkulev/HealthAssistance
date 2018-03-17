package ru.health.assistance.presentation.pinview;

import javax.inject.Inject;

import ru.health.assistance.domain.PinInteractor;
import ru.health.assistance.presentation.commons.BasePresenter;


class DefaultPinPresenter extends BasePresenter<PinView> {

    private final PinInteractor inter;

    private StringBuilder pinCode;

    @Inject
    DefaultPinPresenter(PinInteractor inter) {
        this.inter = inter;
    }

    void onStart() {
        pinCode = new StringBuilder();
        view.showPinCode(pinCode.length());
    }

    void onFingerprintAuth() {
        view.openQRScreen();
    }

    void onNumberPressed(String s) {
        pinCode.append(s);
        view.showPinCode(pinCode.length());
        if (pinCode.length() == 6){
            view.openQRScreen();
        }
    }

    void onBackPressed() {
        if (pinCode.length() > 0) {
            pinCode.delete(pinCode.length()-1, pinCode.length());
            view.showPinCode(pinCode.length());
        }
    }
}