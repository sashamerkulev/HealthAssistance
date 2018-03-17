package ru.health.assistance.dagger.components;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import ru.health.assistance.dagger.modules.PagerModule;
import ru.health.assistance.dagger.modules.QReaderModule;
import ru.health.assistance.dagger.scopes.QReaderScope;
import ru.health.assistance.presentation.qreader.QReaderActivity;

/**
 * Created by sasha_merkulev on 11.02.2018.
 */

@QReaderScope
@Subcomponent(modules = {PagerModule.class})
public abstract class QReaderComponent extends AndroidInjector.Builder<QReaderActivity>  {

    @Subcomponent.Builder
    public interface Builder {
        QReaderComponent.Builder module(QReaderModule module);
        QReaderComponent build();
    }

}
