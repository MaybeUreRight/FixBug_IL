package com.xgkj.ilive.activity;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.analytics.MobclickAgent;
import com.xgkj.ilive.R;
import com.xgkj.ilive.base.BaseActivity;
import com.xgkj.ilive.mvp.contract.HelpContract;
import com.xgkj.ilive.mvp.presenter.HelpPresenter;
import com.xgkj.ilive.receiver.NetBroadcastReceiver;
import com.xgkj.ilive.utils.AppManager;
import com.xgkj.ilive.utils.NetEvent;

import butterknife.BindView;
import butterknife.OnClick;

public class HelpActivity extends BaseActivity implements HelpContract.View, NetEvent {

    @BindView(R.id.tousu_back)
    ImageView tousu_back;
    @BindView(R.id.tousu_finish)
    TextView tousu_finish;
    @BindView(R.id.tousu_et_content)
    EditText tousu_et_content;

    private HelpPresenter helpPresenter;
    private NetBroadcastReceiver netBroadcastReceiver;
    private int netMobile;

    @Override
    protected void onStart() {
        super.onStart();
        if (netBroadcastReceiver == null) {
            netBroadcastReceiver = new NetBroadcastReceiver();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
            registerReceiver(netBroadcastReceiver,intentFilter);

            netBroadcastReceiver.setNetEvent(this);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_help;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        AppManager.getAppManager().addActivity(this);

        helpPresenter = new HelpPresenter(this);
    }

    @OnClick({R.id.tousu_back,R.id.tousu_finish})
    public void  onClick(View view){
        switch (view.getId()){
            case R.id.tousu_back:
                onBackPressed();
                break;
            case R.id.tousu_finish:
                String content = tousu_et_content.getText().toString();

                if (netMobile == 0 || netMobile == 1) {
                    helpPresenter.commitSuggest(content);
                }else if (netMobile == -1){
                    Toast.makeText(this, R.string.network_none_connection, Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    public void onNetChange(int netMobile) {
        this.netMobile = netMobile;
    }

    @Override
    protected void onDestroy() {
        if (netBroadcastReceiver !=null){
            unregisterReceiver(netBroadcastReceiver);
        }
        super.onDestroy();
    }
}
