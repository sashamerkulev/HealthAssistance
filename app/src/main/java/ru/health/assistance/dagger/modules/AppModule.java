package ru.health.assistance.dagger.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import ru.health.assistance.dagger.components.InfoActivityComponent;
import ru.health.assistance.dagger.components.PagerComponent;
import ru.health.assistance.dagger.components.PinComponent;
import ru.health.assistance.dagger.components.QReaderComponent;
import ru.health.assistance.dagger.scopes.InfoActivityScope;
import ru.health.assistance.dagger.scopes.PagerScope;
import ru.health.assistance.dagger.scopes.PinScope;
import ru.health.assistance.dagger.scopes.QReaderScope;
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
