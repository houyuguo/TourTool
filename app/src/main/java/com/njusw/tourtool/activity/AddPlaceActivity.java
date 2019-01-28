package com.njusw.tourtool.activity;

import android.os.Bundle;
import android.widget.EditText;

import com.njusw.tourtool.R;
import com.njusw.tourtool.utils.net.HttpRequest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Request;

public class AddPlaceActivity extends BaseActivity{

    @BindView(R.id.ET_PlaceTag)
    public EditText ET_PlaceTag;

    @BindView(R.id.ET_UserDefinedPlace)
    public EditText ET_UserDefinedPlace;

    @BindView(R.id.ET_PlaceDes)
    public EditText ET_PlaceDes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView()工作在baseActivity中完成，必须在ButterKnife.bind()之前完成

        ButterKnife.bind(this);
        initView(savedInstanceState);
    }

    @Override
    protected void initUI() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_addplace;
    }

    @OnClick(R.id.Btn_AddPlace)
    public void onClick_addpPlace() {
//      Toast.makeText(getActivity(), "测试出游信息", Toast.LENGTH_SHORT ).show();
        Map<String, String> params = new HashMap<>();
        try {
            params.put("username", URLEncoder.encode("张三丰", "UTF-8"));
            params.put("activityName", URLEncoder.encode(ET_PlaceTag.getText().toString(), "UTF-8"));
            params.put("area",URLEncoder.encode(ET_UserDefinedPlace.getText().toString(), "UTF-8"));
            params.put("specificLocation", URLEncoder.encode(ET_PlaceDes.getText().toString(), "UTF-8"));


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        HttpRequest.postFormRequest("http://192.168.0.105:8080/TeamApp/user/addpredefinedtravelinfo", params, new HttpRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
//              Toast.makeText(this, "测试成功："+result, Toast.LENGTH_SHORT).show();
                tip("添加成功"+result);
            }

            @Override
            public void requestFailure(Request request, IOException e) {
//              Toast.makeText(this, "测试失败", Toast.LENGTH_SHORT).show();
                tip("请求失败");
            }
        });
    }
}
