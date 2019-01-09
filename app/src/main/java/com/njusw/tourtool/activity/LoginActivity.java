package com.njusw.tourtool.activity;


import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.EditText;


import com.njusw.tourtool.R;
import com.njusw.tourtool.bean.RResult;
import com.njusw.tourtool.cons.IdiyMessage;
import com.njusw.tourtool.controller.UserController;
import com.njusw.tourtool.utils.ActivityUtil;

import butterknife.ButterKnife;


public class LoginActivity extends BaseActivity {

    private EditText mNameEt;
    private EditText mPwdEt;

    @Override
    protected void handlerMessage(Message msg) {
        switch (msg.what) {
            case IdiyMessage.LOGIN_ACTION_RESULT:
                handleLoginResult(msg);
                break;
        }
    }

    private void handleLoginResult(Message msg) {
        RResult rResult=(RResult) msg.obj;
        if (rResult.getSuccess().equals("yes")) {

            ActivityUtil.start(this, MainActivity.class, true);
        }else {
            tip("登录失败:"+rResult.getErrMsg());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);
        initView(savedInstanceState);

        initController();
        initUI();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected int getContentViewResId() {
        return R.layout.login;
    }

    public void loginClick(View v) {
//        ActivityUtil.start(this, MainActivity.class, true);
        String name = mNameEt.getText().toString();
        String pwd = mPwdEt.getText().toString();
        if (ifValueWasEmpty(name, pwd)) {
            tip("请输入账号密码");
            return;
        }
        // 发送一个网络请求 去请求网络数据
        mController.sendAsyncMessage(IdiyMessage.LOGIN_ACTION, name, pwd);
    }

    public void registClick(View v){
        ActivityUtil.start(this, RegistActivity.class, false);
    }

    @Override
    protected void initController() {
        mController = new UserController();
        mController.setIModeChangeListener(this);
    }

    @Override
    protected void initUI() {
        mNameEt = (EditText) findViewById(R.id.login_edit_account);
        mPwdEt = (EditText) findViewById(R.id.login_edit_pwd);
    }

}
