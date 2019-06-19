package com.example.weather365;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatActivity;
import com.wang.avi.AVLoadingIndicatorView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {

    //View's Outlets
    @BindView(R.id.avi) AVLoadingIndicatorView avi;

    //Activity related variables
    public static final int TIME_LAPSE = 4000;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        hideStatusBar();
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        startLoadingAnimation(true);
        showMainScreenAfterMilliSeconds(TIME_LAPSE);
    }

    public void startLoadingAnimation(boolean flag){
        if(flag)
            avi.show();
        else
            avi.hide();
    }
    public void hideStatusBar(){
        if (Build.VERSION.SDK_INT < 16)
        {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        else
        {
            View decorView = getWindow().getDecorView();
            // Hide the status bar.
            int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }
    public void showMainScreenAfterMilliSeconds(int milliSeconds){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, milliSeconds);
    }

}
