package com.xgkj.ilive.log;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 作者: 刘净辉
 * 日期: 2017/3/31 10:03.
 */

public class LogUtils {

    private static final String TAG="LogUtils";

    //日志类型标识符 (优先级：由低到高进行排列，取值越小优先级越高)
    public static final char SHOW_VERBOSE_LOG=0x01;
    public static final char SHOW_DEBUG_LOG=0x02;
    public static final char SHOW_INFO_LOG=0x04;
    public static final char SHOW_WRAN_LOG=0x08;
    public static final char SHOW_ERROR_LOG=0x10;

    //默认为五种日志类型，均在LogCat输出显示
    public static char m_cLogCatShowLogType=SHOW_VERBOSE_LOG
            | SHOW_DEBUG_LOG
            | SHOW_INFO_LOG
            | SHOW_WRAN_LOG
            | SHOW_ERROR_LOG;

    //默认五种日志类型，均在日志文件输出保存
//    public static char m_cFileSaveLogType =0;
      public static char m_cFileSaveLogType = SHOW_VERBOSE_LOG
            | SHOW_DEBUG_LOG
            | SHOW_INFO_LOG
            | SHOW_WRAN_LOG
            | SHOW_ERROR_LOG;

    //存放日志文件的目录的全路径
      public static String m_strLogFolderPath="";

      private static void SaveLog2File(String strMsg){

          FileWriter fileWriter=null;
          BufferedWriter bufferedWriter = null;

          do{//非循环，是为了减少分支缩进深度

              //得到状态
              String state = Environment.getExternalStorageState();
              //未安装SD卡
              if (true != Environment.MEDIA_MOUNTED.equals(state)){
                  Log.e(TAG, "SaveLog2File: Not mount sdcard");
                  break;
              }

              //sdcard不可写
              if (true == Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)){
                  Log.e(TAG, "SaveLog2File: sdcard don't write");
                  break;
              }
              // 只有存在外部 SD 卡且可写入的情况下才允许保存日志文件到指定目录路径下
              // 没有指定日志文件存放位置的话，就写到默认位置，即 SD 卡根目录下的 TimeTerminal_log 目录中
              if (true==m_strLogFolderPath.trim().equals("")){
                  String strLogFolderPath = Environment.getExternalStorageDirectory() + "/ILive_Log";
                  File fileSaveLogFolderPath = new File(strLogFolderPath);

                  //保存的日志的路径不存在就创建它
                  if (true != fileSaveLogFolderPath.exists()){
                      fileSaveLogFolderPath.mkdirs();
                  }

                  //如果保存的日志还不存在，就提醒用户
                  if (true !=fileSaveLogFolderPath.exists()){
                      Log.e(TAG, "SaveLog2File: Create a new folder failed");
                      break;
                  }
                  //指定日志文件保存的路径，文件名由内部按日期时间格式
                  m_strLogFolderPath=strLogFolderPath;
              }

              String strDateTimeFileName = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss").format(new Date());

              File fileLogFilePath = new File(m_strLogFolderPath, strDateTimeFileName+".log");
              //如果日志文件不存在就创建它
              if (true != fileLogFilePath.exists()){
                  try{
                      synchronized (fileLogFilePath){
                          fileLogFilePath.createNewFile();
                      }
                  }catch (Exception e){
                      e.printStackTrace();
                      break;
                  }

              }

              //如果日志执行到这部还不存在，就不创建了提醒用户
              if (true != fileLogFilePath.exists()){
                  Log.e(TAG, "SaveLog2File: Create a new file failed");
                  break;
              }

              try {
                  //文件支持续写
                  fileWriter = new FileWriter(fileLogFilePath,true);
              } catch (IOException e) {
                  Log.e(TAG, "SaveLog2File: New file install failed");
                  e.printStackTrace();
                  break;
              }

              bufferedWriter = new BufferedWriter(fileWriter);

              String strDateTimeFileHead = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss").format(new Date());

              strMsg =TAG +""+ strDateTimeFileHead +" " + strMsg+"\n\n";

              try {
                  bufferedWriter.write(strMsg);
                  bufferedWriter.flush();
              } catch (IOException e) {
                  Log.e(TAG, "bufferedWriter.write or bufferedWriter.flush failed");
                  e.printStackTrace();
              }
          }while (false);

          if (null != bufferedWriter){
              try {
                  bufferedWriter.close();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }

          if (null != fileWriter){
              try {
                  fileWriter.close();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
      }

    public static void v(String msg){
        //判断是否为空，为空直接停止下面的不执行
        if (null==msg){
            return;
        }

        String strMsg = "\nmFile"+new Throwable().getStackTrace()[1].getFileName()+
                "\nmClass"+new Throwable().getStackTrace()[1].getClassName()+
                "\nmMethod"+new Throwable().getStackTrace()[1].getMethodName()+
                "\nmLine"+new Throwable().getStackTrace()[1].getLineNumber()+
                "\n" + msg + "\n\n";

        if (0 !=(SHOW_VERBOSE_LOG & m_cLogCatShowLogType)){
            Log.v(TAG,strMsg);
        }

        if (0 !=(SHOW_VERBOSE_LOG & m_cFileSaveLogType)){
            SaveLog2File(strMsg);
        }

    }

    public static void d(String msg){
        //判断是否为空，为空直接停止下面的不执行
        if (null==msg){
            return;
        }

        String strMsg = "\nmFile"+new Throwable().getStackTrace()[1].getFileName()+
                "\nmClass"+new Throwable().getStackTrace()[1].getClassName()+
                "\nmMethod"+new Throwable().getStackTrace()[1].getMethodName()+
                "\nmLine"+new Throwable().getStackTrace()[1].getLineNumber()+
                "\n" + msg + "\n\n";

        if (0 !=(SHOW_DEBUG_LOG & m_cLogCatShowLogType)){
            Log.d(TAG,strMsg);
        }

        if (0 !=(SHOW_DEBUG_LOG & m_cFileSaveLogType)){
            SaveLog2File(strMsg);
        }

    }

    public static void i(String msg){
        //判断是否为空，为空直接停止下面的不执行
        if (null==msg){
            return;
        }

        String strMsg = "\nmFile"+new Throwable().getStackTrace()[1].getFileName()+
                "\nmClass"+new Throwable().getStackTrace()[1].getClassName()+
                "\nmMethod"+new Throwable().getStackTrace()[1].getMethodName()+
                "\nmLine"+new Throwable().getStackTrace()[1].getLineNumber()+
                "\n" + msg + "\n\n";

        if (0 !=(SHOW_INFO_LOG & m_cLogCatShowLogType)){
            Log.i(TAG,strMsg);
        }

        if (0 !=(SHOW_INFO_LOG & m_cFileSaveLogType)){
            SaveLog2File(strMsg);
        }

    }

    public static void w(String msg){
        //判断是否为空，为空直接停止下面的不执行
        if (null==msg){
            return;
        }

        String strMsg = "\nmFile"+new Throwable().getStackTrace()[1].getFileName()+
                "\nmClass"+new Throwable().getStackTrace()[1].getClassName()+
                "\nmMethod"+new Throwable().getStackTrace()[1].getMethodName()+
                "\nmLine"+new Throwable().getStackTrace()[1].getLineNumber()+
                "\n" + msg + "\n\n";

        if (0 !=(SHOW_WRAN_LOG & m_cLogCatShowLogType)){
            Log.w(TAG,strMsg);
        }

        if (0 !=(SHOW_WRAN_LOG & m_cFileSaveLogType)){
            SaveLog2File(strMsg);
        }

    }

    public static void e(String msg){
        //判断是否为空，为空直接停止下面的不执行
        if (null==msg){
            return;
        }

        String strMsg = "\nmFile"+new Throwable().getStackTrace()[1].getFileName()+
                "\nmClass"+new Throwable().getStackTrace()[1].getClassName()+
                "\nmMethod"+new Throwable().getStackTrace()[1].getMethodName()+
                "\nmLine"+new Throwable().getStackTrace()[1].getLineNumber()+
                "\n" + msg + "\n\n";

        if (0 !=(SHOW_ERROR_LOG & m_cLogCatShowLogType)){
            Log.e(TAG,strMsg);
        }

        if (0 !=(SHOW_ERROR_LOG & m_cFileSaveLogType)){
            SaveLog2File(strMsg);
        }

    }
}
