package com.keven.jianshu.netUtil;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by shou'bo on 2019/4/7.
 */

public interface RequestApis {
    //获取豆瓣Top250 榜单
    @GET("top250")
    Observable<String> getTop250(@Query("start") int start, @Query("count")int count);
}


