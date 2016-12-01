package com.lngwu.freenovel.application;

import android.app.Activity;
import android.app.Application;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuling on 16/12/1.
 * <p>
 * 自定义Application
 * 用于初始化各种数据和服务
 */

public class MyApplication extends Application {
    // 记录当前栈里所有activity
    private List<Activity> activities = new ArrayList<>();
    // 记录需要一次性关闭的页面
    private List<Activity> tempActivities = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        // TODO 异常好友管理初始化

        // TODO 文件下载管理初始化
    }

    /**
     * 应用实例
     */
    private static MyApplication instance;

    /**
     * 获取实例
     */
    public static MyApplication getInstance() {
        return instance;
    }

    /**
     * 添加指定的Activity
     */
    public void addActivity(Activity activity) {
        if (activity != null) {
            activities.add(activity);
        }
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activities.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 应用退出, 结束所有的Activities
     */
    public void exit() {
        for (Activity activity : activities) {
            activity.finish();
        }
        System.exit(0);
    }

    /**
     * 临时tempActivities, 添加指定的Activity
     */
    public void addTempActivity(Activity activity) {
        if (activity != null) {
            tempActivities.add(activity);
        }
    }

    /**
     * 临时tempActivities, 结束指定的Activity
     */
    public void finishTempActivity(Activity activity) {
        if (activity != null) {
            tempActivities.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 临时tempActivities, 结束所有的Activities
     */
    public void exitTempActivities() {
        for (Activity activity : tempActivities) {
            activity.finish();
        }
    }

}
