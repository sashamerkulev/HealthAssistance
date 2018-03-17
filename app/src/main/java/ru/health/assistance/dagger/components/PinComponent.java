package ru.health.assistance.dagger.components;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import ru.health.assistance.dagger.modules.PagerModule;
import ru.health.assistance.dagger.modules.PinModule;
import ru.health.assistance.dagger.scopes.PinScope;
import ru.health.assistance.presentation.pinview.PinActivity;

/**
 * Created by sasha_merkulev on 11.02.2018.
 */

@PinScope
@Subcomponent(modules = {PagerModule.class})
public abstract class PinComponent extends AndroidInjector.Builder<PinActivity>  {

    @Subcomponent.Builder
    public interface Builder {
        PinComponent.Builder module(PinModule module);
        PinComponent build();
    }

}
