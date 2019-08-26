package com.keven.jianshu.ioc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by zhengjian on 2019/6/3.
 *
 * 组件注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)//用在属性字段上
public @interface KevenViewInject {
    int value();
}
