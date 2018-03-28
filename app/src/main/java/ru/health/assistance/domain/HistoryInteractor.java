package ru.health.assistance.domain;

import java.util.List;

import io.reactivex.Single;
import ru.health.assistance.domain.dto.User;

/**
 * Created by sasha_merkulev on 17.03.2018.
 */

public interface HistoryInteractor {
    Single<List<User>> getHistories();
}
