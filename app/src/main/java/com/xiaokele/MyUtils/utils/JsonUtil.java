package com.xiaokele.MyUtils.utils;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;

import com.QiyiLive.live.widget.AlertDialog;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonUtil {

    /**
     * 在json对象中根据key找value
     *
     * @param jsonObj json对象
     * @param key     键
     * @return value
     */
    public static int getInt(JSONObject jsonObj, String key) {
        try {
            return jsonObj.getInt(key);
        } catch (Exception e) {
        }
        return 0;
    }

    /**
     * 在json对象中根据key找value
     *
     * @param jsonObj json对象
     * @param key     键
     * @return value
     */
    public static String getString(JSONObject jsonObj, String key) {
        try {
            return jsonObj.getString(key);
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * @param jsonObj json对象
     * @param key     键
     * @return
     */
    public static boolean getBoolean(JSONObject jsonObj, String key) {
        try {
            return jsonObj.getBoolean(key);
        } catch (Exception e) {
        }
        return true;
    }

    /**
     * 在json对象中根据key找json对象
     *
     * @param jsonObj json对象
     * @param key     键
     * @return json对象
     */
    public static JSONObject getJsonObj(JSONObject jsonObj, String key) {
        try {
            return (JSONObject) jsonObj.get(key);
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 转JSON对象
     *
     * @param jsonStr json字符串
     * @return json对象
     */
    public static JSONObject convertJsonObj(Context context, String jsonStr) {
        try {
            return new JSONObject(jsonStr);
        } catch (Exception e) {
            new AlertDialog(context).builder().setMsg("服务器异常").setPositiveButton("确定", new OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            }).show();
        }
        return null;
    }

    /**
     * 将Stiring转换成jsonobject
     * */
    public static JSONObject convertJsonObj(String jsonStr) {
        try {
            return new JSONObject(jsonStr);
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 在json对象中根据key找json数组对象
     *
     * @param jsonObj json对象
     * @param key     键
     * @return json数组对象
     */
    public static JSONArray convertJsonArry(JSONObject jsonObj, String jsonStr) {
        try {
            return jsonObj.getJSONArray(jsonStr);
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 在json数组对象中根据key找json对象
     *
     * @param jsonArry json数组对象 index 下标
     * @param key      键
     * @return json对象
     */
    public static JSONObject convertJsonObj(JSONArray jsonArry, int index) {
        try {
            return (JSONObject) jsonArry.opt(index);
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * json对象put
     */
    public static void putJson(JSONObject jsonObj, String jsonKey, Object jsonValue) {
        try {
            jsonObj.put(jsonKey, jsonValue);
        } catch (Exception e) {
        }
    }

}
