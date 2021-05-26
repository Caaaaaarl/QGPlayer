
package com.google.android.exoplayer2.ui;


import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.google.android.exoplayer2.QGSharedpreferences;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class LandScapeActivity extends Activity {

    private PlayerView playerView;
    private long currentPosition;
    private String baseUrl;
    private String videoUrl;
    private String token;
    private String applicationName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏
        setContentView(R.layout.activity_land_scape);
        /**
         * 设置为横屏
         */
        if(getRequestedOrientation()!= ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }

        EventBus.getDefault().register(this);
        playerView = findViewById(R.id.play_full_view);

        currentPosition = getIntent().getLongExtra("current_position",0l);
        baseUrl = QGSharedpreferences.get("b","");
        videoUrl = QGSharedpreferences.get("v","");
        token = QGSharedpreferences.get("t","");
        applicationName = QGSharedpreferences.get("a","");
        playerView.setPlayer(this,
                baseUrl,
                videoUrl,
                token,
                applicationName);

        playerView.seekTo(currentPosition);
        playerView.play();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(HSQBean bean) {
        if ("full_out".equals(bean.tag)){
            QGSharedpreferences.save("temp_positon",playerView.getCurrent());
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}