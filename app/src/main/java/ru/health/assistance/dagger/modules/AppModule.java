package ru.health.assistance.dagger.modules;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import ru.health.assistance.BuildConfig;
import ru.health.assistance.dagger.components.InfoActivityComponent;
import ru.health.assistance.dagger.components.PagerComponent;
import ru.health.assistance.dagger.components.PinComponent;
import ru.health.assistance.dagger.components.QReaderComponent;
import ru.health.assistance.dagger.scopes.InfoActivityScope;
import ru.health.assistance.dagger.scopes.PagerScope;
import ru.health.assistance.dagger.scopes.PinScope;
import ru.health.assistance.dagger.scopes.QReaderScope;
import ru.health.assistance.data.database.DbCache;
import ru.health.assistance.data.database.DbRoom;
import ru.health.assistance.data.database.DefaultDbCache;
import ru.health.assistance.data.network.ApiNetwork;
import ru.health.assistance.data.network.DateTimeTypeAdapter;
import ru.health.assistance.data.network.DefaultApiNetwork;
import ru.health.assistance.presentation.info.InfoActivity;
import ru.health.assistance.presentation.pagerview.PagerActivity;
import ru.health.assistance.presentation.pinview.PinActivity;
import ru.health.assistance.presentation.qreader.QReaderActivity;

/**
 * Created by sasha_merkulev on 11.02.2018.
 */

@Module(includes = {AndroidSupportInjectionModule.class},
        subcomponents = {PinComponent.class, PagerComponent.class,
                QReaderComponent.class, InfoActivityComponent.class})
public abstract class AppModule {

    @Singleton
    @Provides
    static Scheduler providesScheduler() {
        return Schedulers.io();
    }

    @Singleton
    @Provides
    static Gson providesGson() {
        return new GsonBuilder()
                .registerTypeAdapter(Date.class, new DateTimeTypeAdapter())
                .create();
    }

    @Singleton
    @Provides
    static OkHttpClient providesOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG_MODE){
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            builder.addInterceptor(loggingInterceptor);
        }
        return builder.build();
    }

    @Singleton
    @Provides
    static ApiNetwork providesApiNetwork(Gson gson, OkHttpClient client) {
        return new DefaultApiNetwork(BuildConfig.API_URL, gson, client);
    }

    @Singleton
    @Provides
    static DbRoom providesDbRoom(Context context){
        return Room.databaseBuilder(context, DbRoom.class, BuildConfig.DB_NAME).build();
    }

    @Singleton
    @Provides
    static DbCache providesDbCache(DbRoom room){
        return new DefaultDbCache(room);
    }

    @PinScope
    @ContributesAndroidInjector(modules = PinModule.class)
    abstract PinActivity oinInjector();

    @PagerScope
    @ContributesAndroidInjector(modules = PagerModule.class)
    abstract PagerActivity pagerInjector();

    @QReaderScope
    @ContributesAndroidInjector(modules = QReaderModule.class)
    abstract QReaderActivity qrActivityInjector();

    @InfoActivityScope
    @ContributesAndroidInjector(modules = InfoActivityModule.class)
    abstract InfoActivity infoInjector();
}
