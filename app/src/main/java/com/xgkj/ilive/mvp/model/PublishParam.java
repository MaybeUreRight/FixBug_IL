package com.xgkj.ilive.mvp.model;

import com.netease.LSMediaCapture.lsMediaCapture;
import com.netease.vcloud.video.effect.VideoEffect;

import java.io.Serializable;

import static com.netease.LSMediaCapture.lsMediaCapture.FormatType.RTMP;
import static com.netease.LSMediaCapture.lsMediaCapture.StreamType.AV;

/**
 * 作者：刘净辉
 * 日期: 2017/8/6 0006 16:05
 */

public class PublishParam implements Serializable{
    public static lsMediaCapture.StreamType streamType = AV;  // 推流类型
    public static lsMediaCapture.FormatType formatType = RTMP; // 推流格式
    public static  lsMediaCapture.VideoQuality videoQuality = lsMediaCapture.VideoQuality.HIGH; //清晰度
    public static  boolean isScale_16x9 = false; //是否强制16:9
    public static  boolean useFilter = true; //是否使用滤镜
    public static VideoEffect.FilterType filterType = VideoEffect.FilterType.clean; //滤镜类型
    public static  boolean frontCamera = true; //是否默认前置摄像头
    public static boolean qosEnable = true;  //是否开启QOS
    public static  boolean uploadLog = true; //是否上传SDK运行日志
}
