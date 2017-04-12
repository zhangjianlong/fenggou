package com.slash.youth.domain.repository;

import com.slash.youth.domain.bean.LabelBean;

import java.util.List;

import rx.Observable;

/**
 * Created by acer on 2017/3/4.
 */

public interface TaskRepository {

    Observable<List<LabelBean>> getLables(String def);

}
