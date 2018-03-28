package ru.health.assistance.data;

import javax.inject.Inject;

import io.reactivex.Single;
import ru.health.assistance.data.database.DbCache;
import ru.health.assistance.data.dto.UserDTO;
import ru.health.assistance.data.network.ApiNetwork;

/**
 * Created by sasha_merkulev on 17.03.2018.
 */

public class DefaultInfoRepository implements InfoRepository {

    private final ApiNetwork api;
    private final DbCache db;

    @Inject
    DefaultInfoRepository(ApiNetwork api, DbCache db){
        this.api = api;
        this.db = db;
    }

    @Override
    public Single<UserDTO> getInfo(String id) {
        return api.getUser(id)
                .doOnSuccess(db::saveUser);
    }
}
