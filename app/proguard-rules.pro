# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\Android\Sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-optimizationpasses 7  #指定代码的压缩级别 0 - 7
-dontusemixedcaseclassnames  #是否使用大小写混合
-dontskipnonpubliclibraryclasses  #如果应用程序引入的有jar包，并且想混淆jar包里面的class
-dontpreverify  #混淆时是否做预校验（可去掉加快混淆速度）
-verbose #混淆时是否记录日志（混淆后生产映射文件 map 类名 -> 转化后类名的映射
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*  #淆采用的算法

-keep public class * extends android.app.Activity  #所有activity的子类不要去混淆
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class com.xgkj.ilive.player.services.NELivePlayerService #指定具体类不要去混淆
-keep public class com.xgkj.ilive.receiver.NetBroadcastReceiver #指定具体类不要去混淆

# 保留support下的所有类及其内部类
-keep class android.support.** {*;}

# 保留继承的
-keep public class * extends android.support.v4.**
-keep public class * extends android.support.v7.**
-keep public class * extends android.support.annotation.**

# 保留R下面的资源
-keep class **.R$* {*;}

#友盟分享混淆配置
-dontshrink
-dontoptimize
-dontwarn com.google.android.maps.**
-dontwarn android.webkit.WebView
-dontwarn com.umeng.**
-dontwarn com.tencent.weibo.sdk.**
-dontwarn com.facebook.**
-keep public class javax.**
-keep public class android.webkit.**
-dontwarn android.support.v4.**
-keep enum com.facebook.**
-keepattributes Exceptions,InnerClasses,Signature
-keepattributes *Annotation*
-keepattributes SourceFile,LineNumberTable

-keep public interface com.facebook.**
-keep public interface com.tencent.**
-keep public interface com.umeng.socialize.**
-keep public interface com.umeng.socialize.sensor.**
-keep public interface com.umeng.scrshot.**

-keep public class com.umeng.socialize.* {*;}

-keepattributes Signature
-keep class com.facebook.**
-keep class com.facebook.** { *; }
-keep class com.umeng.scrshot.**
-keep public class com.tencent.** {*;}
-keep class com.umeng.socialize.sensor.**
-keep class com.umeng.socialize.handler.**
-keep class com.umeng.socialize.handler.*
-keep class com.umeng.weixin.handler.**
-keep class com.umeng.weixin.handler.*
-keep class com.umeng.qq.handler.**
-keep class com.umeng.qq.handler.*
-keep class UMMoreHandler{*;}
-keep class com.tencent.mm.sdk.modelmsg.WXMediaMessage {*;}
-keep class com.tencent.mm.sdk.modelmsg.** implements com.tencent.mm.sdk.modelmsg.WXMediaMessage$IMediaObject {*;}
-keep class im.yixin.sdk.api.YXMessage {*;}
-keep class im.yixin.sdk.api.** implements im.yixin.sdk.api.YXMessage$YXMessageData{*;}
-keep class com.tencent.mm.sdk.** {
   *;
}
-keep class com.tencent.mm.opensdk.** {
   *;
}
-keep class com.tencent.wxop.** {
   *;
}
-keep class com.tencent.mm.sdk.** {
   *;
}
-dontwarn twitter4j.**
-keep class twitter4j.** { *; }

-keep class com.tencent.** {*;}
-dontwarn com.tencent.**
-keep class com.kakao.** {*;}
-dontwarn com.kakao.**
-keep public class com.umeng.com.umeng.soexample.R$*{
    public static final int *;
}
-keep public class com.linkedin.android.mobilesdk.R$*{
    public static final int *;
}
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keep class com.tencent.open.TDialog$*
-keep class com.tencent.open.TDialog$* {*;}
-keep class com.tencent.open.PKDialog
-keep class com.tencent.open.PKDialog {*;}
-keep class com.tencent.open.PKDialog$*
-keep class com.tencent.open.PKDialog$* {*;}
-keep class com.umeng.socialize.impl.ImageImpl {*;}
-keep class com.sina.** {*;}
-dontwarn com.sina.**
-keep class  com.alipay.share.sdk.** {
   *;
}

-keepnames class * implements android.os.Parcelable {
    public static final ** CREATOR;
}

-keep class com.linkedin.** { *; }
-keep class com.android.dingtalk.share.ddsharemodule.** { *; }
-keepattributes Signature


#友盟统计混淆
-keepclassmembers class * {
   public <init> (org.json.JSONObject);
}

-keep public class [com.xgkj.ilive].R$*{
public static final int *;
}

-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}



#网易直播混淆配置
-keep class com.netease.** { *; }

#autolayout不能混淆
-keep class com.zhy.autolayout.** {*;}

-dontwarn okio.**
#okgo不能混淆的代码
#okgo
-dontwarn com.lzy.okgo.**
-keep class com.lzy.okgo.**{*;}

#okrx
-dontwarn com.lzy.okrx.**
-keep class com.lzy.okrx.**{*;}

#okrx2
#-dontwarn com.lzy.okrx2.**
#-keep class com.lzy.okrx2.**{*;}

#okserver
-dontwarn com.lzy.okserver.**
-keep class com.lzy.okserver.**{*;}

#gson解析不能混淆
-keep class com.google.gson.** {*;}

#convenientbanner不能混淆
-keep class com.bigkoo.convenientbanner.** {*;}

#
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }

-keepclasseswithmembernames class * {
       @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
       @butterknife.* <methods>;
         }



#xrecyclerview不能混淆
-keep class com.jcodecraeer.xrecyclerview.** {*;}

#java-json不能混淆
-keep class org.json.** {*;}

#glide混淆配置
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.AppGlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

#timeselector混淆配置
-keep class org.feezu.liuli.timeselector.** {*;}

#flexbox
-keep class com.google.android.flexbox.** {*;}

#实体类混淆配置
-keep class com.xgkj.ilive.mvp.model.**{*;}

#工具类混淆配置
-keep class com.xgkj.ilive.mvp.model.AdvanceNoticeModel {*;}
-keep class com.xgkj.ilive.mvp.model.AdvanceLiveModel {*;}
-keep class com.xgkj.ilive.mvp.model.AdvertModel {*;}
-keep class com.xgkj.ilive.mvp.model.AnnounceInAdvanceModel {*;}
-keep class com.xgkj.ilive.mvp.model.BestNewModel {*;}
-keep class com.xgkj.ilive.mvp.model.BindingModel {*;}
-keep class com.xgkj.ilive.mvp.model.BrowseRecordsLiveModel {*;}
-keep class com.xgkj.ilive.mvp.model.BrowseRecordsModel {*;}
-keep class com.xgkj.ilive.mvp.model.BrowseRecordsVideoModel {*;}
-keep class com.xgkj.ilive.mvp.model.ChatModel {*;}
-keep class com.xgkj.ilive.mvp.model.CommentModel {*;}
-keep class com.xgkj.ilive.mvp.model.DBSerachModel {*;}
-keep class com.xgkj.ilive.mvp.model.ForgetPasswordModel {*;}
-keep class com.xgkj.ilive.mvp.model.GuidePagerModel {*;}
-keep class com.xgkj.ilive.mvp.model.HelpModel {*;}
-keep class com.xgkj.ilive.mvp.model.HighlightsModel {*;}
-keep class com.xgkj.ilive.mvp.model.HomeModel {*;}
-keep class com.xgkj.ilive.mvp.model.HotInformationModel {*;}
-keep class com.xgkj.ilive.mvp.model.HotModel {*;}
-keep class com.xgkj.ilive.mvp.model.HotVideoModel {*;}
-keep class com.xgkj.ilive.mvp.model.IndustryModel {*;}
-keep class com.xgkj.ilive.mvp.model.InformationDetailModel {*;}
-keep class com.xgkj.ilive.mvp.model.JoinModel {*;}
-keep class com.xgkj.ilive.mvp.model.LiveDetailsModel {*;}
-keep class com.xgkj.ilive.mvp.model.LiveModel {*;}
-keep class com.xgkj.ilive.mvp.model.LiveStreamingModel {*;}
-keep class com.xgkj.ilive.mvp.model.LoginModel {*;}
-keep class com.xgkj.ilive.mvp.model.LookStatusModel {*;}
-keep class com.xgkj.ilive.mvp.model.MainModel {*;}
-keep class com.xgkj.ilive.mvp.model.MineAttentionModel {*;}
-keep class com.xgkj.ilive.mvp.model.MineCompanyInfoModel {*;}
-keep class com.xgkj.ilive.mvp.model.MineInformationModel {*;}
-keep class com.xgkj.ilive.mvp.model.MineLiveModel {*;}
-keep class com.xgkj.ilive.mvp.model.MineModel {*;}
-keep class com.xgkj.ilive.mvp.model.MineVideoModel {*;}
-keep class com.xgkj.ilive.mvp.model.NewsSerachModel {*;}
-keep class com.xgkj.ilive.mvp.model.PersonalProfileModel {*;}
-keep class com.xgkj.ilive.mvp.model.PhoneCodeLoginModel {*;}
-keep class com.xgkj.ilive.mvp.model.PublishInformationModel {*;}
-keep class com.xgkj.ilive.mvp.model.PublishParam {*;}
-keep class com.xgkj.ilive.mvp.model.PublishVideoDetailsModel {*;}
-keep class com.xgkj.ilive.mvp.model.PublishVideoModel {*;}
-keep class com.xgkj.ilive.mvp.model.QueryChatMessageModel {*;}
-keep class com.xgkj.ilive.mvp.model.RegisterModel {*;}
-keep class com.xgkj.ilive.mvp.model.RoomIdModel {*;}
-keep class com.xgkj.ilive.mvp.model.SerachModel{*;}
-keep class com.xgkj.ilive.mvp.model.SettingsModel {*;}
-keep class com.xgkj.ilive.mvp.model.SmsCodeModel {*;}
-keep class com.xgkj.ilive.mvp.model.SplashModel {*;}
-keep class com.xgkj.ilive.mvp.model.ThirdLoginModel {*;}
-keep class com.xgkj.ilive.mvp.model.UpdateInfoModel {*;}
-keep class com.xgkj.ilive.mvp.model.UpLoadIconModel {*;}

#自定义混淆配置
-keep class com.xgkj.ilive.view.** {*;}

#log混淆配置
-keep class com.xgkj.ilive.log.** {*;}


-dontwarn com.netease.**
-keep class com.netease.** {*;}
#如果你使用全文检索插件，需要加入
-dontwarn org.apache.lucene.**
-keep class org.apache.lucene.** {*;}


# 保留我们自定义控件（继承自View）不被混淆
-keep public class * extends android.view.View{
    *** get*();
    void set*(***);
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}


-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
 long producerIndex;
 long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
 rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
 rx.internal.util.atomic.LinkedQueueNode consumerNode;
}