package com.keven.jianshu.part7_litepal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.keven.jianshu.R;
import com.keven.jianshu.utils.LogUtils;

import org.litepal.LitePal;
import org.litepal.crud.callback.FindMultiCallback;
import org.litepal.crud.callback.SaveCallback;

import java.util.List;

public class LitePalActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lite_pal);


        //添加数据
//        insertLitepal();
        //更新数据
//        updateLitepal();
        //删除数据
//        deleteLitepal();
        //查询数据
//        queryLitepal();

        //异步操作，避免在主线程中做耗时操作
//        AsyncLitepal();

        //运行时创建数据库

        initViews();
    }

    private void initViews() {
        Button bt_insert = findViewById(R.id.bt_save);
        Button bt_query = findViewById(R.id.bt_query);
        Button bt_delete = findViewById(R.id.bt_delete);
        bt_insert.setOnClickListener(this);
        bt_query.setOnClickListener(this);
        bt_delete.setOnClickListener(this);
    }

    /**
     * 进行异步操作
     */
    private void AsyncLitepal() {
        //异步查询
        LitePal.findAllAsync(IbeaconBean.class).listen(new FindMultiCallback<IbeaconBean>() {
            @Override
            public void onFinish(List<IbeaconBean> list) {

            }
        });

        //异步存储
        IbeaconBean bean = new IbeaconBean();
        bean.setUUID("FDA50693-A4E2-4FB1-AFCF-C6EB07647825");
        bean.setAccuracy("0.01742948930021488");
        bean.setMajor(16160);
        bean.setMinor(48905);
        bean.setRssi("-35");
        bean.setTimeStamp("1557918194312");
        bean.setSrc("Android");
        bean.setDeviceId("0011");
        bean.saveAsync().listen(new SaveCallback() {
            @Override
            public void onFinish(boolean success) {

            }
        });

    }

    /**
     * 数据查询
     */
    private void queryLitepal() {
        //1. 根据 id 进行数据查询
        IbeaconBean bean = LitePal.find(IbeaconBean.class, 1);

        //2. 查询表中所有数据
        List<IbeaconBean> ibeaconBeanList = LitePal.findAll(IbeaconBean.class);

        //3. 根据条件进行查询
        List<IbeaconBean> ibeaconBeans = LitePal.where("src = ? and timeStamp > ?", "Android", "10023023").order("timeStamp").find(IbeaconBean.class);


    }

    /**
     * 进行数据库数据删除
     */
    private void deleteLitepal() {
        //1. 根据id 进行数据删除
        LitePal.delete(IbeaconBean.class, 1);
        //2. 根据筛选条件进行数据删除
        LitePal.deleteAll(IbeaconBean.class, "src = ?", "Android");
    }

    /**
     * 更新数据
     */
    private void updateLitepal() {
        //1.先找到指定数据，进行更新
        IbeaconBean bean = LitePal.find(IbeaconBean.class, 1);
        bean.setDeviceId("0022");
        bean.save();
        //2.先创建一个新的，在进行更新
        IbeaconBean ibeaconBean = new IbeaconBean();
        ibeaconBean.setDeviceId("0033");
        ibeaconBean.update(1);
        //3.根据条件进行更新
        IbeaconBean updateBean = new IbeaconBean();
        updateBean.setDeviceId("0044");
        updateBean.updateAll("src = ?", "Android");
    }

    /**
     * 向 LitePal 中添加数据
     */
    private void insertLitepal() {
        IbeaconBean bean = new IbeaconBean();
        bean.setUUID("FDA50693-A4E2-4FB1-AFCF-C6EB07647825");
        bean.setAccuracy("0.01742948930021488");
        bean.setMajor(16160);
        bean.setMinor(48905);
        bean.setRssi("-35");
        bean.setTimeStamp(System.currentTimeMillis() + "");
        bean.setSrc("Android");
        bean.setDeviceId("0011");
        boolean save = bean.save();
        LogUtils.e("存储成功-->" + save);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_save://存储
                insertLitepal();
                break;
            case R.id.bt_delete://删除
                LitePal.deleteAll(IbeaconBean.class);
                break;
            case R.id.bt_query://查询
                List<IbeaconBean> all = LitePal.findAll(IbeaconBean.class);
                Gson gson=new Gson();
                String s = gson.toJson(all);
                LogUtils.e(s);
                break;
        }
    }
}
