package com.xiaokele.MyUtils.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by supertramp on 17/4/24.
 */
public class UrlConnectionUtil {

    public static String getMsgFromServer(String urlStr) throws Exception
    {

        HttpURLConnection urlConnection = null;
        URL url= null;

        url = new URL(urlStr);
        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setConnectTimeout(3000);
        urlConnection.setUseCaches(false);
        urlConnection.setInstanceFollowRedirects(true);
        urlConnection.setReadTimeout(3000);//响应的超时时间
        urlConnection.setDoOutput(true);//设置这个连接是否可以输出数据
        urlConnection.setRequestMethod("GET");//设置请求的方式
        urlConnection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");//设置消息的类型
        urlConnection.connect();

        if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK)
        {

            InputStream in = urlConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String str = null;
            StringBuffer buffer = new StringBuffer();
            while ((str = br.readLine()) != null)
            {
                //BufferedReader特有功能，一次读取一行数据
                buffer.append(str);
            }
            in.close();
            br.close();
            return buffer.toString();
        }

        return null;

    }

}
