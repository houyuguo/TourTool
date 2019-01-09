package com.njusw.tourtool.activity;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.EditText;


import com.njusw.tourtool.R;
import com.njusw.tourtool.bean.RResult;
import com.njusw.tourtool.cons.IdiyMessage;
import com.njusw.tourtool.controller.UserController;

import butterknife.ButterKnife;


public class RegistActivity extends BaseActivity  {

	private EditText mNameEt;
	private EditText mPwdEt;
	private EditText mSurePwdEt;

	@Override
	protected void handlerMessage(Message msg) {
		switch (msg.what) {
			case IdiyMessage.REGIST_ACTION_RESULT:
				handleRegistResult((RResult) msg.obj);
				break;
		}
	}

	private void handleRegistResult(RResult resultBean) {
		tip(resultBean.getSuccess().equals("yes")?"注册成功":resultBean.getErrMsg());
		if (resultBean.getSuccess().equals("yes")){
			finish();
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);

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
		return R.layout.register;
	}

	@Override
	protected void initController() {
		mController = new UserController();
		mController.setIModeChangeListener(this);
	}

	@Override
	protected void initUI() {
		mNameEt = (EditText) findViewById(R.id.resetpwd_edit_name);
		mPwdEt = (EditText) findViewById(R.id.resetpwd_edit_pwd_old);
		mSurePwdEt = (EditText) findViewById(R.id.resetpwd_edit_pwd_new);
	}

	public void registClick(View v) {
		String name = mNameEt.getText().toString();
		String pwd = mPwdEt.getText().toString();
		String surePwd = mSurePwdEt.getText().toString();
		if (ifValueWasEmpty(name, pwd, surePwd)) {
			tip("请输入完整的信息!");
			return;
		}
		if (!pwd.equals(surePwd)) {
			tip("两次密码不一致!");
			return;
		}
		// 注册用户
		mController.sendAsyncMessage(IdiyMessage.REGIST_ACTION, name, pwd);
	}

}