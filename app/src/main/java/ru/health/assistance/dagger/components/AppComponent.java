package ru.health.assistance.dagger.components;

import android.content.Context;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import ru.health.assistance.App;
import ru.health.assistance.dagger.modules.AppModule;

/**
 * Created by sasha_merkulev on 11.02.2018.
 */

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder context(Context context);
        AppComponent build();
    }

    void inject(App app);
}
