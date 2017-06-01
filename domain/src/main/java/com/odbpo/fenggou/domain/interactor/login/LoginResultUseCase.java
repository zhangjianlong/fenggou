package com.odbpo.fenggou.domain.interactor.login;


import com.odbpo.fenggou.domain.bean.PhoneLoginResultBean;
import com.odbpo.fenggou.domain.executor.PostExecutionThread;
import com.odbpo.fenggou.domain.executor.ThreadExecutor;
import com.odbpo.fenggou.domain.interactor.UseCase;
import com.odbpo.fenggou.domain.repository.LoginRepository;

import javax.inject.Inject;

import rx.Observable;

public class LoginResultUseCase extends UseCase<PhoneLoginResultBean> {
    LoginRepository repository;

    @Inject
    public LoginResultUseCase(LoginRepository repository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }


    @Override
    protected Observable<PhoneLoginResultBean> buildUseCaseObservable() {
        return repository.login(params[0]);
    }
}
