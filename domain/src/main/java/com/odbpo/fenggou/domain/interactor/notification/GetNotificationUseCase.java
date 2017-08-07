package com.odbpo.fenggou.domain.interactor.notification;

import com.odbpo.fenggou.domain.bean.LoginResponse;
import com.odbpo.fenggou.domain.executor.PostExecutionThread;
import com.odbpo.fenggou.domain.executor.ThreadExecutor;
import com.odbpo.fenggou.domain.interactor.UseCase;
import com.odbpo.fenggou.domain.repository.LoginRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * @author: zjl
 * @Time: 2017/8/7 12:55
 * @Desc:我的-会员中心-站内信箱
 */
public class GetNotificationUseCase extends UseCase<LoginResponse> {
    LoginRepository repository;

    @Inject
    public GetNotificationUseCase(LoginRepository repository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }


    @Override
    protected Observable<LoginResponse> buildUseCaseObservable() {
        return repository.getNotification(map);
    }
}
