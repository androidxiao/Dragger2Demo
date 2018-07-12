/*
 * Copyright 2017 JessYan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.commmonlibrary.cn.imageloader.glide;

import android.widget.ImageView;

import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.commmonlibrary.cn.imageloader.ImageConfig;

/**
 * ================================================
 * 这里存放图片请求的配置信息,可以一直扩展字段,如果外部调用时想让图片加载框架
 * 做一些操作,比如清除缓存或者切换缓存策略,则可以定义一个 int 类型的变量,内部根据 switch(int) 做不同的操作
 * 其他操作同理
 * <p>
 * Created by JessYan on 8/5/16 15:19
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */
public class ImageConfigImpl extends ImageConfig {
    //all:缓存源资源和转换后的资源
    // none:不作任何磁盘缓存
    // source:缓存源资源
    // result：缓存转换后的资源
    private int cacheStrategy;//0：对应DiskCacheStrategy.all,1：对应DiskCacheStrategy.NONE,2：对应DiskCacheStrategy.SOURCE,3：对应DiskCacheStrategy.RESULT
    private int fallback; //请求 url 为空,则使用此图片作为占位符
    private int imageRadius;//图片每个圆角的大小
    private int blurValue;//高斯模糊值, 值越大模糊效果越大
    /**
     * @see {@link Builder#transformation(BitmapTransformation)}
     */
    @Deprecated
    private BitmapTransformation transformation;//glide用它来改变图形的形状
    private ImageView[] imageViews;
    private boolean isCrossFade;//是否使用淡入淡出过渡动画
    private boolean isCenterCrop;//是否将图片剪切为 CenterCrop
    private boolean isCircle;//是否将图片剪切为圆形
    private boolean isClearMemory;//清理内存缓存
    private boolean isClearDiskCache;//清理本地缓存
    private boolean isResId;
    private boolean isSdPath;
    private boolean isAssets;
    private boolean isCustomInto;
    GlideImageLoaderStrategy.IGlideIntoListener listener;

    private ImageConfigImpl(Builder builder) {
        this.url = builder.url;
        this.resId=builder.resId;
        this.sdImagePath=builder.sdImagePath;
        this.assetsPath=builder.assetsPath;
        this.imageView = builder.imageView;
        this.placeholder = builder.placeholder;
        this.errorPic = builder.errorPic;
        this.fallback = builder.fallback;
        this.cacheStrategy = builder.cacheStrategy;
        this.imageRadius = builder.imageRadius;
        this.blurValue = builder.blurValue;
        this.transformation = builder.transformation;
        this.imageViews = builder.imageViews;
        this.isCrossFade = builder.isCrossFade;
        this.isCenterCrop = builder.isCenterCrop;
        this.isCircle = builder.isCircle;
        this.isResId=builder.isResId;
        this.isSdPath=builder.isSDPath;
        this.isAssets=builder.isAssets;
        this.isCustomInto=builder.isCustomInto;
        this.isClearMemory = builder.isClearMemory;
        this.isClearDiskCache = builder.isClearDiskCache;
        this.listener=builder.listener;
    }

    public int getCacheStrategy() {
        return cacheStrategy;
    }

    public BitmapTransformation getTransformation() {
        return transformation;
    }

    public ImageView[] getImageViews() {
        return imageViews;
    }

    public boolean isClearMemory() {
        return isClearMemory;
    }

    public boolean isClearDiskCache() {
        return isClearDiskCache;
    }

    public int getFallback() {
        return fallback;
    }

    public int getBlurValue() {
        return blurValue;
    }

    public boolean isBlurImage() {
        return blurValue > 0;
    }

    public int getImageRadius() {
        return imageRadius;
    }

    public boolean isImageRadius() {
        return imageRadius > 0;
    }

    public boolean isCrossFade() {
        return isCrossFade;
    }

    public boolean isCenterCrop() {
        return isCenterCrop;
    }

    public boolean isCircle() {
        return isCircle;
    }

    public boolean isResId(){
        return isResId;
    }

    public boolean isSdPath(){
        return isSdPath;
    }

    public boolean isAssets(){
        return isAssets;
    }

    public boolean isCustomInto(){
        return isCustomInto;
    }

    public GlideImageLoaderStrategy.IGlideIntoListener getGlideImageLoadListener(){
        return listener;
    }

    public static Builder builder() {
        return new Builder();
    }


    public static final class Builder {
        private int resId;
        private String sdImagePath;
        private String assetsPath;
        private String url;
        private ImageView imageView;
        private int placeholder;
        private int errorPic;
        private int fallback; //请求 url 为空,则使用此图片作为占位符
        private int cacheStrategy;//0对应DiskCacheStrategy.all,1对应DiskCacheStrategy.NONE,2对应DiskCacheStrategy.SOURCE,3对应DiskCacheStrategy.RESULT
        private int imageRadius;//图片每个圆角的大小
        private int blurValue;//高斯模糊值, 值越大模糊效果越大
        /**
         * @see {@link Builder#transformation(BitmapTransformation)}
         */
        @Deprecated
        private BitmapTransformation transformation;//glide用它来改变图形的形状
        private ImageView[] imageViews;
        private boolean isCrossFade;//是否使用淡入淡出过渡动画
        private boolean isCenterCrop;//是否将图片剪切为 CenterCrop
        private boolean isCircle;//是否将图片剪切为圆形
        private boolean isResId;//资源图片
        private boolean isSDPath;//SD 卡图片
        private boolean isAssets;//Assets 图片
        private boolean isClearMemory;//清理内存缓存
        private boolean isClearDiskCache;//清理本地缓存
        private boolean isCustomInto;//自定义下载回调

        private Builder() {
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder placeholder(int placeholder) {
            this.placeholder = placeholder;
            return this;
        }

        public Builder errorPic(int errorPic) {
            this.errorPic = errorPic;
            return this;
        }

        public Builder fallback(int fallback) {
            this.fallback = fallback;
            return this;
        }

        public Builder imageView(ImageView imageView) {
            this.imageView = imageView;
            return this;
        }

        public Builder cacheStrategy(int cacheStrategy) {
            this.cacheStrategy = cacheStrategy;
            return this;
        }

        public Builder imageRadius(int imageRadius) {
            this.imageRadius = imageRadius;
            return this;
        }

        public Builder blurValue(int blurValue) { //blurValue 建议设置为 15
            this.blurValue = blurValue;
            return this;
        }

        public Builder resId(int resId){
            this.resId=resId;
            return this;
        }

        public Builder sdImagePath(String sdImagePath) {
            this.sdImagePath=sdImagePath;
            return this;
        }

        public Builder assetsPath(String assetsPath) {
            this.assetsPath=assetsPath;
            return this;
        }


        /**
         * 给图片添加 Glide 独有的 BitmapTransformation
         * <p>
         * 因为 BitmapTransformation 是 Glide 独有的类, 所以如果 BitmapTransformation 出现在 {@link ImageConfigImpl} 中
         * 会使 {@link com.commmonlibrary.cn.imageloader.ImageLoader} 难以切换为其他图片加载框架, 在 {@link ImageConfigImpl} 中只能配置基础类型和 Android 包里的类
         * 此 API 会在后面的版本中被删除, 请使用其他 API 替代
         *
         * @param transformation {@link BitmapTransformation}
         * @deprecated 请使用 {@link #isCircle()}, {@link #isCenterCrop()}, {@link #isImageRadius()} 替代
         * 如果有其他自定义 BitmapTransformation 的需求, 请自行扩展 {@link com.commmonlibrary.cn.imageloader.BaseImageLoaderStrategy}
         */
        @Deprecated
        public Builder transformation(BitmapTransformation transformation) {
            this.transformation = transformation;
            return this;
        }

        public Builder imageViews(ImageView... imageViews) {
            this.imageViews = imageViews;
            return this;
        }

        public Builder isCrossFade(boolean isCrossFade) {
            this.isCrossFade = isCrossFade;
            return this;
        }

        public Builder isCenterCrop(boolean isCenterCrop) {
            this.isCenterCrop = isCenterCrop;
            return this;
        }

        public Builder isCircle(boolean isCircle) {
            this.isCircle = isCircle;
            return this;
        }

        public Builder isResID(boolean isResId) {
            this.isResId=isResId;
            return this;
        }

        public Builder isSdPath(boolean isSDPath) {
            this.isSDPath=isSDPath;
            return this;
        }

        public Builder isAssets(boolean isAssets){
            this.isAssets=isAssets;
            return this;
        }

        public Builder isCustomInto(boolean isCustomInto) {
            this.isCustomInto=isCustomInto;
            return this;
        }

        public Builder isClearMemory(boolean isClearMemory) {
            this.isClearMemory = isClearMemory;
            return this;
        }

        public Builder isClearDiskCache(boolean isClearDiskCache) {
            this.isClearDiskCache = isClearDiskCache;
            return this;
        }
        GlideImageLoaderStrategy.IGlideIntoListener listener;
        public Builder setIGlideIntoListener(GlideImageLoaderStrategy.IGlideIntoListener listener){
            this.listener=listener;
            return this;
        }


        public ImageConfigImpl build() {
            return new ImageConfigImpl(this);
        }

    }
}
