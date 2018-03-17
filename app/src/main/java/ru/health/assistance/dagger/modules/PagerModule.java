package ru.health.assistance.dagger.modules;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import ru.health.assistance.dagger.components.HistoryComponent;
import ru.health.assistance.dagger.components.InfoComponent;
import ru.health.assistance.dagger.components.ScanComponent;
import ru.health.assistance.dagger.scopes.HistoryScope;
import ru.health.assistance.dagger.scopes.InfoScope;
import ru.health.assistance.dagger.scopes.PagerScope;
import ru.health.assistance.dagger.scopes.ScanScope;
import ru.health.assistance.data.DefaultPagerRepository;
import ru.health.assistance.data.PagerRepository;
import ru.health.assistance.domain.DefaultPagerInteractor;
import ru.health.assistance.domain.PagerInteractor;
import ru.health.assistance.presentation.history.HistoryFragment;
import ru.health.assistance.presentation.info.InfoFragment;
import ru.health.assistance.presentation.scan.ScanFragment;

/**
 * Created by sasha_merkulev on 11.02.2018.
 */

@Module(includes = {AndroidSupportInjectionModule.class}, subcomponents = {
        ScanComponent.class, InfoComponent.class, HistoryComponent.class
})
public abstract class PagerModule {

    @PagerScope
    @Binds
    abstract PagerRepository providesRepository(DefaultPagerRepository repository);

    @PagerScope
    @Binds
    abstract PagerInteractor providesInteractor(DefaultPagerInteractor interactor);

    @ScanScope
    @ContributesAndroidInjector(modules = {ScanModule.class})
    abstract ScanFragment scanInjector();

    @InfoScope
    @ContributesAndroidInjector(modules = {InfoModule.class})
    abstract InfoFragment infoInjector();

    @HistoryScope
    @ContributesAndroidInjector(modules = {HistoryModule.class})
    abstract HistoryFragment historyInjector();

}
