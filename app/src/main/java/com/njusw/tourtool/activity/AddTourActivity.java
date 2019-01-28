package com.njusw.tourtool.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

public class AddTourActivity extends BaseActivity{

    @BindView(R.id.ET_TourTitle)
    public EditText ET_TourTitle;
    @BindView(R.id.ET_TourPlace)
    public EditText ET_TourPlace;
    @BindView(R.id.ET_TourTime)
    public EditText ET_TourTime;
    @BindView(R.id.ET_TourNumber)
    public EditText ET_TourNumber;
    @BindView(R.id.ET_TourDescribe)
    public EditText ET_TourDescribe;

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
        return R.layout.activity_addtour;
    }

    @OnClick(R.id.Btn_addtour)
    public void onClick_addpTour() {
//      Toast.makeText(getActivity(), "测试出游信息", Toast.LENGTH_SHORT ).show();
        Map<String, String> params = new HashMap<>();
        try {
            params.put("id", "1");
            params.put("username", URLEncoder.encode("张三丰", "UTF-8"));
            params.put("toWhere", URLEncoder.encode(ET_TourPlace.getText().toString(), "UTF-8"));

            //我要获取当前的日期
            Date date = new Date();
            //设置要获取到什么样的时间
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //获取String类型的时间
            String createdate = sdf.format(date);

            params.put("startTime", createdate);
            params.put("peopleNum", ET_TourNumber.getText().toString());
            params.put("descInfo", URLEncoder.encode(ET_TourDescribe.getText().toString(), "UTF-8"));

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        HttpRequest.postFormRequest("http://192.168.0.114:8080/TeamApp/user/addtravelinfo", params, new HttpRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
//                Toast.makeText(this, "测试成功："+result, Toast.LENGTH_SHORT).show();
                tip("发布成功"+result);
            }

            @Override
            public void requestFailure(Request request, IOException e) {
//               Toast.makeText(this, "测试失败", Toast.LENGTH_SHORT).show();
                tip("请求失败");
            }
        });
    }
}
