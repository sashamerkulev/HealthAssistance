package ru.health.assistance.dagger.components;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import ru.health.assistance.dagger.modules.PagerModule;
import ru.health.assistance.dagger.scopes.PagerScope;
import ru.health.assistance.presentation.pagerview.PagerActivity;

/**
 * Created by sasha_merkulev on 11.02.2018.
 */

@PagerScope
@Subcomponent(modules = {PagerModule.class})
public abstract class PagerComponent extends AndroidInjector.Builder<PagerActivity>  {

    @Subcomponent.Builder
    public interface Builder {
        PagerComponent.Builder module(PagerModule module);
        PagerComponent build();
    }

}
