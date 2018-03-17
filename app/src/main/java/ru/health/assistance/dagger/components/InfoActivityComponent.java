package ru.health.assistance.dagger.components;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import ru.health.assistance.dagger.modules.InfoActivityModule;
import ru.health.assistance.dagger.modules.PagerModule;
import ru.health.assistance.dagger.scopes.QReaderScope;
import ru.health.assistance.presentation.info.InfoActivity;

/**
 * Created by sasha_merkulev on 11.02.2018.
 */

@QReaderScope
@Subcomponent(modules = {PagerModule.class})
public abstract class InfoActivityComponent extends AndroidInjector.Builder<InfoActivity>  {

    @Subcomponent.Builder
    public interface Builder {
        InfoActivityComponent.Builder module(InfoActivityModule module);
        InfoActivityComponent build();
    }

}
