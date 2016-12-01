package com.lngwu.freenovel.mvp.base;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.lngwu.freenovel.R;
import com.lngwu.freenovel.application.MyApplication;

import butterknife.ButterKnife;

/**
 * Created by wuling on 16/12/1.
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    protected Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 锁定竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // 设置切换动画
        //setTheme(R.style.AnimationActivity);

        mContext = getActivityContext();
        initView();
        ButterKnife.bind(this);
        initData();
        MyApplication.getInstance().addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyApplication.getInstance().finishActivity(this);
    }

    /**
     * 初始化Activity方法
     */
    private void initView() {
        loadViewLayout();
    }

    /**
     * 初始化Activity方法
     */
    private void initData() {
        findViewById();
        setListener();
        processLogic();
    }

    /**
     * 加载页面layout
     */
    protected abstract void loadViewLayout();

    /**
     * 加载页面元素
     */
    protected abstract void findViewById();

    /**
     * 设置各种事件的监听器
     */
    protected abstract void setListener();

    /**
     * 业务逻辑处理, 主要与后端交互
     */
    protected abstract void processLogic();

    /**
     * Activity.this
     */
    protected abstract Context getActivityContext();

    /**
     * 弹出Toast
     */
    public void showToast(String content) {
        Toast toast = Toast.makeText(this, content, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    /**
     * 获取屏幕宽度(px)
     */
    public int getScreenWidth() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    /**
     * 获取屏幕高度(px)
     */
    public int getScreenHeight() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }

    /**
     * 获取状态栏高度
     */
    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 获取版本名称
     */
    public String getVersionName() {
        String version = "1.0";
        try {
            PackageManager pm = getPackageManager();
            PackageInfo packageInfo = pm.getPackageInfo(getPackageName(), 0); // 0代表获取版本信息
            version = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return version;
    }

    /**
     * 获取版本号
     */
    public int getVersionCode() {
        int version = 1;
        try {
            PackageManager pm = getPackageManager();
            PackageInfo packageInfo = pm.getPackageInfo(getPackageName(), 0); // 0代表获取版本信息
            version = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return version;
    }

    /**
     * 检查网络是否连接
     */
    public boolean checkNetworkState() {
        boolean flag = false;
        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        if (cm.getActiveNetworkInfo() != null) {
            flag = cm.getActiveNetworkInfo().isAvailable();
        }
        return flag;
    }
}
