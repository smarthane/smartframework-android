package com.smarthane.android.mvp.model.api.service;


import com.smarthane.android.mvp.model.entity.GankEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by smarthane on 2017/11/22.
 */

public interface CommonService {

    @GET("api/data/{type}/{pageSize}/{page}")
    Observable<GankEntity> gank(@Path("type") String type, @Path("pageSize") int pageSize, @Path("page") String page);

    // 随机获取一个妹子
    @GET("api/random/data/福利/1")
    Observable<GankEntity> getRandomGirl();
}
