package ru.health.assistance.domain;

import io.reactivex.Single;
import ru.health.assistance.data.dto.InfoDTO;

/**
 * Created by sasha_merkulev on 17.03.2018.
 */

public interface InfoInteractor {
    Single<InfoDTO> getInfo(String id);
}
