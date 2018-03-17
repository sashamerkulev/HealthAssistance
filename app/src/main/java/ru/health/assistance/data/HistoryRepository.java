package ru.health.assistance.data;

import java.util.List;

import io.reactivex.Single;
import ru.health.assistance.data.dto.InfoDTO;

/**
 * Created by sasha_merkulev on 17.03.2018.
 */

public interface HistoryRepository {
    Single<List<InfoDTO>> getHistories();
}
