package com.xgkj.ilive.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.xgkj.ilive.R;
import com.xgkj.ilive.base.BaseActivity;
import com.xgkj.ilive.utils.AppManager;

import butterknife.BindView;
import butterknife.OnClick;

public class SetTitleActivity extends BaseActivity {

    @BindView(R.id.title_finished)
    TextView title_finished;
    @BindView(R.id.activity_title_content)
    EditText activity_title_content;
    @BindView(R.id.tv_many_font)
    TextView many_font;

    /**
     * 获取标题
     */
    private static final int ACTIVITY_TITLE = 1;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_title;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        AppManager.getAppManager().addActivity(this);
        activity_title_content.addTextChangedListener(new TitleEditChangedListener());
    }

    @OnClick({R.id.title_finished})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.title_finished:
                String s = activity_title_content.getText().toString();
                if (s.length() <=30){
                    if ("".equals(s) || null == s){
                        Toast.makeText(this, "活动标题不能为空!", Toast.LENGTH_SHORT).show();
                    }else {
                        Log.e("title_finished", "onClick: "+s );
                        Intent intent = new Intent();
                        intent.putExtra("title",s);
                        setResult(ACTIVITY_TITLE,intent);
                        finish();
                    }
                }else {
                    Toast.makeText(this, R.string.font_more_limit, Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
    class TitleEditChangedListener implements TextWatcher {

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
}
