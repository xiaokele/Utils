package com.xiaokele.MyUtils.utils.message;

import android.app.Activity;
import android.content.Context;

import com.QiyiLive.live.BaseActivity;
import com.QiyiLive.live.BaseApplication;
import com.QiyiLive.live.R;
import com.QiyiLive.live.common.DateHelper;
import com.QiyiLive.live.entity.Gpmsg;
import com.QiyiLive.live.utils.LogUtils;
import com.QiyiLive.live.utils.ToastUtils;
import com.QiyiLive.live.xml.XmlNode;
import com.QiyiLive.live.xml.XmlParser;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.SDKOptions;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.netease.nimlib.sdk.chatroom.ChatRoomService;

import io.rong.imlib.RongIMClient;
import io.rong.message.InformationNotificationMessage;

/**
 * Created by supertramp on 17/4/9.
 */
public class NeteaseUtils {

    public static void init(Context context, LoginInfo mInfo, SDKOptions mOptions) {
        NIMClient.init(context, mInfo, mOptions);
    }

//    public static void sendChatroomMsg(Context context, Gpmsg gpmsg) {
//        BaseApplication application = (BaseApplication) ((BaseActivity) context).getApplication();
//
//        XmlNode xml = getBaseXml(gpmsg, context);
//        final String strSend = xml.createXML();
//        xml.deinit();
//        send(application, strSend, gpmsg.getNetease_to());
//    }

    public static void sendChatroomMsg(Context context, Gpmsg gpmsg,String roomId) {
        BaseApplication application = (BaseApplication) ((BaseActivity) context).getApplication();

        XmlNode xml = getBaseXml(gpmsg, context);
        final String strSend = xml.createXML();
        xml.deinit();
        send(application, strSend, roomId);
    }

    private static String getGpmsgId(Gpmsg gpmsg) {
        return gpmsg.getFrom() + gpmsg.getTo() + System.currentTimeMillis();
    }

    private static XmlNode getBaseXml(Gpmsg gpmsg, Context context) {
        XmlNode xml = XmlParser.parserXML(context.getResources().getXml(
                R.xml.gpmsg));
        // 主体
        xml.setAttrValue("id", getGpmsgId(gpmsg));
        xml.setAttrValue("dateline", DateHelper.getDateTime("yyyy-MM-dd HH:mm:ss"));
        xml.setAttrValue("from", gpmsg.getFrom());
        xml.setAttrValue("to", gpmsg.getTo());
        xml.setAttrValue("type", gpmsg.getType());

        xml.setAttrValue("gpmsg:send", "uid", gpmsg.getUid());
        xml.setAttrValue("gpmsg:send", "nickname", gpmsg.getNickName());
        xml.setAttrValue("gpmsg:send", "avatar", gpmsg.getAvatar());
        xml.setAttrValue("gpmsg:send", "role_id", gpmsg.getRole_id());
        xml.setAttrValue("gpmsg:send", "icon", gpmsg.getIcon());
        xml.setAttrValue("gpmsg:send", "grade", gpmsg.getGrade());
        xml.setAttrValue("gpmsg:send", "level", gpmsg.getLevel());

        xml.setAttrValue("gpmsg:msg", "body", gpmsg.getBody());
        xml.setAttrValue("gpmsg:msg", "file", gpmsg.getFile());
        xml.setAttrValue("gpmsg:msg", "file_location", gpmsg.getFile_location());
        return xml;

    }

//    private static void send(BaseApplication application, String strSend, String toJid) {
//        if (null != application.connectionHelper) {
//            application.connectionHelper.sendLiveChatroomStr(strSend, toJid);
//        }
//    }

    private static void send(BaseApplication application, String strSend, String roomId) {
        if (null != application.connectionHelper) {
            application.connectionHelper.sendLiveChatroomStr(strSend, roomId);
        }
    }

//    public static void enterChatroom(final Activity context, String mNeteaseTid)
//    {
//        EnterChatRoomData data = new EnterChatRoomData(mNeteaseTid);
//        NIMClient.getService(ChatRoomService.class).enterChatRoom(data)
//                .setCallback(new RequestCallback()
//                {
//                    @Override
//                    public void onSuccess(Object o)
//                    {
//                        LogGlobal.log("");
//                    }
//
//                    @Override
//                    public void onFailed(int i)
//                    {
//                        if (i == 302)
//                        {
//                            ToastUtils.showToast(context, "请重新登陆！", false);
//                        }
//                    }
//
//                    @Override
//                    public void onException(Throwable throwable)
//                    {
//                        throwable.printStackTrace();
//                    }
//
//                });
//    }

    /**
     * 修改之前的进入云信房间---进入融云直播室
     */
    public static void joinChatRoom(final Activity context, final String roomId) {
        RongIMClient.getInstance().joinChatRoom(roomId, 0, new RongIMClient.OperationCallback() {
            @Override
            public void onSuccess() {
                LogUtils.eLog("观看直播加入聊天室", "聊天室加入成功!");
                final InformationNotificationMessage content = InformationNotificationMessage.obtain("进入房间！");
//                sendMessage(content,roomId);
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                ToastUtils.showToast(context, "聊天室加入失败! errorCode = " + errorCode);
                LogUtils.eLog("观看直播加入聊天室", "聊天室加入失败! errorCode = " + errorCode);
            }
        });
    }

    public static void exitChatroom(String mNeteaseTid) {
        NIMClient.getService(ChatRoomService.class).exitChatRoom(mNeteaseTid);
    }
}
