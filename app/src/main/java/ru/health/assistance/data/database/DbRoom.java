package ru.health.assistance.data.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import ru.health.assistance.data.database.dao.UserDao;
import ru.health.assistance.data.database.entities.UserEntity;

/**
 * Created by sasha_merkulev on 27.03.2018.
 */

@Database(entities = {UserEntity.class}
        , version = 1, exportSchema = false)
@TypeConverters(Converters.class)
public abstract class DbRoom extends RoomDatabase {

    public abstract UserDao getUserDao();
}
