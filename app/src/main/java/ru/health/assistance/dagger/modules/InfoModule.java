package ru.health.assistance.dagger.modules;

import dagger.Binds;
import dagger.Module;
import dagger.android.support.AndroidSupportInjectionModule;
import ru.health.assistance.dagger.scopes.InfoScope;
import ru.health.assistance.data.DefaultInfoRepository;
import ru.health.assistance.data.InfoRepository;
import ru.health.assistance.domain.DefaultInfoInteractor;
import ru.health.assistance.domain.InfoInteractor;

/**
 * Created by sasha_merkulev on 11.02.2018.
 */

@Module(includes = {AndroidSupportInjectionModule.class})
public abstract class InfoModule {

    @InfoScope
    @Binds
    abstract InfoRepository providesRepository(DefaultInfoRepository repository);

    @InfoScope
    @Binds
    abstract InfoInteractor providesInteractor(DefaultInfoInteractor interactor);
}
