package ru.health.assistance.data.database;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import ru.health.assistance.data.database.entities.UserEntity;
import ru.health.assistance.data.dto.UserDTO;

/**
 * Created by sasha_merkulev on 27.03.2018.
 */

public class DefaultDbCache implements DbCache {

    private final DbRoom room;

    @Inject
    public DefaultDbCache(DbRoom room){
        this.room = room;
    }


    @Override
    public Single<List<UserEntity>> getUsers() {
        return room.getUserDao().getUsers();
    }

    @Override
    public void saveUser(UserDTO userDTO) {
        room.getUserDao()
                .addClient(new UserEntity(userDTO.getId(), userDTO.getMicardSerial(), userDTO.getMicardNumber(),
                        userDTO.getSurname(), userDTO.getName(), userDTO.getPatronymic(), userDTO.getBirthDate(),
                        userDTO.getSex(), userDTO.getDocument(), userDTO.getVisaNumber(), userDTO.getPurpose(),
                        userDTO.getHost(), userDTO.getPhoto(), userDTO.getFrom(), userDTO.getTo()));
    }
}
