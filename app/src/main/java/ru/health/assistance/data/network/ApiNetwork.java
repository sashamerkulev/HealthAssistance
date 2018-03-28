package ru.health.assistance.data.network;

import io.reactivex.Single;
import ru.health.assistance.data.dto.UserDTO;

/**
 * Created by sasha_merkulev on 27.03.2018.
 */

public interface ApiNetwork {

    Single<UserDTO> getUser(String id);

}
