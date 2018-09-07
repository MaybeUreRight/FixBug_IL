package com.xgkj.ilive.activity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okrx.RxAdapter;
import com.umeng.analytics.MobclickAgent;
import com.xgkj.ilive.R;
import com.xgkj.ilive.base.BaseActivity;
import com.xgkj.ilive.log.LogUtils;
import com.xgkj.ilive.mvp.contract.PersonalProfileContract;
import com.xgkj.ilive.mvp.model.MineModel;
import com.xgkj.ilive.mvp.model.UpdateInfoModel;
import com.xgkj.ilive.mvp.presenter.PersonalProfilePresenter;
import com.xgkj.ilive.net.NetUrl;
import com.xgkj.ilive.utils.AppManager;
import com.xgkj.ilive.utils.GsonUtils;
import com.xgkj.ilive.utils.SharedPreferencesUtil;
import com.xgkj.ilive.view.CircleImageView;
import com.zhy.autolayout.AutoLinearLayout;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * 个人资料
 */
public class PersonalProfileActivity extends BaseActivity implements PersonalProfileContract.View {

    private static final int PHOTO_CODE = 1;
    private static final int LOCAL_PHOTO_CODE = 2;

    @BindView(R.id.personal_setting)
    LinearLayout personal_setting;
    @BindView(R.id.persion_back)
    ImageView persion_back;
    @BindView(R.id.user_icon)
    CircleImageView user_icon;
    @BindView(R.id.get_app_cache)
    TextView get_app_cache;
    @BindView(R.id.personal_sex)
    TextView personal_sex;
    @BindView(R.id.tv_industry)
    TextView tv_industry;

    private PersonalProfilePresenter personalProfilePresenter;
    private static final int INDUSTRY_TAG = 3;
    private int sex;
    private String id;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_personal_profile;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        AppManager.getAppManager().addActivity(this);

        MobclickAgent.setCatchUncaughtExceptions(true);

        personalProfilePresenter = new PersonalProfilePresenter(this);
        personalProfilePresenter.getUserInfo();
    }

    @OnClick({R.id.persion_back, R.id.user_icon, R.id.tv_industry, R.id.personal_sex, R.id.get_app_cache})
    public void persionClick(View view) {
        switch (view.getId()) {
            case R.id.persion_back:
                onBackPressed();
                updateInfo();
                break;
            case R.id.user_icon:
                final PopupWindow popupWindow = new PopupWindow();
                popupWindow.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
                popupWindow.setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
                View view1 = View.inflate(this, R.layout.mine_icon_upload, null);

                View blank = view1.findViewById(R.id.blank);
                blank.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
                TextView tv_photograph = (TextView) view1.findViewById(R.id.tv_photograph);
                TextView local_pictures = (TextView) view1.findViewById(R.id.local_pictures);
                TextView tv_cancel = (TextView) view1.findViewById(R.id.tv_cancel);

                popupWindow.setContentView(view1);
                popupWindow.setOutsideTouchable(true);
                popupWindow.setBackgroundDrawable(new BitmapDrawable());
                popupWindow.showAsDropDown(view, Gravity.BOTTOM, 0, 0);

                //拍照上传头像
                tv_photograph.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent1, PHOTO_CODE);
                        popupWindow.dismiss();
                    }
                });

                //选择本地相册进行上传头像
                local_pictures.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(Intent.ACTION_PICK);
                        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                        startActivityForResult(intent, LOCAL_PHOTO_CODE);

                        popupWindow.dismiss();
                    }
                });

                tv_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popupWindow.dismiss();
                    }
                });
                break;
            case R.id.tv_industry:
                startActivityForResult(new Intent(this, IndustryActivity.class), INDUSTRY_TAG);
                break;
            case R.id.personal_sex:
                View view2 = View.inflate(this, R.layout.personal_sex_item, null);
                final TextView man = (TextView) view2.findViewById(R.id.man);
                final TextView woman = (TextView) view2.findViewById(R.id.woman);
                TextView man_cancel = (TextView) view2.findViewById(R.id.cancel);
                View blank2 = view2.findViewById(R.id.blank);

                final PopupWindow popupWindow1 = new PopupWindow(this);
                popupWindow1.setContentView(view2);
                popupWindow1.setOutsideTouchable(true);
                popupWindow1.setBackgroundDrawable(new BitmapDrawable());
                popupWindow1.setWidth(AutoLinearLayout.LayoutParams.MATCH_PARENT);
                popupWindow1.setHeight(AutoLinearLayout.LayoutParams.MATCH_PARENT);
                popupWindow1.showAsDropDown(view, Gravity.BOTTOM, 0, 0);

                blank2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow1.dismiss();
                    }
                });

                man.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        sex = 1;
                        personal_sex.setText(man.getText().toString());
                        popupWindow1.dismiss();
                    }
                });

                woman.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        sex = 2;
                        personal_sex.setText(woman.getText().toString());
                        popupWindow1.dismiss();
                    }
                });

                man_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popupWindow1.dismiss();
                    }
                });

                break;
            case R.id.get_app_cache:
                final PopupWindow popupWindow2 = new PopupWindow(this);
                View view3 = View.inflate(this, R.layout.pop_nickname, null);
                TextView title_finished = (TextView) view3.findViewById(R.id.title_finished);
                View blank3 = view3.findViewById(R.id.blank);
                final EditText activity_title_content = (EditText) view3.findViewById(R.id.activity_title_content);
                popupWindow2.setContentView(view3);
                popupWindow2.setOutsideTouchable(true);
                popupWindow2.setBackgroundDrawable(new BitmapDrawable());
                popupWindow2.setFocusable(true);
                popupWindow2.setWidth(AutoLinearLayout.LayoutParams.MATCH_PARENT);
                popupWindow2.setHeight(AutoLinearLayout.LayoutParams.MATCH_PARENT);
                popupWindow2.showAsDropDown(view, Gravity.BOTTOM, 0, 0);

                blank3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow2.dismiss();
                    }
                });

                title_finished.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String s = activity_title_content.getText().toString();
                        if (TextUtils.isEmpty(s)) {
                            Toast.makeText(PersonalProfileActivity.this, "昵称不能为空!", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        get_app_cache.setText(s);
                        popupWindow2.dismiss();
                    }
                });
                break;
        }
    }

    @Override
    public void onBackPressed() {
        updateInfo();
        SystemClock.sleep(1000);
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        updateInfo();
        SystemClock.sleep(1000);
        super.onDestroy();
    }

    private void updateInfo() {
        Map<String, String> infoMap = new HashMap<>();
        infoMap.put("sex", String.valueOf(sex));
        infoMap.put("nickname", get_app_cache.getText().toString());
        infoMap.put("industry", id);
        infoMap.put("pic", photoData);

        JSONObject jobj = GsonUtils.upJson(infoMap);

        Subscription subscription = OkGo.post(NetUrl.UPDATE_INFO + SharedPreferencesUtil.getAccessToken(this, "access_token"))
                .upJson(jobj)
                .getCall(StringConvert.create(), RxAdapter.<String>create())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {

                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        UpdateInfoModel updateInfoModel = GsonUtils.jsonToBean(s, UpdateInfoModel.class);
                        UpdateInfoModel.APIDATABean apidata = updateInfoModel.getAPIDATA();
                        int code = apidata.getCode();
                        if (code == 200) {
                            Toast.makeText(PersonalProfileActivity.this, "个人资料修改成功!", Toast.LENGTH_SHORT).show();
                        } else {
                            String msg = apidata.getMsg();
                            Toast.makeText(PersonalProfileActivity.this, msg, Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        MobclickAgent.reportError(PersonalProfileActivity.this, throwable.toString());
                    }
                });
    }

    @Override
    public void getUserInfoFinished(MineModel.APIDATABean.RetBean ret) {
        Glide.with(this).load(ret.getPic()).asBitmap().centerCrop().error(R.drawable.mine_circle_icon).placeholder(R.drawable.mine_circle_icon).into(user_icon);
        get_app_cache.setText(ret.getNickname());
        String industry = ret.getIndustry();
        if ("".equals(industry) || industry == null) {
            tv_industry.setText("未设置");
        } else {
            tv_industry.setText(industry);
        }

        String sex = ret.getSex();
        if ("".equals(sex) || sex == null) {
            personal_sex.setText("未设置");
        } else {
            personal_sex.setText(sex);
        }

    }

    @Override
    public void getUserData(String photoData) {
        this.photoData = photoData;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PHOTO_CODE:
                personalProfilePresenter.updateUserIcon(data);
                break;
            case LOCAL_PHOTO_CODE:
                if (data != null) {
                    selectLocalPhoto(data);
                }
                break;
            case INDUSTRY_TAG:
                if (data != null) {
                    id = data.getStringExtra("id");
                    String title = data.getStringExtra("title");
                    tv_industry.setText(title);
                }
                break;
        }
    }

    private String photoData;

    /**
     * 选择本地图片相册
     *
     * @param data
     */
    private void selectLocalPhoto(Intent data) {
        try {
            Uri data1 = data.getData();
            String[] images = {MediaStore.Images.Media.DATA};
            Cursor cursor = managedQuery(data1, images, null, null, null);
            int columnIndexOrThrow = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            String cursorString = cursor.getString(columnIndexOrThrow);
            Bitmap bitmap = personalProfilePresenter.getBitmap(cursorString, 200, 480);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte[] bytes = baos.toByteArray();
            System.out.println("图片的大小：" + bytes.length);
            String photo = Base64.encodeToString(bytes, 0, bytes.length, Base64.DEFAULT);
            photoData = photo;
            personalProfilePresenter.updateLocalPicture(photo);
        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
            LogUtils.e(e.toString());
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
}
