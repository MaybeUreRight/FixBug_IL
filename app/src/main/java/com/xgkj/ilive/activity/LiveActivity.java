package com.xgkj.ilive.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;
import com.xgkj.ilive.R;
import com.xgkj.ilive.base.BaseActivity;
import com.xgkj.ilive.mvp.contract.LiveContract;
import com.xgkj.ilive.mvp.presenter.LivePresenter;
import com.xgkj.ilive.utils.AppManager;
import com.xgkj.ilive.utils.BitmapUtils;
import com.xgkj.ilive.utils.PhotoUtil;
import com.zhy.autolayout.AutoLinearLayout;

import java.io.ByteArrayOutputStream;

import butterknife.BindView;
import butterknife.OnClick;

public class LiveActivity extends BaseActivity implements LiveContract.View{


    @BindView(R.id.announce_advance)
    AutoLinearLayout announce_advance;
    @BindView(R.id.anchor_back)
    ImageView anchor_back;
    @BindView(R.id.upload_front_cover)
    ImageView upload_front_cover;
    @BindView(R.id.settings_cover)
    TextView settings_cover;
    @BindView(R.id.settings_title)
    TextView settings_title;
    @BindView(R.id.appointment_time)
    TextView appointment_time;
    @BindView(R.id.who_is_look)
    TextView who_is_look;
    @BindView(R.id.add_title) //活动介绍
     TextView add_title;
    @BindView(R.id.start_live)
    Button start_live;
    @BindView(R.id.activity_introduced)
    EditText activity_introduced;

    private String photoData ;

    private int index;
    private LivePresenter livePresenter;
    private String lookWay;
    private PhotoUtil photoUtil;

    private static final int SETTING_TITLE =3;
    private static final int WHO_IS_LOOK =3;
    /**
     * 请求权限的处理
     */
    private static final int CAMERA_WRITE_READ_CODE = 1;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_live;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

        AppManager.getAppManager().addActivity(this);
        livePresenter = new LivePresenter(this);
      //  MobclickAgent.setCatchUncaughtExceptions(true);

        //实现上传封面的功能
        photoUtil = new PhotoUtil(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED
                ||ActivityCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE},CAMERA_WRITE_READ_CODE);
        }else {
            photoUtil.showDialog();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_WRITE_READ_CODE){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                photoUtil.showDialog();
            }
        }
    }

    @OnClick({R.id.anchor_back, R.id.upload_front_cover, R.id.settings_title, R.id.who_is_look,R.id.add_title,R.id.start_live})
    public void onDownClick(View view) {
        switch (view.getId()) {
            case R.id.anchor_back:
                onBackPressed();
                break;
            //设置封面的方法
            case R.id.upload_front_cover:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN &&
                        ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED
                        || ActivityCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED
                        || ActivityCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE},CAMERA_WRITE_READ_CODE);
                }else {
                    if (photoUtil!=null) {
                        photoUtil.showDialog();
                    }
                }
                break;
            //设置标题
            case R.id.settings_title:
                startActivityForResult(new Intent(this,SetTitleActivity.class),SETTING_TITLE);
                break;

            case R.id.who_is_look:
                startActivityForResult(new Intent(this,WhoMayLookActivity.class),WHO_IS_LOOK);
                break;
            case R.id.start_live:
//                //活动标题
                String activityTitle = settings_title.getText().toString();
                String activity_introduced1 = activity_introduced.getText().toString();
                String look = who_is_look.getText().toString();
                livePresenter.createLive(photoData,activityTitle,index,activity_introduced1,look);
                break;
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // 相册返回
        if (PhotoUtil.CAMRA_SETRESULT_CODE == requestCode) {
            if (resultCode == RESULT_OK) {
                // 相册选中图片路径
                String cameraPath = photoUtil.getCameraPath(data);
                Bitmap bitmap = photoUtil.readBitmapAutoSize(cameraPath);
                upload_front_cover.setImageBitmap(bitmap);
                String str = photoUtil.bitmaptoString(bitmap);
                settings_cover.setVisibility(View.GONE);
                startClipActivity(cameraPath);
            }
        }
        // 相机返回
        else if (PhotoUtil.PHOTO_SETRESULT_CODE == requestCode) {
            if (resultCode == RESULT_OK) {
                String photoPath = photoUtil.getPhotoPath();
                Bitmap bitmap = photoUtil.readBitmapAutoSize(photoPath);
                String str = photoUtil.bitmaptoString(bitmap);
                settings_cover.setVisibility(View.GONE);
                upload_front_cover.setImageBitmap(bitmap);
                startClipActivity(photoPath);
            }
        }
        // 裁剪返回
        else if (PhotoUtil.PHOTO_CORPRESULT_CODE == requestCode) {
            if (resultCode == RESULT_OK) {
                settings_cover.setVisibility(View.GONE);
                String path = data.getStringExtra("path");
                BitmapUtils bitmapUtils = new BitmapUtils(getApplicationContext());
                Bitmap bitmap = bitmapUtils.decodeFile(path);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                byte[] bytes = baos.toByteArray();
                String photo_data = Base64.encodeToString(bytes, 0, bytes.length, Base64.DEFAULT);
                this.photoData = photo_data;
                upload_front_cover.setImageBitmap(bitmap);

            }
        }else if (SETTING_TITLE == requestCode&& resultCode ==1){
            String title = data.getStringExtra("title");
            if (title != null){
                settings_title.setText(title);
            }
        }else if (WHO_IS_LOOK == requestCode && resultCode == 2){
            String selectWay = data.getStringExtra("selectWay");
            String lookWay = data.getStringExtra("lookWay");
            int index = data.getIntExtra("index",1);
            this.lookWay = lookWay;
            this.index = index;
            who_is_look.setText(selectWay);
        }
    }


    /**
     * @param path
     */
    public void startClipActivity(String path) {
        Intent intent = new Intent(this, PhotoClipActivity.class);
        intent.putExtra("path", path);
        startActivityForResult(intent, PhotoUtil.PHOTO_CORPRESULT_CODE);
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
}
