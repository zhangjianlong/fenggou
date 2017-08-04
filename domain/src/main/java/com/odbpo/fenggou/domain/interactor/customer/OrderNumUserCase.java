package com.odbpo.fenggou.domain.interactor.customer;

import com.odbpo.fenggou.domain.bean.OrderNumBean;
import com.odbpo.fenggou.domain.bean.base.CustomerInfo;
import com.odbpo.fenggou.domain.executor.PostExecutionThread;
import com.odbpo.fenggou.domain.executor.ThreadExecutor;
import com.odbpo.fenggou.domain.interactor.UseCase;
import com.odbpo.fenggou.domain.repository.LoginRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * @author: zjl
 * @Time: 2017/8/4 15:07
 * @Desc:
 */
public class OrderNumUserCase extends UseCase<String> {
    LoginRepository repository;

    @Inject
    public OrderNumUserCase(LoginRepository repository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }


    @Override
    protected Observable<String> buildUseCaseObservable() {
        return repository.getOrderNum(params[0]);
    }

}
