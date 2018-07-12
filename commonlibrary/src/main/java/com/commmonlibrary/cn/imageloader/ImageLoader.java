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
package com.commmonlibrary.cn.imageloader;

import android.content.Context;

import com.commmonlibrary.cn.imageloader.glide.GlideImageLoaderStrategy;
import com.commmonlibrary.cn.imageloader.glide.ImageConfigImpl;

/**
 * ================================================
 * {@link ImageLoader} 使用策略模式和建造者模式,可以动态切换图片请求框架(比如说切换成 Picasso )
 * 当需要切换图片请求框架或图片请求框架升级后变更了 Api 时
 * 这里可以将影响范围降到最低,所以封装 {@link ImageLoader} 是为了屏蔽这个风险
 *
 * @see <a href="https://github.com/JessYanCoding/MVPArms/wiki#3.4">ImageLoader wiki 文档</a>
 * Created by JessYan on 8/5/16 15:57
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */
public final class ImageLoader {

    GlideImageLoaderStrategy mStrategy;

    private ImageLoader(){
        mStrategy=new GlideImageLoaderStrategy();
    }

    public static ImageLoader getInstance(){
        return ImageLoaderHolder.IMAGE_LOADER;
    }

    private static class ImageLoaderHolder{
        private static final ImageLoader IMAGE_LOADER = new ImageLoader();
    }


    /**
     * 加载图片
     *
     * @param context
     * @param config
     * @param <T>
     */
    public <T extends ImageConfig> void loadImage(Context context, ImageConfigImpl config) {
        mStrategy.loadImage(context, config);
    }

    /**
     * 停止加载或清理缓存
     *
     * @param context
     * @param config
     * @param <T>
     */
    public <T extends ImageConfig> void clear(Context context, ImageConfigImpl config) {
        mStrategy.clear(context, config);
    }

    public BaseImageLoaderStrategy getLoadImgStrategy() {
        return new GlideImageLoaderStrategy();
    }
}
