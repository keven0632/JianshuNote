package com.keven.jianshu.ioc;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by zhengjian on 2019/6/3.
 * <p>
 * InjectUtils 注入工具类
 */
public class InjectUtils {
    //注入方法 Activity
    public static void inject(Activity activity) {
        injectLayout(activity);
        injectViews(new ViewFinder(activity), activity);
        injectEvents(new ViewFinder(activity), activity);
    }

    //注入方法 View
    public static void inject(View view, Activity activity) {
        injectViews(new ViewFinder(view), activity);
        injectEvents(new ViewFinder(view), activity);

    }

    //注入方法 Fragment
    public static void inject(View view, Object object) {
        injectViews(new ViewFinder(view), object);
        injectEvents(new ViewFinder(view), object);
    }


    /**
     * 事件注入
     */
    private static void injectEvents(ViewFinder viewFinder, Object object) {
        // 1.获取所有方法
        Class<?> clazz = object.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        // 2.获取方法上面的所有id
        for (Method method : methods) {
            KevenOnClickInject onClick = method.getAnnotation(KevenOnClickInject.class);
            if (onClick != null) {
                int[] viewIds = onClick.value();
                if (viewIds.length > 0) {
                    for (int viewId : viewIds) {
                        // 3.遍历所有的id 先findViewById然后 setOnClickListener
                        View view = viewFinder.findViewById(viewId);
                        if (view != null) {
                            view.setOnClickListener(new DeclaredOnClickListener(method, object));
                        }
                    }
                }
            }
        }
    }


    private static class DeclaredOnClickListener implements View.OnClickListener {
        private Method mMethod;
        private Object mHandlerType;

        public DeclaredOnClickListener(Method method, Object handlerType) {
            mMethod = method;
            mHandlerType = handlerType;
        }

        @Override
        public void onClick(View v) {
            // 4.反射执行方法
            mMethod.setAccessible(true);
            try {
                mMethod.invoke(mHandlerType, v);
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    mMethod.invoke(mHandlerType, null);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    //控件注入
    private static void injectViews(ViewFinder viewFinder, Object object) {
        //获取每一个属性上的注解
        Class<?> myClass = object.getClass();
        Field[] myFields = myClass.getDeclaredFields();//先拿到所有的成员变量
        for (Field field : myFields) {
            KevenViewInject myView = field.getAnnotation(KevenViewInject.class);
            if (myView != null) {
                int value = myView.value();//拿到属性id
                View view = viewFinder.findViewById(value);
                //将view 赋值给类里面的属性
                try {
                    field.setAccessible(true);//为了防止其是私有的，设置允许访问
                    field.set(object, view);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void injectLayout(Activity activity) {
        //获取我们自定义类KevenContentViewInject 上面的注解
        Class<?> myClass = activity.getClass();
        KevenContentViewInject myContentView = myClass.getAnnotation(KevenContentViewInject.class);
        if (myContentView!=null){
            int myLayoutResId = myContentView.value();
            activity.setContentView(myLayoutResId);
        }

    }

}
