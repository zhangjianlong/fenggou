package com.odbpo.fenggou.domain.interactor.order;

import com.odbpo.fenggou.domain.bean.LoginResponse;
import com.odbpo.fenggou.domain.bean.OrderListBean;
import com.odbpo.fenggou.domain.executor.PostExecutionThread;
import com.odbpo.fenggou.domain.executor.ThreadExecutor;
import com.odbpo.fenggou.domain.interactor.UseCase;
import com.odbpo.fenggou.domain.repository.LoginRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * @author: zjl
 * @Time: 2017/8/8 9:17
 * @Desc: 订单列表
 */
public class OrderUseCase extends UseCase<OrderListBean> {
    LoginRepository repository;

    @Inject
    public OrderUseCase(LoginRepository repository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }


    @Override
    protected Observable<OrderListBean> buildUseCaseObservable() {
        return repository.getOrderList(params[0], map);
    }
}

