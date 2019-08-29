package com.keven.jianshu.part11_retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.keven.jianshu.R;
import com.keven.jianshu.utils.LogUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Part11RetrofitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part11_retrofit);

        initNetWork();
    }

    //https://api.apiopen.top/getJoke?page=1&count=2&type=video
    public static final String BASE_URL = "https://api.apiopen.top/";

    private void initNetWork() {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).
                addCallAdapterFactory(RxJava2CallAdapterFactory.create()).
                addConverterFactory(GsonConverterFactory.create()).build();
        MovieService movieService=retrofit.create(MovieService.class);

        movieService.getJoke(1, 2, "video")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JokeSubject>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(JokeSubject jokeSubject) {
                        LogUtils.e(jokeSubject.getResult().toString());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });




    }
}
