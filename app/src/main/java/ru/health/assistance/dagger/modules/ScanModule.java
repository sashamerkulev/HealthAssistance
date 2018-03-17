package ru.health.assistance.dagger.modules;

import dagger.Module;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by sasha_merkulev on 11.02.2018.
 */

@Module(includes = {AndroidSupportInjectionModule.class})
public abstract class ScanModule {

//    @ScanScope
//    @Binds
//    abstract ScanRepository providesRepository(DefaultScanRepository repository);
//
//    @ScanScope
//    @Binds
//    abstract ScanInteractor providesInteractor(DefaultScanInteractor interactor);
}
