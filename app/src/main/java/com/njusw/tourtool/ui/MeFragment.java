package com.njusw.tourtool.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.leon.lib.settingview.LSettingItem;
import com.njusw.tourtool.R;
import com.njusw.tourtool.activity.MyInfoActivity;
import com.njusw.tourtool.activity.MyMapActivity;
import com.njusw.tourtool.utils.net.HttpRequest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Request;

public class MeFragment extends Fragment {

    private Unbinder unbinder;
    @BindView(R.id.item_myInfo)
    public LSettingItem litem;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me, container, false);
        ButterKnife.bind(this, view);


        litem.setmOnLSettingItemClick(new LSettingItem.OnLSettingItemClick() {
            @Override
            public void click() {

                Intent intent = new Intent();
                intent.setClass(getActivity(),
                        MyInfoActivity.class);
                startActivity(intent);

            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

//    @OnClick(R.id.item_myInfo)
//    public void onClick_myInfo() {
//        Toast.makeText(getActivity(), "测试按钮", Toast.LENGTH_SHORT).show();
//    }
}
