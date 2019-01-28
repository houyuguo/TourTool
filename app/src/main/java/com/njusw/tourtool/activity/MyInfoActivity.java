package com.njusw.tourtool.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.njusw.tourtool.R;
import com.njusw.tourtool.adapter.MyConditionSimpleAdapter;
import com.njusw.tourtool.bean.PersonInfo;
import com.njusw.tourtool.bean.Result;
import com.njusw.tourtool.bean.TeamInfo;
import com.njusw.tourtool.utils.net.HttpRequest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Request;

public class MyInfoActivity extends BaseActivity{

    @BindView(R.id.ET_myInfo_nickname)
    public EditText ET_myInfo_nickname;
    @BindView(R.id.ET_myInfo_age)
    public EditText ET_myInfo_age;
    @BindView(R.id.ET_myInfo_gender)
    public EditText ET_myInfo_gender;
    @BindView(R.id.ET_myInfo_school)
    public EditText ET_myInfo_school;

    PersonInfo personInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView()工作在baseActivity中完成，必须在ButterKnife.bind()之前完成
        ButterKnife.bind(this);
        initView(savedInstanceState);

        QueryMyInfo();

        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch(msg.what) {
                    case 1:
                        Result res= JSON.parseObject((msg.obj).toString(), Result.class);
                        personInfo = JSON.parseObject(res.getResult(), PersonInfo.class);
                        Toast.makeText(MyInfoActivity.this, "查询成功："+msg.obj, Toast.LENGTH_SHORT).show();
                        setViews();
                        break;
                    case 2:
                        finish();
                        break;
                    default:
                        break;
                }


            }
        };
    }

    @Override
    protected void initUI() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_myinfo;
    }


    private void QueryMyInfo() {

        Map<String, String> params = new HashMap<>();
        try {
            params.put("username", URLEncoder.encode("张三丰", "UTF-8"));

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        HttpRequest.postFormRequest("http://192.168.0.105:8080/TeamApp/user/querypersoninfo", params, new HttpRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
//                Toast.makeText(MyInfoActivity.this, "测试成功："+result, Toast.LENGTH_SHORT).show();
                Message msg = new Message();
                msg.what = 1;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }

            @Override
            public void requestFailure(Request request, IOException e) {
                 Toast.makeText(MyInfoActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setViews() {
        ET_myInfo_nickname.setText(personInfo.getUsername().toString());
        ET_myInfo_age.setText(personInfo.getAge().toString());
        if(personInfo.getGender() == 0){
            ET_myInfo_gender.setText("男");
        }else{
            ET_myInfo_gender.setText("女");
        }
        ET_myInfo_school.setText(personInfo.getSchoolArea().toString());
    }

    @OnClick(R.id.Btn_myInfo_update)
    public void onClick_myInfo_update() {

        Map<String, String> params = new HashMap<>();
        try {
            params.put("username", URLEncoder.encode(ET_myInfo_nickname.getText().toString(), "UTF-8"));
            params.put("age", URLEncoder.encode(ET_myInfo_age.getText().toString(), "UTF-8"));
            if(ET_myInfo_gender.getText().toString().equals("男")){
                params.put("gender", URLEncoder.encode("0", "UTF-8"));
            }else{
                params.put("gender", URLEncoder.encode("1", "UTF-8"));
            }
            params.put("schoolArea", URLEncoder.encode(ET_myInfo_school.getText().toString(), "UTF-8"));

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        HttpRequest.postFormRequest("http://192.168.0.105:8080/TeamApp/user/updatepersoninfo", params, new HttpRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Toast.makeText(MyInfoActivity.this, "修改成功："+result, Toast.LENGTH_SHORT).show();
                Message msg = new Message();
                msg.what = 2;
                msg.obj = result;
                mHandler.sendMessage(msg);

            }

            @Override
            public void requestFailure(Request request, IOException e) {
                Toast.makeText(MyInfoActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @OnClick(R.id.Btn_myInfo_return)
    public void onClick_myInfo_return() {
        finish();
    }

}
