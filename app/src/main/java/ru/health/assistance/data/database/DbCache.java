package ru.health.assistance.data.database;

import java.util.List;

import io.reactivex.Single;
import ru.health.assistance.data.database.entities.UserEntity;
import ru.health.assistance.data.dto.UserDTO;

/**
 * Created by sasha_merkulev on 27.03.2018.
 */

public interface DbCache {

    Single<List<UserEntity>> getUsers();
    void saveUser(UserDTO userDTO);
}
