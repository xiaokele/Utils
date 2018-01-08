package com.xiaokele.MyUtils.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.QiyiLive.live.activity.LivePlayerActivity;
import com.QiyiLive.live.entity.LiveInfo;
import com.QiyiLive.live.global.ActivityType;

/**
 * 跳转直播界面帮助类
 */
public class LiveUtils {
    public static void toLivePlayer(Context context, LiveInfo live,int livePositionType)
    {
        LogUtils.eLog("LiveUtils","toLivePlayer-2:live="+live.toString());
        Intent intent = new Intent(context, LivePlayerActivity.class);
        intent.putExtra("id", live.getUser().getUid());
        intent.putExtra("rtmp", live.getRtmp());
        intent.putExtra("cover", live.getCover());
        intent.putExtra("user", live.getUser());
        intent.putExtra("num", live.getUsers());
        intent.putExtra("live", live);
        intent.putExtra("livePositionType", livePositionType);
        ((Activity) context).startActivityForResult(intent, ActivityType.LivePlayerActivity);
    }

    public static void toLivePlayer(Context context, int id, String avatar, String rtmp)
    {
        LogUtils.eLog("LiveUtils","toLivePlayer-4:id="+id+"\r\navatar="+avatar+"\r\nrtmp="+rtmp);
        Intent intent = new Intent(context, LivePlayerActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("avatar", avatar);
        intent.putExtra("rtmp", rtmp);
        LogUtils.eLog("---唤起APP", "跳转主播界面-toLivePlayer");
        ((Activity) context).startActivityForResult(intent, ActivityType.LivePlayerActivity);
    }

    public static void toLivePlayer(Context context, int id, String avatar, String rtmp, int position)
    {
        LogUtils.eLog("LiveUtils","toLivePlayer-5:id="+id+"\r\navatar="+avatar+"\r\nrtmp="+rtmp+"\r\nposition="+position);
        Intent intent = new Intent(context, LivePlayerActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("cover", avatar);
        intent.putExtra("rtmp", rtmp);
        intent.putExtra("num", 12);
        intent.putExtra("position", position);
        ((Activity) context).startActivityForResult(intent, ActivityType.LivePlayerActivity);
    }
}
