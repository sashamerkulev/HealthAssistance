package ru.health.assistance.data.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Single;
import ru.health.assistance.data.database.entities.UserEntity;

/**
 * Created by sasha_merkulev on 27.03.2018.
 */

@Dao
public interface UserDao {

    @Insert
    void saveClients(UserEntity[] users);

    @Insert
    void addClient(UserEntity user);

    @Query("select * from users")
    Single<List<UserEntity>> getUsers();

}
