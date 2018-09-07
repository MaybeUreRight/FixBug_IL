package com.xgkj.ilive.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import com.xgkj.ilive.utils.NetEvent;
import com.xgkj.ilive.utils.NetWorkManager;

/**
 * 作者：刘净辉
 * 日期: 2017/8/25 0025 16:19
 * 作用: 通过广播
 */

public class NetBroadcastReceiver extends BroadcastReceiver{



    private NetEvent netEvent;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)){
            int netWorkStatus = NetWorkManager.getNetWorkStatus(context);

            if (netEvent != null)
                netEvent.onNetChange(netWorkStatus);
        }
    }

    public void setNetEvent(NetEvent netEvent) {
        this.netEvent = netEvent;
    }
}
