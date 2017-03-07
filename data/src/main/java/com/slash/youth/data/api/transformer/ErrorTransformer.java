package com.slash.youth.data.api.transformer;


import com.slash.youth.data.api.BaseResponse;
import com.slash.youth.data.api.exception.RepositoryException;

import rx.Observable;
import rx.Subscriber;


/**
 * @author op
 * @version 1.0
 * @description 对response数据进行拦截处理
 * @createDate 2016/3/24
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
                if (response.getRescode() != -1) {
                    if (!subscriber.isUnsubscribed()) {
                        subscriber.onNext(response.getData());
                    }
                } else {
                    if (!subscriber.isUnsubscribed()) {
                        subscriber.onError(new RepositoryException(response.getMsg()));
                    }
                    return;
                }
//                if (!response.getResponse().isSuccessful() || response.getBody() == null) {
//                    subscriber.onError(new RepositoryException());
//                } else if (!response.getBody().getStatus().equals("S")) {
//                    subscriber.onError(new RepositoryException(response.getBody().getMessage()));
//                }

                if (!subscriber.isUnsubscribed()) {
                    subscriber.onCompleted();
                }
            }
        });
    }
}
