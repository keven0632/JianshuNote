package com.keven.jianshu.part8_handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.keven.jianshu.R;

import java.lang.ref.WeakReference;

public class Part8HandlerActivity extends AppCompatActivity {

    private Button bt_handler_send;
    private MyHandler myHandler;
    private Button bt_thread;

    private static class MyHandler extends Handler {

        //弱引用持有Part8HandlerActivity , GC 回收时会被回收掉
        private WeakReference<Part8HandlerActivity> weakReference;

        public MyHandler(Part8HandlerActivity activity) {
            this.weakReference = new WeakReference(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            Part8HandlerActivity activity = weakReference.get();
            super.handleMessage(msg);
            if (null != activity) {
                //执行业务逻辑
                Toast.makeText(activity, "handleMessage", Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part8_handler);

        myHandler = new MyHandler(this);

        bt_handler_send = findViewById(R.id.bt_handler_send);
        bt_thread = findViewById(R.id.bt_thread);
        bt_thread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Looper.prepare();
                        Handler handler = new Handler() {
                            @Override
                            public void handleMessage(Message msg) {
                                super.handleMessage(msg);
                            }
                        };

                        Looper.loop();
                    }
                });
            }
        });
        bt_handler_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message = myHandler.obtainMessage();
                        Message message1 = Message.obtain();
                        Message message2 = new Message();

                        myHandler.sendMessage(message);
                        myHandler.sendEmptyMessage(0);
                        myHandler.post(new Runnable() {
                            @Override
                            public void run() {

                            }
                        });

                    }
                }).start();
            }
        });
    }

    @Override
    protected void onDestroy() {
        //移除所有回调及消息
        myHandler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}
