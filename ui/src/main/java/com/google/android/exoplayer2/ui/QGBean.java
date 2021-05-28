package com.google.android.exoplayer2.ui;

import com.google.android.exoplayer2.Player;

public class QGBean {

    String tag;
    long position;
    Player player;

    public QGBean(String tag, long position, Player player) {
        this.tag = tag;
        this.position = position;
        this.player = player;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public long getPosition() {
        return position;
    }

    public void setPosition(long position) {
        this.position = position;
    }
}
