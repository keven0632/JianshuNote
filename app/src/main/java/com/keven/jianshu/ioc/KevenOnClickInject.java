package com.keven.jianshu.ioc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by zhengjian on 2019/6/3.
 *
 * 事件注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)//使用在方法上
public @interface KevenOnClickInject {
    //会有很多个点击事件，所以使用数组
    int[] value();
}

