package com.xgkj.ilive.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;
import android.util.Base64;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.umeng.analytics.MobclickAgent;
import com.xgkj.ilive.R;
import com.xgkj.ilive.activity.BrowseRecordsActivity;
import com.xgkj.ilive.activity.MineAttentionActivity;
import com.xgkj.ilive.activity.MineLiveActivity;
import com.xgkj.ilive.activity.SettingsActivity;
import com.xgkj.ilive.app.App;
import com.xgkj.ilive.base.BaseFragment;
import com.xgkj.ilive.log.LogUtils;
import com.xgkj.ilive.mvp.contract.MineContract;
import com.xgkj.ilive.mvp.model.MineModel;
import com.xgkj.ilive.mvp.presenter.MinePresenter;
import com.xgkj.ilive.view.CircleImageView;
import com.zhy.autolayout.AutoLinearLayout;

import java.io.ByteArrayOutputStream;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者：刘净辉
 * 日期: 2017/7/13 0013 16:54
 * 作用: 我的模块
 */

public class MineFragment extends BaseFragment implements MineContract.View {

    private static final int PHOTO_CODE = 1;
    private static final int LOCAL_PHOTO_CODE = 2;

    @BindView(R.id.mine_layout)
    LinearLayout mine_layout;
    @BindView(R.id.mine_round_circle)
    CircleImageView mine_round_circle;
    @BindView(R.id.tv_nickname)
    TextView tv_nickname;
    @BindView(R.id.tv_company)
    TextView tv_company;
    @BindView(R.id.company_profile)
    TextView company_profile;
    @BindView(R.id.mine_attention)
    AutoLinearLayout mine_attention;
    @BindView(R.id.mine_browse_records)
    AutoLinearLayout mine_browse_records;
    @BindView(R.id.mine_live)
    AutoLinearLayout mine_live;
    @BindView(R.id.mine_settings)
    AutoLinearLayout mine_settings;
    private MinePresenter minePresenter;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        MobclickAgent.setCatchUncaughtExceptions(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (PermissionChecker.checkCallingOrSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            }
        }

        minePresenter = new MinePresenter(this, getActivity());
        minePresenter.getUserInfo();

    }


    @OnClick({R.id.mine_round_circle, R.id.mine_attention, R.id.mine_browse_records, R.id.mine_live, R.id.mine_settings})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mine_round_circle:
                final PopupWindow popupWindow = new PopupWindow();
                View view1 = View.inflate(getActivity(), R.layout.mine_icon_upload, null);
                TextView tv_photograph = (TextView) view1.findViewById(R.id.tv_photograph);
                TextView local_pictures = (TextView) view1.findViewById(R.id.local_pictures);
                TextView tv_cancel = (TextView) view1.findViewById(R.id.tv_cancel);
                View blank = view1.findViewById(R.id.blank);
                blank.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });

                popupWindow.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
                popupWindow.setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
                popupWindow.setContentView(view1);
                popupWindow.setOutsideTouchable(true);
                popupWindow.setBackgroundDrawable(new BitmapDrawable());
                popupWindow.showAsDropDown(view, Gravity.BOTTOM, 0, 0);

                //拍照监听
                tv_photograph.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent1, PHOTO_CODE);

                        popupWindow.dismiss();
                    }
                });

                //本地相册的监听
                local_pictures.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_PICK);
                        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                        startActivityForResult(intent, LOCAL_PHOTO_CODE);

                        popupWindow.dismiss();
                    }
                });

                //取消监听
                tv_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popupWindow.dismiss();
                    }
                });
                break;
            case R.id.mine_attention:
                minePresenter.startActivity(MineAttentionActivity.class);
                break;
            case R.id.mine_browse_records:
                minePresenter.startActivity(BrowseRecordsActivity.class);
                break;
            case R.id.mine_live:
                minePresenter.startActivity(MineLiveActivity.class);
                break;
            case R.id.mine_settings:
                minePresenter.startActivity(SettingsActivity.class);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PHOTO_CODE:
                //上传拍照的图片
                minePresenter.updateUserIcon(data);
                break;
            case LOCAL_PHOTO_CODE:
                if (data != null) {
                    selectLocalPhoto(data);
                }
                break;

        }
    }

    /**
     * 选择本地图片相册
     *
     * @param data
     */
    private void selectLocalPhoto(Intent data) {
        try {
            Uri data1 = data.getData();
            String[] images = {MediaStore.Images.Media.DATA};
            Cursor cursor = getActivity().managedQuery(data1, images, null, null, null);
            int columnIndexOrThrow = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            String cursorString = cursor.getString(columnIndexOrThrow);
            Bitmap bitmap = minePresenter.getBitmap(cursorString, 200, 480);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte[] bytes = baos.toByteArray();
            System.out.println("图片的大小：" + bytes.length);
            String photo = Base64.encodeToString(bytes, 0, bytes.length, Base64.DEFAULT);
            minePresenter.updateLocalPicture(photo);
        } catch (Exception e) {
            Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
            LogUtils.e(e.toString());
        }
    }

    @Override
    public void getUserInfoFinished(MineModel.APIDATABean.RetBean ret) {
        int bind_company = ret.getBind_company();
        if (bind_company == 0) {
            tv_company.setVisibility(View.GONE);
            company_profile.setVisibility(View.GONE);
        } else if (bind_company == 1) {
            tv_company.setVisibility(View.VISIBLE);
            company_profile.setVisibility(View.VISIBLE);
            tv_company.setText("公司认证:\t" + ret.getCompany_title());
            company_profile.setText("公司介绍:" + ret.getCompany_abbr());
//            String company_id = ret.getCompany_id();
//            minePresenter.getCompanyInfo(company_id);
        }
        tv_nickname.setText(ret.getNickname());
        Glide.with(getActivity())
                .asBitmap()
                .load(ret.getPic())
                .apply(App.requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL).centerCrop().placeholder(R.drawable.mine_circle_icon).error(R.drawable.mine_circle_icon))
                .into(mine_round_circle);
    }

    @Override
    public CircleImageView getCicleImageViewWidget() {
        return mine_round_circle;
    }
}
