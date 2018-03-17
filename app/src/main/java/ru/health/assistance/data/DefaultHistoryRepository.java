package ru.health.assistance.data;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import ru.health.assistance.App;
import ru.health.assistance.data.dto.InfoDTO;

/**
 * Created by sasha_merkulev on 17.03.2018.
 */

public class DefaultHistoryRepository implements HistoryRepository {
    @Inject
    DefaultHistoryRepository(){

    }

    private boolean exists(String id){
        boolean result = false;
        for(String s : App.ids){
            if (s.equals(id)) {
                result = true;
                break;
            }
        }
        return result;
    }


    @Override
    public Single<List<InfoDTO>> getHistories() {
        List<InfoDTO> result = new ArrayList<>();
        if (exists("id123456781")) result.add(DefaultInfoRepository.getid123456781());
        if (exists("id123456782")) result.add(DefaultInfoRepository.getid123456782());
        return Single.just(result);
    }
}
