package com.xiaokele.MyUtils.utils;

import android.widget.ImageView;
import com.QiyiLive.live.R;

/**
 * Created by supertramp on 17/1/25.
 */
public class VipUtil {

    public static void setVip(int rold_id, int level, ImageView ivVip)
    {
        //机器人的等级
        if (level == 0) {
            switch (rold_id)
            {
                case 0:
                    ivVip.setImageResource(R.mipmap.level_vip_gray01);
                    break;
                case 1:
                    ivVip.setImageResource(R.mipmap.level_vip_gray01);
                    break;
                case 2:
                    ivVip.setImageResource(R.mipmap.level_vip_red01);
                    break;
                case 3:
                    ivVip.setImageResource(R.mipmap.level_vip_orange01);
                    break;
                case 4:
                    ivVip.setImageResource(R.mipmap.level_vip_purple01);
                    break;
            }
        }
        //真实用户等级
        if (level > 0 && level < 41)
        {
            switch (rold_id)
            {
                case 1:
                    ivVip.setImageResource(R.mipmap.level_vip_gray01);
                    break;
                case 2:
                    ivVip.setImageResource(R.mipmap.level_vip_red01);
                    break;
                case 3:
                    ivVip.setImageResource(R.mipmap.level_vip_orange01);
                    break;
                case 4:
                    ivVip.setImageResource(R.mipmap.level_vip_purple01);
                    break;
            }
        }
        else if(level > 40 && level < 81)
        {
            switch (rold_id)
            {
                case 1:
                    ivVip.setImageResource(R.mipmap.level_vip_gray41);
                    break;
                case 2:
                    ivVip.setImageResource(R.mipmap.level_vip_red41);
                    break;
                case 3:
                    ivVip.setImageResource(R.mipmap.level_vip_orange41);
                    break;
                case 4:
                    ivVip.setImageResource(R.mipmap.level_vip_purple41);
                    break;
            }
        }
        else if (level > 80 && level < 121)
        {
            switch (rold_id)
            {
                case 1:
                    ivVip.setImageResource(R.mipmap.level_vip_gray81);
                    break;
                case 2:
                    ivVip.setImageResource(R.mipmap.level_vip_red81);
                    break;
                case 3:
                    ivVip.setImageResource(R.mipmap.level_vip_orange81);
                    break;
                case 4:
                    ivVip.setImageResource(R.mipmap.level_vip_purple81);
                    break;
            }

        }
        else if (level > 120 && level < 161)
        {
            switch (rold_id)
            {
                case 1:
                    ivVip.setImageResource(R.mipmap.level_vip_gray121);
                    break;
                case 2:
                    ivVip.setImageResource(R.mipmap.level_vip_red121);
                    break;
                case 3:
                    ivVip.setImageResource(R.mipmap.level_vip_orange121);
                    break;
                case 4:
                    ivVip.setImageResource(R.mipmap.level_vip_purple121);
                    break;
            }
        }
        else if (level > 160 && level < 191)
        {
            switch (rold_id)
            {
                case 1:
                    ivVip.setImageResource(R.mipmap.level_vip_gray161);
                    break;
                case 2:
                    ivVip.setImageResource(R.mipmap.level_vip_red161);
                    break;
                case 3:
                    ivVip.setImageResource(R.mipmap.level_vip_orange161);
                    break;
                case 4:
                    ivVip.setImageResource(R.mipmap.level_vip_purple161);
                    break;
            }
        }
        else if (level > 190 && level <= 200)
        {
            switch (rold_id)
            {
                case 1:
                    ivVip.setImageResource(R.mipmap.level_vip_gray191);
                    break;
                case 2:
                    ivVip.setImageResource(R.mipmap.level_vip_red191);
                    break;
                case 3:
                    ivVip.setImageResource(R.mipmap.level_vip_purple191);
                    break;
                case 4:
                    ivVip.setImageResource(R.mipmap.level_vip_purple191);
                    break;
            }
        }
    }
}
