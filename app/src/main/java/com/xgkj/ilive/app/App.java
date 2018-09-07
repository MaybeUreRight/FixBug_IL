package com.xgkj.ilive.app;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.StrictMode;
import android.support.annotation.RequiresApi;

import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.cookie.store.PersistentCookieStore;
import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.xgkj.ilive.R;
import com.xgkj.ilive.log.CrashHandler;
import com.xgkj.ilive.log.LogUtils;
import com.xgkj.ilive.utils.ILiveConstant;

import java.util.logging.Level;


/**
 * 作者：刘净辉
 * 日期：2017/7/12 11:43
 */

public class App extends Application  {

    private static Context mContext;
    public static RequestOptions requestOptions;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    public void onCreate() {
        super.onCreate();

        // android 7.0系统解决拍照的问题
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();

        //获取崩溃日志的方法
        getCrashLog();
        okgoInit();
        OkGo.init(this);
        okgoInit();
        //友盟分享初始化配置
        UMShareAPI.get(this);

        mContext = this;

        //设置统计的场景
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);

        //设置微信 、 qq 和 sina 的第三方配置
        PlatformConfig.setWeixin(ILiveConstant.WEIXIN_APPID,ILiveConstant.WEIXIN_APPSERCET);
        PlatformConfig.setQQZone(ILiveConstant.QQ_APPID, ILiveConstant.QQ_SERCET);
       // PlatformConfig.setSinaWeibo(ILiveConstant.SINA_APPID, ILiveConstant.SINA_APPSERCET,"http://sns.whalecloud.com");

        requestOptions = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.default_pic)
                .error(R.drawable.default_pic)
                .priority(Priority.HIGH);
    }

    public static Context getmContext() {
        return mContext;
    }

    /**
     * 获取崩溃日志
     */
    private void getCrashLog() {
        CrashHandler crashHandler = CrashHandler.getCrashHandler();
        crashHandler.setCrashHandler(getApplicationContext());
    }

    private void okgoInit() {
        try{
            OkGo.getInstance()
                    // 打开该调试开关,打印级别INFO,并不是异常,是为了显眼,不需要就不要加入该行
                    // 最后的true表示是否打印okgo的内部异常，一般打开方便调试错误
                    .debug("OkGo", Level.INFO, true)
                    //如果使用默认的 60秒,以下三行也不需要传
                    .setConnectTimeout(OkGo.DEFAULT_MILLISECONDS)  //全局的连接超时时间
                    .setReadTimeOut(OkGo.DEFAULT_MILLISECONDS)     //全局的读取超时时间
                    .setWriteTimeOut(OkGo.DEFAULT_MILLISECONDS)    //全局的写入超时时间
                    //可以全局统一设置缓存模式,默认是不使用缓存,可以不传,具体其他模式看 github 介绍 https://github.com/jeasonlzy/
                    .setCacheMode(CacheMode.NO_CACHE)
                    //可以全局统一设置缓存时间,默认永不过期,具体使用方法看 github 介绍
                    .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)
                    //可以全局统一设置超时重连次数,默认为三次,那么最差的情况会请求4次(一次原始请求,三次重连请求),不需要可以设置为0
                    .setRetryCount(3)
                    //如果不想让框架管理cookie（或者叫session的保持）,以下不需要
//              .setCookieStore(new MemoryCookieStore())            //cookie使用内存缓存（app退出后，cookie消失）
                    .setCookieStore(new PersistentCookieStore())       //cookie持久化存储，如果cookie不过期，则一直有效
                    .setCertificates();//方法一：信任所有证书,不安全有风险

        }catch (Exception e){
            e.printStackTrace();
            LogUtils.e(e.toString());
        }
    }

}
