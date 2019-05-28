package com.keven.jianshu.part6;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.keven.jianshu.R;
import com.keven.jianshu.utils.LogUtils;

import java.io.IOException;
import java.lang.ref.WeakReference;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Part6ImageActivity extends AppCompatActivity {


    private static String imgUrl = "https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1559013549&di=41b6aa8d219f05d44708d296dbf96b5f&src=http://img5.duitang.com/uploads/item/201601/03/20160103233143_4KLWs.jpeg";
    private static final int SUCCESS = 0x0001;
    private static final int FAIL = 0x0002;
    private MyHandler mHandler;
    private ImageView mImageView;
    private MyImageLoader mImageLoader;
    private Button mBt_load;

    class MyHandler extends Handler {
        WeakReference<AppCompatActivity> mWeakReference;

        public MyHandler(AppCompatActivity activity) {
            mWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            final AppCompatActivity appCompatActivity = mWeakReference.get();
            if (appCompatActivity != null) {
                switch (msg.what) {
                    case SUCCESS://成功
                        byte[] Picture = (byte[]) msg.obj;
                        Bitmap bitmap = BitmapFactory.decodeByteArray(Picture, 0, Picture.length);
                        mImageLoader.addBitmap(ImageUtils.hashKeyForCache(imgUrl), bitmap);
                        mImageView.setImageBitmap(bitmap);

                        break;
                    case FAIL://失败

                        break;
                }

            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part6_image);

        mHandler = new MyHandler(this);
        mImageView = findViewById(R.id.iv_lrucache);
        mImageLoader = new MyImageLoader();
        mBt_load = findViewById(R.id.bt_load);
        mBt_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap = getBitmapFromCache();
                if (bitmap != null) {//有缓存
                    LogUtils.e("从缓存中取出图片");
                    mImageView.setImageBitmap(bitmap);
                } else {//没有缓存
                    LogUtils.e("从网络下载图片");
                    downLoadBitmap();
                }
            }
        });

    }

    /**
     * 从缓存中获取图片
     *
     * @return
     */
    private Bitmap getBitmapFromCache() {
        return mImageLoader.getBitmap(ImageUtils.hashKeyForCache(imgUrl));
    }

    /**
     * 从网络上下载图片
     */
    private void downLoadBitmap() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(imgUrl)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                byte[] Picture_bt = response.body().bytes();
                Message message = mHandler.obtainMessage();
                message.obj = Picture_bt;
                message.what = SUCCESS;
                mHandler.sendMessage(message);

            }
        });

    }
}
