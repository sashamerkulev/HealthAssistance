package ru.health.assistance.dagger.modules;

import dagger.Binds;
import dagger.Module;
import dagger.android.support.AndroidSupportInjectionModule;
import ru.health.assistance.dagger.scopes.HistoryScope;
import ru.health.assistance.data.DefaultHistoryRepository;
import ru.health.assistance.data.HistoryRepository;
import ru.health.assistance.domain.DefaultHistoryInteractor;
import ru.health.assistance.domain.HistoryInteractor;

/**
 * Created by sasha_merkulev on 11.02.2018.
 */

@Module(includes = {AndroidSupportInjectionModule.class})
public abstract class HistoryModule {

    @HistoryScope
    @Binds
    abstract HistoryRepository providesRepository(DefaultHistoryRepository repository);

    @HistoryScope
    @Binds
    abstract HistoryInteractor providesInteractor(DefaultHistoryInteractor interactor);
}
