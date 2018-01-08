package com.xiaokele.MyUtils.utils;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.QiyiLive.live.R;
import com.QiyiLive.live.common.ImageLoaderHelper;
import com.QiyiLive.live.entity.IcoInfo;

/**
 * Created by supertramp on 17/7/16.
 */
public class LabelUtil {

    public static void setLabel(Context context, ImageView imgView, IcoInfo ico, boolean isExcellent, boolean isBig)
    {

        imgView.setVisibility(View.VISIBLE);
        if (ico.isHeat())
        {
            imgView.setImageDrawable(ImageLoaderHelper.getBmpDrawable(context, isBig? R.mipmap.ic_label_golden_54:R.mipmap.ic_label_golden_34));
        }
        else if (ico.isSale())
        {
            imgView.setImageDrawable(ImageLoaderHelper.getBmpDrawable(context, isBig?R.mipmap.ic_label_silver_54:R.mipmap.ic_label_silver_34));
        }
        else if (ico.isOfficial())
        {
            imgView.setImageDrawable(ImageLoaderHelper.getBmpDrawable(context, isBig?R.mipmap.ic_label_coppery_54:R.mipmap.ic_label_coppery_34));
        }
        else
        {

            if (isExcellent)
            {
                imgView.setImageDrawable(ImageLoaderHelper.getBmpDrawable(context, isBig?R.mipmap.ic_label_54:R.mipmap.ic_label_34));
            }
            else
            {
                imgView.setVisibility(View.GONE);
            }

        }

    }

}
