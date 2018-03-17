package ru.health.assistance.dagger.components;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import ru.health.assistance.dagger.modules.ScanModule;
import ru.health.assistance.dagger.scopes.ScanScope;
import ru.health.assistance.presentation.scan.ScanFragment;

/**
 * Created by sasha_merkulev on 11.02.2018.
 */

@ScanScope
@Subcomponent(modules = {ScanModule.class})
public abstract class ScanComponent extends AndroidInjector.Builder<ScanFragment>  {

    @Subcomponent.Builder
    public interface Builder {
        ScanComponent.Builder module(ScanModule module);
        ScanComponent build();
    }

}
