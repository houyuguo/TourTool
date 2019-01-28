package com.njusw.tourtool.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.njusw.tourtool.R;
import com.njusw.tourtool.activity.AddTourActivity;
import com.njusw.tourtool.activity.MapActivity;
import com.njusw.tourtool.activity.MyMapActivity;
import com.njusw.tourtool.adapter.MyConditionSimpleAdapter;
import com.njusw.tourtool.adapter.MyPlaceSimpleAdapter;
import com.njusw.tourtool.bean.Result;
import com.njusw.tourtool.bean.TeamInfo;
import com.njusw.tourtool.bean.TourInfo;
import com.njusw.tourtool.bean.TourPlace;
import com.njusw.tourtool.utils.ActivityUtil;
import com.njusw.tourtool.utils.net.HttpRequest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Request;

public class MessageFragment extends Fragment {

    private Unbinder unbinder;
    @BindView(R.id.btn_test_message)
    public Button mBtnGet;

    @BindView(R.id.lv_message)
    public ListView lv;

    private Handler mHandler;

    List<Map<String, Object>> list;
    MyConditionSimpleAdapter adapter;
    List<TeamInfo> teaminfolist = null;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        QueryMycondition();

        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch(msg.what) {
                    case 1:
                        Result res= JSON.parseObject((msg.obj).toString(), Result.class);
                        teaminfolist = (List<TeamInfo>) JSONArray.parseArray(res.getResult(), TeamInfo.class);
                        setViews();
                        break;
                    default:
                        break;
                }


            }
        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    //测试ButterKnife的事件绑定
    @OnClick(R.id.btn_test_message)
    public void onClick_add() {

        Map<String, String> params = new HashMap<>();
        try {

            params.put("leaderUsername", URLEncoder.encode("张三", "UTF-8"));
            params.put("leaderId", URLEncoder.encode("1", "UTF-8"));
            params.put("activityId", URLEncoder.encode("1", "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        HttpRequest.postFormRequest("http://192.168.0.105:8080/TeamApp/user/checkteamcondition", params, new HttpRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Toast.makeText(getActivity(), "测试成功："+result, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void requestFailure(Request request, IOException e) {
                Toast.makeText(getActivity(), "测试失败", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @OnClick(R.id.btn_baidumap)
    public void onClick_baidumap() {
        Intent intent = new Intent();
        intent.setClass(getActivity(),
                MyMapActivity.class);
        startActivity(intent);

    }

    @OnClick(R.id.btn_qunliao)
    public void onClick_qunliao() {


    }

    @OnClick(R.id.btn_query_myCondition)
    public void onClick_accept() {
        QueryMycondition();

    }


    private void QueryMycondition(){

        Map<String, String> params = new HashMap<>();
        try {
            params.put("leaderUsername", URLEncoder.encode("张三", "UTF-8"));
            params.put("leaderId", URLEncoder.encode("1", "UTF-8"));
            params.put("activityId", URLEncoder.encode("1", "UTF-8"));

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        HttpRequest.postFormRequest("http://192.168.0.105:8080/TeamApp/user/checkteamcondition", params, new HttpRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Message msg = new Message();
                msg.what = 1;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }

            @Override
            public void requestFailure(Request request, IOException e) {
                Toast.makeText(getActivity(), "请求失败", Toast.LENGTH_SHORT).show();
            }
        });


    }


    private void setViews() {

        list = getDatas();
        try {
            adapter = new MyConditionSimpleAdapter(getActivity(), list,
                    R.layout.mycondition_list_item, new String[] { "memberUsername",
                    "beizhu"},
                    new int[] {
                            R.id.tv_membername,
                            R.id.tv_beizhu,});
            lv.setAdapter(adapter);
        } catch (Exception ex) {

        }
    }


    private List<Map<String, Object>> getDatas() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
//            List<TeamInfo> teaminfolist = teaminfolist;
            for (int i = 0; i < teaminfolist.size(); i++) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("memberUsername", teaminfolist.get(i).getMemberUsername());
                list.add(map);
            }
        } catch (Exception e) {

        }
        return list;
    }
}
