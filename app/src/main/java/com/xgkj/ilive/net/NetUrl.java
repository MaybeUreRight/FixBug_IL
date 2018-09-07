package com.xgkj.ilive.net;

/**
 * 作者：刘净辉
 * 日期: 2017/7/15 0015 14:38
 */

public class NetUrl {

//    public static  String BASE_URL = "http://api.devtool6.com";
    public static  String BASE_URL = "http://api.mfglive.com";

    /**
     * 发现中资讯的基本地址
     */
    public static String BASE_PUBLISH_INFO = "http://admin.devtool6.com/news/detail?id=";

    /**
     * 发现模块视频播放的基本
     */
    public static String BASE_PUBLISH_VIDEO = "http://vodltrqzwtx.vod.126.net/vodltrqzwtx/";

    /**
     * 获取验证码的接口
     */
    public static String GET_SMS_CODE = BASE_URL + "/users/send-captcha";

    /**
     * 注册接口
     */
    public static String REGISTER_USER_CODE =BASE_URL + "/users/ccreate";

    /**
     * 用户登录
     */
    public static String USER_LOGIN=BASE_URL + "/users/clogin";

    /**
     * 忘记密码
     */
    public static String FORGET_PWD = BASE_URL + "/users/forget-password";

    /**
     * 获取用户信息
     */
    public static String GET_USER_INFO = BASE_URL + "/users/cview?access-token=";

    /**
     * 获取广告条
     */
    public static String GET_ADVERT_PIC = BASE_URL + "/settings/cadindex";

    /**
     * 获取首页推荐
     */
    public static String GET_HOME_GROOM=BASE_URL + "/channels/cindex";

    /**
     * 获取直播列表的数据
     */
    public static String GET_LIVE_LIST = BASE_URL + "/channels/cchannellist";


    /**
     * 获取资讯列表的数据
     */
    public static String INFORMATION_URL = BASE_URL+"/news/clist";

    /**
     * 获取点播的列表
     */
    public static String VIDEO_LIST_URL = BASE_URL + "/videos/cindex";

    /**
     * 获取点播详情列表
     */
    public static String VIDEO_LIST_DETAILS_URL = BASE_URL + "/videos/cview";

    /**
     * 上传用户头像
     */
    public static String UPLOAD_USERS_ICON = BASE_URL + "/users/cupload?access-token=";

    /**
     * 注册云信id
     */
    public static String REGISTER_YUNXIN_ID = BASE_URL +"/yunxins/ccreate?access-token=";

    /**
     * 获取云信的历史消息
     */
    public static String CHAT_ROOM_HISTORY = BASE_URL + "/yunxins/chatlist";

    /**
     * 创建直播频道
     */
    public static String CREATE_LIVE_CHANNELS=BASE_URL + "/channels/ccreate?access-token=";


    /**
     * 第三方登录
     */
    public static String THIRD_LOGIN_REGISTER = BASE_URL + "/users/oauth-reg";


    /**
     * 创建聊天室id
     */
    public static String CREATE_ROOMID = BASE_URL +"/yunxins/ccreatechat?access-token=";

    /**
     * 观看直播的人数
     */
    public static String LOOK_LIVE_PEOPLE = BASE_URL +"/users/user-info?access-token=";

    /**
     * 获取直播详情
     */
    public static String GET_LIVE_DETAILS = BASE_URL + "/channels/cview?access-token=";

    /**
     * 浏览记录功能
     */
    public static String BROWSE_RECORDS_LIVE_VIDEO = BASE_URL + "/users/view-history?access-token=";

    /**
     * 我的关注记录
     */
    public static String MINE_ATTENTION = BASE_URL + "/users/follow-list?access-token=";

    /**
     * 我的直播记录
     */
    public static String MINE_LIVE= BASE_URL + "/users/cchannellist?access-token=";

    /**
     * 绑定手机号
     */
    public static String BING_PHONE_URL = BASE_URL + "/users/bind-mobile?access-token=";

    /**
     * 投诉建议
     */
    public static String COMPLAINTS_SUGGESTIONS = BASE_URL + "/channels/complaint?access-token=";

    /**
     * 设置info
     */
    public static String SETTINGS_INFO = BASE_URL + "/settings/cinfo";

    /**
     * 用户协议
     */
    public static String USER_AGREE = BASE_URL + "/website/index.html";

    /**
     * 行业列表
     */
    public static String INDUSTRY_URL = BASE_URL + "/channels/industry-list?access-token=";

    /**
     * 修改资料
     */
    public static String UPDATE_INFO = BASE_URL + "/users/cupdate?access-token=";


    /**
     * 热门搜索词
     */
    public static String HOT_SERACH = BASE_URL + "/users/hot-search?access-token=";

    /**
     * 热门词汇搜索
     */
    public static String USER_HOT_SERACH = BASE_URL + "/users/search?access-token=";

    /**
     * 发布聊天记录
     */
    public static String PUBLISH_CHAT_BROWSE = BASE_URL + "/news/insappcomment?access-token=";

    /**
     * 获取公司信息
     */
    public static String COMPANY_INFO = BASE_URL + "/cusers/company?access-token=";


    /**
     * 查询聊天记录
     */
    public static String QUERY_CHAT_CONTENT = BASE_URL + "/news/selappcomment";


    /**
     * 转发接口
     */
    public static String TRANSMIT = BASE_URL + "/channels/zsend";

    /**
     * 点赞功能
     */
    public static String LIKE_URL = BASE_URL + "/channels/like?access-token=";

    /**
     * 他人的用户信息
     */
    public static String OTHERVIEW = BASE_URL + "/users/otherview?access-token=";

    /**
     * 关注接口
     */
    public static String FOLLOW = BASE_URL + "/users/follow?access-token=";

    /**
     * 取消关注接口
     */
    public static String UNFOLLOW = BASE_URL + "/users/un-follow?access-token=";
}
