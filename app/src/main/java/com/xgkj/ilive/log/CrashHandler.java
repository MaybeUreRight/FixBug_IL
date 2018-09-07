package com.xgkj.ilive.log;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Looper;
import android.os.Process;
import android.util.Log;
import android.widget.Toast;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 作者: 刘净辉
 * 日期: 2017/3/30 10:18.
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {

    ExDateUtils exDateUtils = new ExDateUtils();
    /**
     * <pre>
     * 获取打印TAG标志，当前类的名称
     * </pre>
     */
    private static final String TAG = CrashHandler.class.getSimpleName();

    /**
     * <pre>
     *     初始化上下文
     * </pre>
     */
    private Context mContext;

    /**
     * <pre>
     * 初始化crashHandler对象
     * </pre>
     */
    public static volatile CrashHandler crashHandler;
    private Thread.UncaughtExceptionHandler mDefaultUncaught;

    /**
     * <pre>
     * 是否开启日志输出，在true的情况debug下日志输出
     * </pre>
     */
    private static final boolean DEBUG = true;

    private static final String VERSION_NAME = "版本名称";
    private static final String VERSION_NUMBER = "版本号";
    private static final String STACE_TRACE = "踪迹堆栈";
    private ExPropertiesUtil mProp;

    /**
     * <pre>
     * 通过单例模式进行封装 ， 实例化该类
     * @return 返回CrashHandler创建的对象
     * </pre>
     */
    public static CrashHandler getCrashHandler() {
        if (null == crashHandler) {
            synchronized (CrashHandler.class) {
                if (null == crashHandler) {
                    crashHandler = new CrashHandler();
                }
            }
        }
        return crashHandler;
    }

    /**
     * <pre>
     * @param context 上下文
     *                 获取系统默认的UncaughtException处理器，设置该CrashHandler为程序的默认处理器
     * </pre>
     */
    public void setCrashHandler(Context context) {
        mContext = context;
        mDefaultUncaught = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
        mProp = ExPropertiesUtil.getExPropertiesUtil(context);
    }

    /**
     * <pre>
     * 异常处理的方法
     * @param t  线程
     * @param e  异常
     * </pre>
     */
    @Override
    public void uncaughtException(Thread t, Throwable e) {

        Log.d(TAG, "处理:uncaughtException: ");
        if (DEBUG) {
            e.printStackTrace();
        }

        if (!handleException(e) && mDefaultUncaught != null) {
            Log.d(TAG, "uncaughtException: 系统的默认异常处理器处理");
            //如果用户没有处理会让系统默认处理器处理
            mDefaultUncaught.uncaughtException(t, e);
        } else {
            Log.d(TAG, "uncaughtException: 错误处理（执行sleep and killprocess）");
            try {
                t.sleep(2000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            Process.killProcess(Process.myPid());
            System.exit(0);
        }
    }

    private boolean handleException(Throwable throwable) {

        if (null == throwable) {
            Log.d(TAG, "异常对象为空：handleException: ");
            return true;
        }
        //获取异常的消息
        final String exceptionMsg = getExceptionMsg(throwable);
        if (null == exceptionMsg) {
            Log.d(TAG, "handleException: 当前消息为null");
            return false;
        }

        new Thread() {
            @Override
            public void run() {
                super.run();
                Looper.prepare();
                Toast.makeText(mContext, "程序出错，即将退出 \n" + exceptionMsg, Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }.start();
        //得到错误log的输出时间
        String error_log_time = String.format("crash-%s.log", exDateUtils.getExLogDateTime());
        mProp.setFile(error_log_time).init();
        // 收集设备信息
        collectCrashDeviceInfo(mContext);
        //保存错误信息到文件
        saveCrashInfoToFile(throwable);
        mProp.commit();
        return true;
    }

    /**
     * <pre>
     * @param throwable
     * </pre>
     */
    private void saveCrashInfoToFile(Throwable throwable) {
        Log.d(TAG, "saveCrashInfoToFile: 保存错误信息到文件");
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        throwable.printStackTrace(printWriter);
        Throwable cause = throwable.getCause();
        if (null != cause) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        String result = writer.toString();
        mProp.writeString("错误信息", getExceptionMsg(throwable));
        mProp.writeString(STACE_TRACE, result);
    }

    private void collectCrashDeviceInfo(Context mContext) {
        Log.d(TAG, "collectCrashDeviceInfo: 收集程序崩溃的信息");
        PackageManager pm = mContext.getPackageManager();
        try {
            //包信息
            PackageInfo pi = pm.getPackageInfo(mContext.getPackageName(), PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                mProp.writeString(VERSION_NAME, (pi.versionName == null) ? "not set" : pi.versionName);
                mProp.writeInt(VERSION_NUMBER, pi.versionCode);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            Log.e(TAG, "collectCrashDeviceInfo: 收集程序崩溃的信息");
        }
        //使用反射来收集设备信息，例如：系统版本号、设备生产商等环境信息
        Field[] fields = Build.class.getFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                mProp.writeString(field.getName(), "" + field.get(null));
                if (DEBUG) {
                    Log.d(TAG, "collectCrashDeviceInfo: " + field.get(null));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                Log.e(TAG, "collectCrashDeviceInfo: 收集程序崩溃的信息" + e);
            }
        }

    }

    /**
     * <pre>
     * @param throwable 传递的异常的对象
     * @return 获取的异常
     * </pre>
     */
    private String getExceptionMsg(Throwable throwable) {
        //若为空指针异常，返回的结果为null
        String localizedMessage = throwable.getLocalizedMessage();
        if (null == localizedMessage) {
            StackTraceElement[] stackTrace = throwable.getStackTrace();
            StackTraceElement stackTraceElement = stackTrace[0];
            localizedMessage = stackTraceElement.toString();
        }
        return localizedMessage;
    }

    /**
     * 获取捕获异常当前
     */
    public class ExDateUtils {
        //获取打印异常log的时间
        protected final String TAG = ExDateUtils.class.getSimpleName();

        /**
         * <pre>
         * @return 返回获取系统时间的值
         * </pre>
         */
        public String getExLogDateTime() {
            String date_time_format = "yyyy-MM-dd_hh-mm-ss";
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(date_time_format);
            String time_result = simpleDateFormat.format(date);
            return time_result;
        }
    }
}


