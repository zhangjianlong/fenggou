package com.odbpo.fenggou.domain.interactor.customer;

import com.odbpo.fenggou.domain.bean.HistoryNumBean;
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
public class HistoryNumUserCase extends UseCase<HistoryNumBean> {
    LoginRepository repository;

    @Inject
    public HistoryNumUserCase(LoginRepository repository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }


    @Override
    protected Observable<HistoryNumBean> buildUseCaseObservable() {
        return repository.getHistoryNum();
    }
}
