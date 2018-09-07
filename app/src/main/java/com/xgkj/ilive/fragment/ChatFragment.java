package com.xgkj.ilive.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.analytics.MobclickAgent;
import com.xgkj.ilive.R;
import com.xgkj.ilive.adapter.CommentAdapter;
import com.xgkj.ilive.base.BaseFragment;
import com.xgkj.ilive.mvp.contract.ChatContract;
import com.xgkj.ilive.mvp.model.ChatModel;
import com.xgkj.ilive.mvp.model.MineModel;
import com.xgkj.ilive.mvp.model.QueryChatMessageModel;
import com.xgkj.ilive.mvp.presenter.ChatPresenter;
import com.xgkj.ilive.utils.emoji.EmojiWidget;
import com.xgkj.ilive.view.DividerItemDecoration;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者：刘净辉
 * 日期: 2017/7/20 0020 19:07
 */

public class ChatFragment extends BaseFragment implements ChatContract.View {

    @BindView(R.id.et_input_message)
    EditText et_input_message;
    @BindView(R.id.image_emojli)
    TextView image_emojli;
    @BindView(R.id.send_share)
    ImageView send_share;
    @BindView(R.id.btn_send)
    Button btn_send;
    @BindView(R.id.emojli_layout)
    AutoLinearLayout emojli_layout;
    @BindView(R.id.emoji_viewpage)
    ViewPager emoji_viewpage;
    @BindView(R.id.emoji_cursor)
    AutoLinearLayout emoji_cursor;
    @BindView(R.id.messageListView)
    RecyclerView messageListView;

    private String cid;
    private ChatPresenter chatPresenter;
    /**
     * 查询消息的标识
     */
    private static final int QUERY_MESSAGE_TAG = 1;
    private  StringBuilder sb = new StringBuilder();
    private final static int ON_EMOJI_CHANGE = 0xc1;
    private EmojiWidget emojiWidget;
    private CommentAdapter commentAdapter;

    public static ChatFragment newInstance(String room_id, String cid) {
        ChatFragment newFragment = new ChatFragment();
        Bundle bundle = new Bundle();
        bundle.putString("room_id", room_id);
        bundle.putString("cid", cid);
        newFragment.setArguments(bundle);
        return newFragment;
    }

    /**
     * 发送消息
     */
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int what = msg.what;
            switch (what){
                case QUERY_MESSAGE_TAG:
                    SystemClock.sleep(1000);
                    //发送完成后进行查询消息进行展示
                    chatPresenter.queryChatMessage(cid);
                    handler.removeMessages(QUERY_MESSAGE_TAG);
                    handler.sendEmptyMessageDelayed(QUERY_MESSAGE_TAG,1000);
                    break;
                case ON_EMOJI_CHANGE:  // 监听表情界面的变化
                    emojiWidget.refreshWidgetUI(msg);
                    break;

            }
        }
    };

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_chat;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        MobclickAgent.setCatchUncaughtExceptions(true);
        Bundle args = getArguments();
        if (args != null) {
            cid = args.getString("cid");
        }
        emojiWidget = new EmojiWidget(emoji_viewpage,emoji_cursor, getActivity(), ON_EMOJI_CHANGE, handler, et_input_message);
        chatPresenter = new ChatPresenter(this, getActivity());
        //添加文本改变监听
        et_input_message.addTextChangedListener(new InputMessage());

        commentAdapter = new CommentAdapter(getActivity());
        handler.sendEmptyMessage(QUERY_MESSAGE_TAG);
        messageListView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        messageListView.addItemDecoration(new DividerItemDecoration(getActivity()));
        messageListView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_HOVER_MOVE:
                    case MotionEvent.ACTION_DOWN:
                        handler.removeMessages(QUERY_MESSAGE_TAG);
                        break;
                    case MotionEvent.ACTION_UP:
                        handler.sendEmptyMessageDelayed(QUERY_MESSAGE_TAG,1000);
                        break;
                }
                return false;
            }
        });
    }

    @OnClick({R.id.image_emojli,R.id.et_input_message,R.id.btn_send})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.image_emojli:
                hideKeyboardAndEmoji();
                if (emojli_layout.getVisibility() == View.GONE) {
                    emojli_layout.setVisibility(View.VISIBLE);
                } else {
                    emojli_layout.setVisibility(View.GONE);
                }
                break;
            case R.id.et_input_message:
                emojli_layout.setVisibility(View.GONE);
                break;
            case R.id.btn_send:
                String input_content = et_input_message.getText().toString();
                Log.e("input_content",input_content);
                if (TextUtils.isEmpty(input_content) || input_content == null ){
                    Toast.makeText(getActivity(), "评论内容不能为空!", Toast.LENGTH_SHORT).show();
                    return;
                }

                chatPresenter.sendMessageContent(cid,input_content);
                handler.sendEmptyMessage(QUERY_MESSAGE_TAG);
                et_input_message.setText("");
                break;
        }
    }

    class InputMessage implements TextWatcher{

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            String content = editable.toString();
            if (content.length()>0){
                btn_send.setEnabled(true);
                btn_send.setClickable(true);
                btn_send.setBackgroundResource(R.drawable.send_btn_checked);
            }else {
                btn_send.setEnabled(false);
                btn_send.setClickable(false);
                btn_send.setBackgroundResource(R.drawable.send_btn_bg);
            }
        }
    }
    @Override
    public void onDestroyView() {
        handler.removeCallbacksAndMessages(null);
        super.onDestroyView();
    }


    @Override
    public void pullMessageHistoryFinished(List<ChatModel.APIDATABean.RetBean.ListBean> list) {

    }


    @Override
    public void getUserInfoFinished(MineModel.APIDATABean.RetBean ret) {

    }

    @Override
    public void queryChatMessageFinished(List<QueryChatMessageModel.APIDATABean.RetBean> ret) {
        Collections.reverse(ret);
        commentAdapter.setData(ret);
        messageListView.setAdapter(commentAdapter);
    }

    //隐藏键盘
    private void hideKeyboardAndEmoji() {
        ((InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

}
