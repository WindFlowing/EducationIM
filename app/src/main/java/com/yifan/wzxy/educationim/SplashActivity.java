package com.yifan.wzxy.educationim;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.widget.RelativeLayout;

import java.util.Timer;
import java.util.TimerTask;

/**
 * ∆Ù∂ØΩÁ√Ê
 * Created by yifan on 2015/7/24.
 */
public class SplashActivity extends Activity {
    private RelativeLayout mRL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        mRL = (RelativeLayout) findViewById(R.id.splash);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.2f, 1.0f);
        alphaAnimation.setDuration(3000);
        mRL.startAnimation(alphaAnimation);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
               Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }
}
