package ru.health.assistance.presentation.info;

import ru.health.assistance.data.dto.InfoDTO;

/**
 * Created by sasha_merkulev on 17.03.2018.
 */

interface InfoView {
    void showInfo(InfoDTO infoDTO);
    void showErrorMessage(String message);
}
