package com.odbpo.fenggou.di.components;

import dagger.Component;


import com.odbpo.fenggou.di.modules.ActivityModule;
import com.odbpo.fenggou.di.modules.SearchModule;
import com.odbpo.fenggou.feature.search.SearchActivity;
import com.core.op.lib.di.PerActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class, modules = {ActivityModule.class, SearchModule.class})
public interface SearchComponent extends ActivityComponent {
    void inject(SearchActivity activity);
}