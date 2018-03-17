package ru.health.assistance.dagger.components;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import ru.health.assistance.dagger.modules.HistoryModule;
import ru.health.assistance.dagger.scopes.HistoryScope;
import ru.health.assistance.presentation.history.HistoryFragment;

/**
 * Created by sasha_merkulev on 11.02.2018.
 */

@HistoryScope
@Subcomponent(modules = {HistoryModule.class})
public abstract class HistoryComponent extends AndroidInjector.Builder<HistoryFragment>  {

    @Subcomponent.Builder
    public interface Builder {
        HistoryComponent.Builder module(HistoryModule module);
        HistoryComponent build();
    }

}
