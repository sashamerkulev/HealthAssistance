package ru.health.assistance.dagger.modules;

import dagger.Binds;
import dagger.Module;
import dagger.android.support.AndroidSupportInjectionModule;
import ru.health.assistance.dagger.scopes.PinScope;
import ru.health.assistance.data.DefaultPinRepository;
import ru.health.assistance.data.PinRepository;
import ru.health.assistance.domain.DefaultPinInteractor;
import ru.health.assistance.domain.PinInteractor;

/**
 * Created by sasha_merkulev on 11.02.2018.
 */

@Module(includes = {AndroidSupportInjectionModule.class})
public abstract class PinModule {

    @PinScope
    @Binds
    abstract PinRepository providesRepository(DefaultPinRepository repository);

    @PinScope
    @Binds
    abstract PinInteractor providesInteractor(DefaultPinInteractor interactor);
}
