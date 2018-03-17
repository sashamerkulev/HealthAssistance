package ru.health.assistance.dagger.components;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import ru.health.assistance.dagger.modules.InfoModule;
import ru.health.assistance.dagger.scopes.InfoScope;
import ru.health.assistance.presentation.info.InfoFragment;

/**
 * Created by sasha_merkulev on 11.02.2018.
 */

@InfoScope
@Subcomponent(modules = {InfoModule.class})
public abstract class InfoComponent extends AndroidInjector.Builder<InfoFragment>  {

    @Subcomponent.Builder
    public interface Builder {
        InfoComponent.Builder module(InfoModule module);
        InfoComponent build();
    }

}
