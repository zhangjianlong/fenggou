package com.odbpo.fenggou.domain.interactor.search;

import com.odbpo.fenggou.domain.bean.LoginResponse;
import com.odbpo.fenggou.domain.bean.SearchProductBean;
import com.odbpo.fenggou.domain.executor.PostExecutionThread;
import com.odbpo.fenggou.domain.executor.ThreadExecutor;
import com.odbpo.fenggou.domain.interactor.UseCase;
import com.odbpo.fenggou.domain.repository.LoginRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * @author: zjl
 * @Time: 2017/8/2 9:06
 * @Desc:
 */
public class SearchGoodsUserCase extends UseCase<SearchProductBean> {
    LoginRepository repository;

    @Inject
    public SearchGoodsUserCase(LoginRepository repository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }


    @Override
    protected Observable<SearchProductBean> buildUseCaseObservable() {
        return repository.searchGoods(params[0]);
    }

}
