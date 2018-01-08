package com.xiaokele.MyUtils.utils;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.QiyiLive.live.R;

import static android.support.design.widget.Snackbar.make;

/**
 * Created by chenxb on 16/6/27.
 */

public class ToastUtils {

    public static void showToast(Context context, String text) {
        if (context instanceof Activity) {
            showToast((Activity) context, text, false);
        } else {
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
        }
    }

    public static void showToast(Activity activity, String text) {
        showToast(activity, text, false);
    }

    public static void showToast(Activity activity, String text, boolean islong) {
        try {
            ViewGroup viewGroup = (ViewGroup) activity.findViewById(android.R.id.content);
//            View rootLayout = viewGroup.getChildAt(0);
            Snackbar snackbar = Snackbar.make(viewGroup, text, Snackbar.LENGTH_SHORT);
            View snackview = snackbar.getView();
            snackview.setBackgroundResource(R.color.main_color_yellow);
            ((TextView) snackview.findViewById(R.id.snackbar_text)).setTextColor(activity.getResources().getColor(R.color.white));
//            ViewGroup.LayoutParams vl = snackview.getLayoutParams();
//            LinearLayout.LayoutParams ll = new LinearLayout.LayoutParams(vl.width,vl.height);
//            ll.gravity = Gravity.TOP;
//            snackview.setLayoutParams(ll);
            snackbar.show();
        } catch (Exception e) {
            Toast.makeText(activity, text, islong ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT).show();
        }
    }

    public static void showToast(View view, String text) {
        make(view, text, Snackbar.LENGTH_SHORT).show();
    }
}
