package com.lngwu.freenovel.mvp;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.View;

import com.lngwu.freenovel.R;
import com.lngwu.freenovel.mvp.base.BaseActivity;
import com.lngwu.freenovel.mvp.home.HomeTabActivity;

/**
 * 欢迎页面
 */
public class WelcomeActivity extends BaseActivity {

    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.activity_welcome);
    }

    @Override
    protected void findViewById() {

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void processLogic() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(WelcomeActivity.this, HomeTabActivity.class));
                finish();
            }
        }, 2000);
    }

    @Override
    protected Context getActivityContext() {
        return this;
    }

    @Override
    public void onClick(View view) {

    }
}
