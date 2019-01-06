package com.njusw.tourtool.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewResId());
        unbinder = ButterKnife.bind(this);
        initView(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

   protected abstract void initView(Bundle savedInstanceState);

    /**
     * setContentView()必须在ButterKnife.bind(this)之前完成
     * 此处获取layout，用于支持setContentView()工作
     * @return activity对应的layout的id
     */
    protected abstract int getContentViewResId() ;
}
