/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.odbpo.fenggou.di.components;

import android.app.Activity;

import com.google.gson.Gson;
import com.odbpo.fenggou.di.modules.AppModule;
import com.odbpo.fenggou.domain.repository.LoginRepository;
import com.odbpo.fenggou.global.SlashApplication;
import com.odbpo.fenggou.data.ApiClient;
import com.odbpo.fenggou.domain.executor.PostExecutionThread;
import com.odbpo.fenggou.domain.executor.ThreadExecutor;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(Activity baseActivity);

    SlashApplication context();

    ThreadExecutor threadExecutor();

    PostExecutionThread postExecutionThread();

    ApiClient apiClient();

    LoginRepository loginRepository();

    Gson gson();

}