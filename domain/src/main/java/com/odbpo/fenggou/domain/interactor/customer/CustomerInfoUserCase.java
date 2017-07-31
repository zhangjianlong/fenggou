package com.odbpo.fenggou.domain.interactor.customer;

import com.odbpo.fenggou.domain.bean.LoginResponse;
import com.odbpo.fenggou.domain.bean.base.CustomerInfo;
import com.odbpo.fenggou.domain.executor.PostExecutionThread;
import com.odbpo.fenggou.domain.executor.ThreadExecutor;
import com.odbpo.fenggou.domain.interactor.UseCase;
import com.odbpo.fenggou.domain.repository.LoginRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * @author: zjl
 * @Time: 2017/7/31 14:35
 * @Desc:
 */
public class CustomerInfoUserCase extends UseCase<CustomerInfo> {
    LoginRepository repository;

    @Inject
    public CustomerInfoUserCase(LoginRepository repository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }


    @Override
    protected Observable<CustomerInfo> buildUseCaseObservable() {
        return repository.getCustomerInfo();
    }
}
