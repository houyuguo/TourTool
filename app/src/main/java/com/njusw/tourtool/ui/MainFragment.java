package com.njusw.tourtool.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.njusw.tourtool.R;
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

public class MainFragment extends Fragment {

    private Unbinder unbinder;
    @BindView(R.id.btn_test_get)
    public Button mBtnGet;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        mBtnGet = view.findViewById(R.id.btn_test_get);
//        mBtnGet.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Map<String, String> params = new HashMap<>();
//                try {
//                    params.put("username", URLEncoder.encode("张三丰", "UTF-8"));
//                    params.put("password", "123456");
//                } catch (UnsupportedEncodingException e) {
//                    e.printStackTrace();
//                }
//                HttpRequest.postFormRequest("http://192.168.0.114:8080/TeamApp/user/login", params, new HttpRequest.DataCallBack() {
//                    @Override
//                    public void requestSuccess(String result) throws Exception {
//                        Toast.makeText(getActivity(), result, Toast.LENGTH_SHORT).show();
//                        Log.d("登陆", result);
//                    }
//
//                    @Override
//                    public void requestFailure(Request request, IOException e) {
//                        Toast.makeText(getActivity(), "请求失败", Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//        });
    }

    //测试ButterKnife的事件绑定
    @OnClick(R.id.btn_test_get)
    public void onClick() {
        Toast.makeText(getActivity(), "测试ButterKnife", Toast.LENGTH_SHORT ).show();
    }
}
