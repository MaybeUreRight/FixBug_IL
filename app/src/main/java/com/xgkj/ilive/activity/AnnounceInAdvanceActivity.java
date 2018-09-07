package com.xgkj.ilive.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.analytics.MobclickAgent;
import com.xgkj.ilive.R;
import com.xgkj.ilive.base.BaseActivity;
import com.xgkj.ilive.mvp.contract.AnnounceInAdvanceContract;
import com.xgkj.ilive.mvp.model.LookStatusModel;
import com.xgkj.ilive.mvp.presenter.AnnounceInAdvancePresenter;
import com.xgkj.ilive.utils.AppManager;
import com.xgkj.ilive.utils.BitmapUtils;
import com.xgkj.ilive.utils.PhotoUtil;

import org.feezu.liuli.timeselector.TimeSelector;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class AnnounceInAdvanceActivity extends BaseActivity implements AnnounceInAdvanceContract.View {

    private static final int PHOTO_REQUEST_CUT = 3;
    @BindView(R.id.announce_advance)
    LinearLayout announce_advance;
    @BindView(R.id.upload_front_cover_layout)
    RelativeLayout upload_front_cover_layout;
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
    @BindView(R.id.activity_introduced)
    EditText activity_introduced;
    @BindView(R.id.release_notice)
    Button release_notice;

    private String[] look_status = {"公开", "付费", "加密"};
    private String[] look_description = {"所有人均可观看", "需要先付费才可观看", "需要先验证密码才可观看"};

    private AnnounceInAdvancePresenter announceInAdvancePresenter;
    private TextView title_finished1;
    private PopupWindow popupWindow2;
    private int index;
    private TextView title_finished;
    private TextView many_font;
    private EditText activity_title_content;
    private String lookWay;
    private String photoData;
    private PhotoUtil photoUtil;
    /**
     * 请求权限的处理
     */
    private static final int CAMERA_WRITE_READ_CODE = 1;
    private static final int SETTING_TITLE =3;
    private static final int WHO_IS_LOOK =3;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_announce_in_advance;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        AppManager.getAppManager().addActivity(this);
        announceInAdvancePresenter = new AnnounceInAdvancePresenter(this);
        MobclickAgent.setCatchUncaughtExceptions(true);
        //实现上传封面的功能
        photoUtil = new PhotoUtil(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED
                ||ActivityCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE},CAMERA_WRITE_READ_CODE);
        }else {
            if (photoUtil!=null)
                photoUtil.showDialog();
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
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_WRITE_READ_CODE){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (photoUtil!=null)
                    photoUtil.showDialog();
            }
        }
    }

    @OnClick({R.id.upload_front_cover_layout, R.id.anchor_back, R.id.upload_front_cover, R.id.settings_title, R.id.appointment_time, R.id.who_is_look,R.id.release_notice})
    public void onDownClick(View view) {
        switch (view.getId()) {
            case R.id.anchor_back:
                onBackPressed();
                break;
            case R.id.upload_front_cover_layout:
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
            case R.id.settings_title:
                startActivityForResult(new Intent(this,SetTitleActivity.class),SETTING_TITLE);
                break;
            //活动时间
            case R.id.appointment_time:
                Date date = new Date();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
                String format1 = format.format(date);
                TimeSelector timeSelector = new TimeSelector(this, new TimeSelector.ResultHandler() {
                    @Override
                    public void handle(String time) {
                        appointment_time.setText(time);
                    }
                }, format1, "2050-1-1 24:00");
                timeSelector.show();
                break;
            case R.id.who_is_look:
                startActivityForResult(new Intent(this,WhoMayLookActivity.class),WHO_IS_LOOK);
                break;
            case R.id.release_notice:
                String title = settings_title.getText().toString();
                String time = appointment_time.getText().toString();
                String look = who_is_look.getText().toString();
                if (TextUtils.isEmpty(photoData)){
                    Toast.makeText(this, "上传封面不能为空!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if ("未设置".equals(title) || TextUtils.isEmpty(title)){
                    Toast.makeText(this, "活动标题不能为空!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if ("未设置".equals(time) || TextUtils.isEmpty(time)){
                    Toast.makeText(this, "活动时间不能为空!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if ("未设置".equals(look) || TextUtils.isEmpty(look)){
                    Toast.makeText(this, "谁可以看不能为空!", Toast.LENGTH_SHORT).show();
                    return;
                }
                announceInAdvancePresenter.createAnnounceInAdvance(photoData,title,time,index,look);
                break;
        }
    }

    /**
     * 设置观看方式
     * @param s
     */
    public void setLookWay(String s) {
        lookWay = s;
        Log.e("lookWay",lookWay);
    }



    class TitleEditChangedListener implements TextWatcher

    {

        /**
         * 输入文本前的变化
         *
         * @param s
         * @param start
         * @param count
         * @param after
         */
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        /**
         * 输入文本中的变化
         *
         * @param s
         * @param start
         * @param before
         * @param count
         */
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        /**
         * 输入文本后的变化
         *
         * @param s
         */
        @Override
        public void afterTextChanged(Editable s) {

            if (s.length() <= 30) {
                int i = 30 - s.length();
                many_font.setText("还剩" + i + "字可以输入");
                many_font.setTextColor(Color.BLACK);
            } else {
                many_font.setText("字数超过限制");
                many_font.setTextColor(Color.RED);
            }
        }
    }

    /**
     * 初始化状态数据
     *
     * @return
     */
    private List<LookStatusModel> initData() {
        List<LookStatusModel> lookStatusModels = new ArrayList<>();
        for (int i = 0; i < look_status.length; i++) {
            lookStatusModels.add(new LookStatusModel(false, look_status[i], look_description[i]));
        }
        return lookStatusModels;
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
                startClipActivity(cameraPath);
            }
        }
        // 相机返回
        else if (PhotoUtil.PHOTO_SETRESULT_CODE == requestCode) {
            if (resultCode == RESULT_OK) {
                String photoPath = photoUtil.getPhotoPath();
                Bitmap bitmap = photoUtil.readBitmapAutoSize(photoPath);
                String str = photoUtil.bitmaptoString(bitmap);
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


    public void startClipActivity(String path) {
        Intent intent = new Intent(this, PhotoClipActivity.class);
        intent.putExtra("path", path);
        startActivityForResult(intent, PhotoUtil.PHOTO_CORPRESULT_CODE);
    }


    public void setSelectText(final String s, int mSelectedItem) {
        title_finished1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                who_is_look.setText(s);
                popupWindow2.dismiss();
            }
        });
        index = mSelectedItem + 1;
        Log.e("lookWay",index+"");
    }
}
