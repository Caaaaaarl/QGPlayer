package com.google.android.exoplayer2.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.exoplayer2.QGSharedpreferences;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class BasePlayerFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(QGBean bean) {
        if ("full".equals(bean.tag)){
            Intent intent = new Intent(getContext(), LandScapeActivity.class);
            intent.putExtra("current_position",bean.position);
            startActivity(intent);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        QGSharedpreferences.save("temp_positon",0l);
        EventBus.getDefault().unregister(this);
    }
}
