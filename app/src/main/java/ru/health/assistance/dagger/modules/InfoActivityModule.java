package ru.health.assistance.dagger.modules;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import ru.health.assistance.dagger.scopes.InfoScope;
import ru.health.assistance.presentation.info.InfoFragment;

/**
 * Created by sasha_merkulev on 11.02.2018.
 */

@Module(includes = {AndroidSupportInjectionModule.class})
public abstract class InfoActivityModule {

    @InfoScope
    @ContributesAndroidInjector(modules = {InfoModule.class})
    abstract InfoFragment scanInjector();

}
