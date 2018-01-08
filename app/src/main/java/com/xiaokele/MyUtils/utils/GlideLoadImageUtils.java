package com.xiaokele.MyUtils.utils;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.QiyiLive.live.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

/**
 * Created by QiyiLive on 2017/11/25.
 */

public class GlideLoadImageUtils {

    /**
     * 静态内部类单例
     */
    static class SingleHelper {
        private static final GlideLoadImageUtils single = new GlideLoadImageUtils();
    }

    public static GlideLoadImageUtils getInstance() {
        return SingleHelper.single;
    }

    public void loadImageView(ImageView imageView, String url, int widthAndHeight, boolean isCircle) {
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.mipmap.default_img_bg) //加载中图片
                .error(R.mipmap.default_img_bg) //加载失败图片
                .fallback(R.mipmap.default_img_bg) //url为空图片
                .centerCrop() // 填充方式
//                .transform(new CircleCrop()) //圆角
                .priority(Priority.HIGH) //优先级
                .diskCacheStrategy(DiskCacheStrategy.ALL); //缓存策略，后面详细介绍
        if (widthAndHeight != -1) {
            requestOptions.override(widthAndHeight, widthAndHeight);
        }
        if (isCircle) {
            requestOptions.transform(new CircleCrop());
        }
        Glide.with(imageView.getContext()).load(url).apply(requestOptions).into(imageView);
    }

    public void loadAsBitmap(final ImageView imageView, String url, int widthAndHeight, boolean isCircle) {
        RequestOptions requestOptions = new RequestOptions()
                .centerCrop() // 填充方式
//                .override(600,600) //尺寸
//                .transform(new CircleCrop()) //圆角
                .priority(Priority.HIGH) //优先级
                .diskCacheStrategy(DiskCacheStrategy.ALL); //缓存策略，后面详细介绍
        if (widthAndHeight != -1) {
            requestOptions.override(widthAndHeight, widthAndHeight);
        }
        if (isCircle) {
            requestOptions.transform(new CircleCrop());
        }
        RequestBuilder<Bitmap> requestBuilder = Glide.with(imageView.getContext())
                .asBitmap()
                .load(url);
        requestBuilder
                //  .thumbnail(Glide.with(this).load(R.mipmap.error)) //缩略图
                .apply(requestOptions) //requestOptions
//                .transition(new DrawableTransitionOptions().crossFade(2000)) //变换
                .listener(new RequestListener<Bitmap>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                        LogUtils.eLog("glide", "onLoadFailed : " + e.getMessage());
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                        LogUtils.eLog("glide", "onResourceReady : " + resource);
//                        imageView.setImageBitmap(resource);
                        return false;
                    }
                }).into(imageView);
    }
}
