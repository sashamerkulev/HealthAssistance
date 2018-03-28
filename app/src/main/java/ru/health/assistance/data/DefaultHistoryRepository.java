package ru.health.assistance.data;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import ru.health.assistance.data.database.DbCache;
import ru.health.assistance.domain.dto.User;

/**
 * Created by sasha_merkulev on 17.03.2018.
 */

public class DefaultHistoryRepository implements HistoryRepository {

    private final DbCache db;

    @Inject
    DefaultHistoryRepository(DbCache db){
        this.db = db;
    }

    @Override
    public Single<List<User>> getHistories() {
        return db.getUsers()
                .flattenAsFlowable(userEntities -> userEntities)
                .map(userEntity -> new User(userEntity.getId(),
                        userEntity.getMicardSerial(), userEntity.getMicardNumber(),
                        userEntity.getSurname(), userEntity.getName(), userEntity.getPatronymic(),
                        userEntity.getBirthDate(), userEntity.getSex(), userEntity.getDocument(),
                        userEntity.getVisaNumber(), userEntity.getPurpose(), userEntity.getHost(),
                        userEntity.getPhoto(), userEntity.getFrom(), userEntity.getTo()))
                .toList();
    }
}
