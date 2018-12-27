package com.example.a1220_rk.myapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Environment;
import android.util.Log;

import com.example.a1220_rk.R;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;
import com.nostra13.universalimageloader.core.display.CircleBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

import java.io.File;



public class Application extends android.app.Application {
    public static final String TAG="Application";
    private File file;

    @Override
    public void onCreate() {
        super.onCreate();
        /*
        * context.getFilesDir() 内部存储data/data/包名/files目录
          context.getCacheDir() 内部存储data/data/包名/cache目录
          Environment.getExternalStorageDirectory() 外部存储根目录
          Environment.getExternalStoragePublicDirectory (Environment.DIRECTORY_DCIM) 外部存储公有目录
         context.getExternalFilesDir() 外部存储私有目录storage/sdcard/Android/data/包名/files。一般存储长时间保存的数据。
         context.getExternalCacheDir() 外部存储私有目录storage/sdcard/Android/data/包名/cache。一般存储临时缓存数据。
        * */
          // String mPath=getFilesDir().getPath()+"/DAY02";
        String mPath=getExternalCacheDir().getPath()+"/1220_rk";
           file = new File(mPath);
        Log.i(TAG, "onCreate: "+mPath);
        //初始化ImageLoader
      //  ImageLoaderConfiguration  配置Imageloader
        ImageLoaderConfiguration build = new ImageLoaderConfiguration.Builder(this)
               // .diskCacheExtraOptions(480, 800, null)  // 本地缓存的详细信息(缓存的最大长宽)，最好不要设置这个
               // .threadPriority(Thread.NORM_PRIORITY - 2) // default 设置当前线程的优先级
              //  .tasksProcessingOrder(QueueProcessingType.FIFO) // default
               // .denyCacheImageMultipleSizesInMemory()
               // .memoryCacheSizePercentage(13) // default
                //.diskCache(new UnlimitedDiscCache(cacheDir)) // default 可以自定义缓存路径
                // default为使用HASHCODE对UIL进行加密命名， 还可以用MD5(new Md5FileNameGenerator())加密
              //  .diskCacheFileNameGenerator(new HashCodeFileNameGenerator())
               // .imageDownloader(new BaseImageDownloader(context)) // default
                //.imageDecoder(new BaseImageDecoder(true)) // default
                //.defaultDisplayImageOptions(DisplayImageOptions.createSimple()) // default
                //.discCache(new LimitedAgeDiscCache(cacheDir, 7 * 24 * 60 * 60))// 自定义缓存路径,7天后自动清除缓存
               // .writeDebugLogs() // 打印debug lo
                //.threadPoolSize(3) // default  线程池内加载的数量
                .memoryCache(new LruMemoryCache(2 * 1024 * 1024)) //可以通过自己的内存缓存实现
                .memoryCacheSize(2 * 1024 * 1024)  // 内存缓存的最大值
                .diskCacheSize(50 * 1024 * 1024) // 50 Mb sd卡(本地)缓存的最大值
                .diskCacheFileCount(100)  // 可以缓存的文件数量
                .diskCache(new UnlimitedDiskCache(file))//UnlimitedDiskCache 限制这个图片的缓存路径
                .defaultDisplayImageOptions(Options())
                .build();
        ImageLoader.getInstance().init(build);//全局初始化此配置

    }
       private  DisplayImageOptions Options(){
        return  new DisplayImageOptions.Builder()
                .showStubImage(R.mipmap.bw_er)//缓冲过程中图片
                .showImageForEmptyUri(R.mipmap.bw_empty)// 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.mipmap.bw_wf)// 设置图片加载或解码过程中发生错误显示的图片
                .cacheInMemory(true)//缓存道内存
                .cacheOnDisc(true)//缓存到硬盘
                .bitmapConfig(Bitmap.Config.ARGB_8888) //设置图片的解码类型
               // .displayer(new RoundedBitmapDisplayer(30,10))//圆角
                //.displayer(new CircleBitmapDisplayer(Color.RED, 10))//圆形
                .build();
       }




}
