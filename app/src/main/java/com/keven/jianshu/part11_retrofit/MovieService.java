package com.keven.jianshu.part11_retrofit;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by zhengjian on 2019/8/26.
 */
public interface MovieService {

    //获取豆瓣Top250 榜单
    @GET("getJoke")
    Observable<JokeSubject> getJoke(@Query("page")int page, @Query("count")int count, @Query("type")String type);
}
