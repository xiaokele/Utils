package com.xiaokele.MyUtils.utils;

import com.QiyiLive.live.common.FileHelper;
import com.QiyiLive.live.global.AppPath;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by smc on 2016/2/29.
 */
public class BadwordUtils {

    public static final String BADWORD_TXT = "badword.txt";

    public static final String BADWORD2_TXT = "badword2.txt";

    public static boolean updateBadword2(String badword) {

        String dir = AppPath.getDownloadPath();
        try {
            if (FileHelper.createDirectory(dir)) {
                FileWriter file = new FileWriter(dir + BADWORD2_TXT, true);
                file.write(badword);
                file.close();
            }
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public static boolean updateBadword2(List<String> list) {
        if (null != list && list.size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (String s : list) {
                sb.append(s);
                sb.append("\n");
            }
            return updateBadword2(sb.toString());
        }
        return false;
    }
}
