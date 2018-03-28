package ru.health.assistance.presentation.history;

import java.util.List;

import ru.health.assistance.domain.dto.User;

/**
 * Created by sasha_merkulev on 17.03.2018.
 */

interface HistoryView {
    void showHistories(List<User> infoDTO);
    void showErrorMessage(String message);

    void showInfo(User item);
}
