package com.odbpo.fenggou.domain.interactor.history;

import com.odbpo.fenggou.domain.bean.HistoryListBean;
import com.odbpo.fenggou.domain.bean.base.CustomerInfo;
import com.odbpo.fenggou.domain.executor.PostExecutionThread;
import com.odbpo.fenggou.domain.executor.ThreadExecutor;
import com.odbpo.fenggou.domain.interactor.UseCase;
import com.odbpo.fenggou.domain.repository.LoginRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * @author: zjl
 * @Time: 2017/8/1 13:55
 * @Desc:
 */
public class GetHistoryUserCase extends UseCase<HistoryListBean> {
    LoginRepository repository;

    @Inject
    public GetHistoryUserCase(LoginRepository repository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }


    @Override
    protected Observable<HistoryListBean> buildUseCaseObservable() {
        return repository.getHistoryList(map);
    }

}
