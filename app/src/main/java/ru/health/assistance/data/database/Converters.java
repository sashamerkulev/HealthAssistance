package ru.health.assistance.data.database;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Created by sasha_merkulev on 27.03.2018.
 */

public class Converters {

    @TypeConverter
    public Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public Long dateToTimestamp(Date date) {
        if (date == null) {
            return null;
        } else {
            return date.getTime();
        }
    }

}
