package com.dragger2.demo.activity.glideapp;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.request.transition.Transition;
import com.commmonlibrary.cn.delegatemvp.presenter.ActivityPresenter;
import com.commmonlibrary.cn.imageloader.ImageLoader;
import com.commmonlibrary.cn.imageloader.glide.GlideImageLoaderStrategy;
import com.commmonlibrary.cn.imageloader.glide.ImageConfigImpl;
import com.commmonlibrary.cn.utils.FileUtils;
import com.commmonlibrary.cn.utils.LogUtil;
import com.dragger2.demo.R;

/**
 * Created by chawei on 2018/7/6.
 */

public class GlideAppActivity extends ActivityPresenter<GlideAppDelegate> {

    @Override
    public void beforeViewData() {

    }

    @Override
    public void requestNetData() {
        LogUtil.debug("requestNetData------>");
    }

    @Override
    protected void bindEventListener() {
        mViewDelegate.setOnClickListener(this,R.id.btn_clear_cache);
            LogUtil.debug("bindEventLister------>");
            initLayout();
    }

    private void initLayout(){
        ImageView iv1=mViewDelegate.get(R.id.iv1);
        ImageView iv2=mViewDelegate.get(R.id.iv2);
        ImageView iv3=mViewDelegate.get(R.id.iv3);

        ImageLoader.getInstance().loadImage(this,
                ImageConfigImpl
                        .builder()
                        .resId(R.drawable.timg)
                        .cacheStrategy(1)
                        .isResID(true)
//                        .url("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1530873436846&di=ca78538eb3b2f82626c5cc3612e1f213&imgtype=jpg&src=http%3A%2F%2Fimg2.imgtn.bdimg.com%2Fit%2Fu%3D3780227177%2C1852476371%26fm%3D214%26gp%3D0.jpg")
                        .imageView(iv1)
                        .isCircle(true)
                        .build());

        ImageLoader.getInstance().loadImage(this,
                ImageConfigImpl
                        .builder()
                        .url("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1530873436846&di=ca78538eb3b2f82626c5cc3612e1f213&imgtype=jpg&src=http%3A%2F%2Fimg2.imgtn.bdimg.com%2Fit%2Fu%3D3780227177%2C1852476371%26fm%3D214%26gp%3D0.jpg")
                        .imageView(iv2)
                        .placeholder(R.drawable.dog)
                        .errorPic(R.drawable.timg)
                        .fallback(R.mipmap.ic_launcher)
                        .cacheStrategy(1)
                        .isCircle(true)
                        .isCustomInto(true)
                        .setIGlideIntoListener(new GlideImageLoaderStrategy.IGlideIntoListener() {
                            @Override
                            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                                Bitmap bitmap = ((BitmapDrawable)resource).getBitmap();
                                final float scale = ((float) bitmap.getHeight()) / ((float) bitmap.getWidth());

                                iv2.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        ViewGroup.LayoutParams lp = iv2.getLayoutParams();
                                        lp.width = 300;
                                        lp.height = (int) (scale * 300);
                                        iv2.setLayoutParams(lp);
                                        iv2.setImageBitmap(bitmap);
                                    }
                                });
                            }

                            @Override
                            public void onLoadFailed(@Nullable Drawable errorDrawable) {
                                LogUtil.debug("onLoadFailed--------->");
                            }
                        })
                        .build());


        ImageLoader.getInstance().loadImage(this,
                ImageConfigImpl
                        .builder()
                        .isAssets(true)
                        .assetsPath("1.png")
                        .cacheStrategy(1)
                        .imageView(iv3)
                        .isCircle(true)
                        .build());

        LogUtil.debug("Glide 缓存的图片个数---》"+ FileUtils.getFileCount(FileUtils.getCacheFilePath(this)));
    }

    @Override
    protected Class<GlideAppDelegate> getDelegateClass() {
        return GlideAppDelegate.class;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_clear_cache:
                ImageLoader.getInstance().clear(this,ImageConfigImpl.builder().isClearDiskCache(true).build());
                break;
        }
    }
}
