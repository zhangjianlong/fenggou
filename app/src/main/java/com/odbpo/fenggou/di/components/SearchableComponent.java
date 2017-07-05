package com.odbpo.fenggou.di.components;

import dagger.Component;


import com.odbpo.fenggou.di.modules.ActivityModule;
import com.odbpo.fenggou.di.modules.SearchableModule;
import com.odbpo.fenggou.feature.Searchable.SearchableActivity;
import com.core.op.lib.di.PerActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class, modules = {ActivityModule.class, SearchableModule.class})
public interface SearchableComponent extends ActivityComponent {
    void inject(SearchableActivity activity);
}