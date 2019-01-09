package com.njusw.tourtool.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.Window;
import android.widget.Toast;


import com.njusw.tourtool.controller.BaseController;
import com.njusw.tourtool.listener.IModeChangeListener;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends FragmentActivity implements
        IModeChangeListener {

    private Unbinder unbinder;

    protected BaseController mController;
    protected Handler mHandler = new Handler() {

        public void handleMessage(Message msg) {
            handlerMessage(msg);
        }

    };

    protected void handlerMessage(Message msg) {
        // default Empty implementn
    }

    protected void initController() {
        // default Empty implementn
    }

    protected abstract void initUI();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getContentViewResId());
        unbinder = ButterKnife.bind(this);
        initView(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @SuppressLint("WrongConstant")
    public void tip(String tipStr) {
        Toast.makeText(this, tipStr, 0).show();
    }

    @Override
    public void onModeChanged(int action, Object... values) {
        mHandler.obtainMessage(action, values[0]).sendToTarget();
    }

    protected boolean ifValueWasEmpty(String... values) {
        for (String value : values) {
            if (TextUtils.isEmpty(value)) {
                return true;
            }
        }
        return false;
    }

    protected abstract void initView(Bundle savedInstanceState);

    /**
     * setContentView()必须在ButterKnife.bind(this)之前完成
     * 此处获取layout，用于支持setContentView()工作
     * @return activity对应的layout的id
     */
    protected abstract int getContentViewResId() ;


}

