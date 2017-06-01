package com.odbpo.fenggou.di.modules;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.odbpo.fenggou.UIThread;
import com.odbpo.fenggou.global.SlashApplication;
import com.slash.youth.data.ApiClient;
import com.slash.youth.data.UrlRoot;
import com.slash.youth.data.api.ApiOption;
import com.slash.youth.data.executor.JobExecutor;
import com.slash.youth.domain.executor.PostExecutionThread;
import com.slash.youth.domain.executor.ThreadExecutor;

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
    Gson gson() {
        return new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    }

}
