package ru.health.assistance.presentation.history;

import java.util.List;

import ru.health.assistance.data.dto.InfoDTO;

/**
 * Created by sasha_merkulev on 17.03.2018.
 */

interface HistoryView {
    void showHistories(List<InfoDTO> infoDTO);
    void showErrorMessage(String message);

    void showInfo(InfoDTO item);
}
