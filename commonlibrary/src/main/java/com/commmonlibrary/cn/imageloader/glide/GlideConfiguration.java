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

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.DiskLruCacheWrapper;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.AppGlideModule;
import com.commmonlibrary.cn.glidehttp.OkHttpUrlLoader;
import com.commmonlibrary.cn.imageloader.BaseImageLoaderStrategy;
import com.commmonlibrary.cn.imageloader.ImageLoader;
import com.commmonlibrary.cn.utils.FileUtils;
import com.commmonlibrary.cn.utils.LogUtil;

import java.io.File;
import java.io.InputStream;

import okhttp3.OkHttpClient;

/**
 * ================================================
 * {@link AppGlideModule} 的默认实现类
 * 用于配置缓存文件夹,切换图片请求框架等操作
 * <p>
 * Created by JessYan on 16/4/15.
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */
@GlideModule(glideName = "CommonGlide")
public class GlideConfiguration extends AppGlideModule {

    public static final int IMAGE_DISK_CACHE_MAX_SIZE = 100 * 1024 * 1024;//图片缓存文件最大值为100Mb

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {

        builder.setDiskCache(new DiskCache.Factory() {
            @Override
            public DiskCache build() {
                LogUtil.debug("Glide 缓存设置-------》");
                // Careful: the external cache directory doesn't enforce permissions
                LogUtil.debug("Glide 缓存路径----》"+FileUtils.getCacheFilePath(context));
                return DiskLruCacheWrapper.create(FileUtils.makeDirs(new File(FileUtils.getCacheFile(context), "Glide")), IMAGE_DISK_CACHE_MAX_SIZE);
            }
        });

        MemorySizeCalculator calculator = new MemorySizeCalculator.Builder(context).build();
        int defaultMemoryCacheSize = calculator.getMemoryCacheSize();
        int defaultBitmapPoolSize = calculator.getBitmapPoolSize();

        int customMemoryCacheSize = (int) (1.2 * defaultMemoryCacheSize);
        int customBitmapPoolSize = (int) (1.2 * defaultBitmapPoolSize);

        builder.setMemoryCache(new LruResourceCache(customMemoryCacheSize));
        builder.setBitmapPool(new LruBitmapPool(customBitmapPoolSize));
        BaseImageLoaderStrategy loadImgStrategy = ImageLoader.getInstance().getLoadImgStrategy();
        ((GlideAppliesOptions) loadImgStrategy).applyGlideOptions(context, builder);
        LogUtil.debug("Glide  缓存设置结束-------》");
    }

    @Override
    public void registerComponents(Context context, Glide glide, Registry registry) {
        LogUtil.debug("Glide 使用 OkHttp3.0 框架");
        //Glide 默认使用 HttpURLConnection 做网络请求,在这切换成 Okhttp 请求
        registry.replace(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory(new OkHttpClient()));
    }

    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }
}
