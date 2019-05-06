package com.keven.jianshu.part4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.keven.jianshu.R;

import java.util.ArrayList;



public class Part4LayoutSeven extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part4_layout_seven);
//        setContentView(R.layout.buybuybee_coupon_instructions);

        ArrayList<String> front=new ArrayList<>();
        front.add("https://hbimg.huabanimg.com/d9c9a645f96fccd73341b6ba430b486d19cef96193568-tUnXLB_fw658");
        front.add("https://hbimg.huabanimg.com/dac56269d41fac045b896bfd93b28521e0f429289c052-OwykZ6_fw658");
        front.add("https://hbimg.huabanimg.com/9487be3f2d3b2dc8a9f367a399aa16f236e50f219aa10-ZYinlP_fw658");
        front.add("https://hbimg.huabanimg.com/525a0a8c6a4df437c30fe5e6d2d1c73df428de769bd47-2bt5uY_fw658");
        front.add("https://hbimg.huabanimg.com/36fe848112af8662b72ef7f7c224a9b9d261c207995c1-PTWG4Y_fw658");
        front.add("https://hbimg.huabanimg.com/39f3b22f165de366005579605e32e67447d8afa99e2c2-knAVQ7_fw658");
        front.add("https://hbimg.huabanimg.com/49b919f44e80004f09da0636d7367362964e3b59982d3-SYGEQ0_fw658");

        MySignInView viewById = findViewById(R.id.layout_mysignview);
//        viewById.setFrontImage(front);

    }
}
