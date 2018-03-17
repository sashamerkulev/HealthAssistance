package ru.health.assistance.dagger.modules;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import ru.health.assistance.dagger.scopes.QReaderScope;
import ru.health.assistance.dagger.scopes.ScanScope;
import ru.health.assistance.data.DefaultQReaderRepository;
import ru.health.assistance.data.QReaderRepository;
import ru.health.assistance.domain.DefaultQReaderInteractor;
import ru.health.assistance.domain.QReaderInteractor;
import ru.health.assistance.presentation.scan.ScanFragment;

/**
 * Created by sasha_merkulev on 11.02.2018.
 */

@Module(includes = {AndroidSupportInjectionModule.class})
public abstract class QReaderModule {

    @QReaderScope
    @Binds
    abstract QReaderRepository providesRepository(DefaultQReaderRepository repository);

    @QReaderScope
    @Binds
    abstract QReaderInteractor providesInteractor(DefaultQReaderInteractor interactor);

    @ScanScope
    @ContributesAndroidInjector(modules = {ScanModule.class})
    abstract ScanFragment scanInjector();

}
