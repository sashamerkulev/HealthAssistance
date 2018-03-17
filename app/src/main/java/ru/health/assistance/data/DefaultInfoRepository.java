package ru.health.assistance.data;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.inject.Inject;

import io.reactivex.Single;
import ru.health.assistance.data.dto.InfoDTO;

/**
 * Created by sasha_merkulev on 17.03.2018.
 */

public class DefaultInfoRepository implements InfoRepository {

    @Inject
    DefaultInfoRepository(){

    }

    public static InfoDTO getid123456781(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getDefault());
        calendar.set(Calendar.YEAR, 1971);
        calendar.set(Calendar.MONTH, 10);
        calendar.set(Calendar.DAY_OF_MONTH, 25);


        return new InfoDTO("id123456781", "Казтуган", "Алтынбеков",
                "Жашмидович", "Мужчина", calendar.getTime(),
                "Казахстан, г.Астана", "Гражданин Казахстана",
                "г.Москва", "119049", "ул.Донская, д.8 стр.1",
                "В связи с работой");
    }

    public static InfoDTO getid123456782(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 1978);
        calendar.set(Calendar.MONTH, 5);
        calendar.set(Calendar.DAY_OF_MONTH, 13);

        return new InfoDTO("id123456782", "Артур", "Беймбетын",
                "Казгутанович", "Мужчина", calendar.getTime(),
                "Казахстан, г.Усть-Каменогорск", "Гражданин Казахстана",
                "г.Москва", "120111", "ул.Морская, д.9",
                "В связи с работой");
    }

    @Override
    public Single<InfoDTO> getInfo(String id) {
        switch (id){
            case "id123456781":
                return Single.just(getid123456781());
            case "id123456782":
                return Single.just(getid123456782());
            default:
                return Single.just(getid123456781());
        }
    }
}
