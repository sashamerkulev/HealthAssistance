package ru.health.assistance.data.network;

import com.google.gson.Gson;

import io.reactivex.Single;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import ru.health.assistance.data.dto.UserDTO;

/**
 * Created by sasha_merkulev on 27.03.2018.
 */

public class DefaultApiNetwork implements ApiNetwork {

    private final Api api;

    public DefaultApiNetwork(String apiUrl, Gson gson, OkHttpClient client) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(apiUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();

        api = retrofit.create(Api.class);

    }

    @Override
    public Single<UserDTO> getUser(String id) {
        return api.getUser(id);
    }

    interface Api {
        @GET("users/{id}")
        Single<UserDTO> getUser(@Path("id") String id);
    }
}