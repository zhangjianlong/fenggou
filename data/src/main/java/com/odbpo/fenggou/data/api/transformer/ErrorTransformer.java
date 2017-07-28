package com.odbpo.fenggou.data.api.transformer;


import android.widget.Toast;

import com.odbpo.fenggou.data.api.BaseResponse;
import com.odbpo.fenggou.data.api.exception.RepositoryException;

import rx.Observable;
import rx.Subscriber;

/**
 * @author: zjl
 * @Time: 2017/6/1 15:13
 * @Desc: 对response数据进行拦截处理
 */


public class ErrorTransformer<T> implements Observable.Transformer<BaseResponse<T>, T> {

    @Override
    public Observable<T> call(Observable<BaseResponse<T>> comicVineResponseObservable) {
        return comicVineResponseObservable.flatMap(response -> flatResponse(response));
    }

    /**
     * 对网络接口返回的Response进行分割操作
     *
     * @param response
     * @param <T>
     * @return
     */
    public <T> Observable<T> flatResponse(final BaseResponse<T> response) {
        return Observable.create(new Observable.OnSubscribe<T>() {

            @Override
            public void call(Subscriber<? super T> subscriber) {
                String msg = "";
                switch (response.getRescode()) {
                    case 0:
                        if (!subscriber.isUnsubscribed()) {
                            subscriber.onNext(response.getData());
                            subscriber.onCompleted();
                        }
                        return;
                }

                if (!subscriber.isUnsubscribed()) {
                    subscriber.onError(new RepositoryException(msg));
                }
            }
        });
    }
}
