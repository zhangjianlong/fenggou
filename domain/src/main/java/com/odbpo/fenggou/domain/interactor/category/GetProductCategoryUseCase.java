package com.odbpo.fenggou.domain.interactor.category;

import com.odbpo.fenggou.domain.bean.LoginResponse;
import com.odbpo.fenggou.domain.bean.ProductCategoryBean;
import com.odbpo.fenggou.domain.executor.PostExecutionThread;
import com.odbpo.fenggou.domain.executor.ThreadExecutor;
import com.odbpo.fenggou.domain.interactor.UseCase;
import com.odbpo.fenggou.domain.repository.LoginRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * @author: zjl
 * @Time: 2017/8/1 8:55
 * @Desc:
 */
public class GetProductCategoryUseCase extends UseCase<ProductCategoryBean> {

    LoginRepository repository;

    @Override
    protected Observable<ProductCategoryBean> buildUseCaseObservable() {
        return repository.getProductCategory(map);
    }


    @Inject
    public GetProductCategoryUseCase(LoginRepository repository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }


}
