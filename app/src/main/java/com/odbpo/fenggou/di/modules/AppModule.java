package com.odbpo.fenggou.di.modules;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.odbpo.fenggou.UIThread;
import com.odbpo.fenggou.data.repository.LoginRepositoryImp;
import com.odbpo.fenggou.domain.repository.LoginRepository;
import com.odbpo.fenggou.global.SlashApplication;
import com.odbpo.fenggou.data.ApiClient;
import com.odbpo.fenggou.data.UrlRoot;
import com.odbpo.fenggou.data.api.ApiOption;
import com.odbpo.fenggou.data.executor.JobExecutor;
import com.odbpo.fenggou.domain.executor.PostExecutionThread;
import com.odbpo.fenggou.domain.executor.ThreadExecutor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Eric on 16/3/22.
 */
@Module
public class AppModule {
    private SlashApplication application;

    public AppModule(SlashApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    SlashApplication provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    ApiClient provideApiClient() {
        return ApiOption.Builder.instance(application).url(UrlRoot.HOST).build().create(ApiClient.class);
    }

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }


    @Provides
    @Singleton
    LoginRepository loginRepository(LoginRepositoryImp loginRepository) {
        return loginRepository;
    }


    @Provides
    @Singleton
    Gson gson() {
        return new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    }

}
