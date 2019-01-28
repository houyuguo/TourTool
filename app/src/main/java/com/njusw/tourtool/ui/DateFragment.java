package com.njusw.tourtool.ui;

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
import com.njusw.tourtool.activity.AddPlaceActivity;
import com.njusw.tourtool.activity.AddTourActivity;
import com.njusw.tourtool.adapter.MyPlaceSimpleAdapter;
import com.njusw.tourtool.adapter.MyTourSimpleAdapter;
import com.njusw.tourtool.bean.Result;
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

public class DateFragment extends Fragment {

    private Unbinder unbinder;
    @BindView(R.id.btn_test_issue)
    public Button mBtnGet;

    @BindView(R.id.h_list_view)
    public ListView lv;

    private Handler mHandler;

    List<Map<String, Object>> list;
    MyPlaceSimpleAdapter adapter;
    List<TourPlace> placelist = null;

    List<Map<String, Object>> list2;
    MyTourSimpleAdapter adapter2;
    List<TourInfo> tourelist = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        QueryMytour();

        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                //处理Msg
                switch(msg.what) {
                    case 1:
//                        Toast.makeText(getActivity(), "测试Handler："+msg.obj, Toast.LENGTH_SHORT).show();
                        Result placeres= JSON.parseObject((msg.obj).toString(), Result.class);
                        placelist = (List<TourPlace>) JSONArray.parseArray(placeres.getResult(), TourPlace.class);
                        setViews();
                        break;
                    case 2:
//                        Toast.makeText(getActivity(), "测试Handler："+msg.obj, Toast.LENGTH_SHORT).show();
                        Result tourres= JSON.parseObject((msg.obj).toString(), Result.class);
                        tourelist = (List<TourInfo>) JSONArray.parseArray(tourres.getResult(), TourInfo.class);
                        setTourViews();
                    default:
                        break;
                }


            }
        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_date, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }




    @OnClick(R.id.btn_test_addplace)
    public void onClick_addplace() {
        Map<String, String> params = new HashMap<>();
        try {

            params.put("username", URLEncoder.encode("张三丰", "UTF-8"));

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        HttpRequest.postFormRequest("http://192.168.0.105:8080/TeamApp/user/showowntravelinfo", params, new HttpRequest.DataCallBack() {
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

    @OnClick(R.id.btn_addplace)
    public void onClick() {
        ActivityUtil.start(getActivity(), AddPlaceActivity.class, false);

    }

    @OnClick(R.id.btn_test_issue)
    public void onClick_add() {
        ActivityUtil.start(getActivity(), AddTourActivity.class, false);
    }

    @OnClick(R.id.btn_query_myplace)
    public void onClick_query_myplace() {
       QueryMyplace();
    }
    @OnClick(R.id.btn_query_myissue)
    public void onClick_query_mypissue() {
        QueryMytour();
    }

    private void setViews() {

        list = getDatas();
        try {
            adapter = new MyPlaceSimpleAdapter(getActivity(), list,
                    R.layout.myplace_list_item, new String[] { "activityName",
                    "area", "specificLocation"},
                    new int[] {
                            R.id.tv_activityname,
                            R.id.tv_area,
                            R.id.tv_specificLocation });
            lv.setAdapter(adapter);
        } catch (Exception ex) {

        }
    }

    private List<Map<String, Object>> getDatas() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            List<TourPlace> placeList = placelist;

            for (int i = 0; i < placeList.size(); i++) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("id", placeList.get(i).getId());
                map.put("username", placeList.get(i).getUsername());
                map.put("activityName", placeList.get(i).getActivityName());
                map.put("area", placeList.get(i).getArea());
                map.put("specificLocation", placeList.get(i).getSpecificLocation());
                list.add(map);
            }
        } catch (Exception e) {

        }
        return list;
    }

    private void QueryMyplace(){

        Map<String, String> params = new HashMap<>();
        try {
            params.put("username", URLEncoder.encode("张三丰", "UTF-8"));

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        HttpRequest.postFormRequest("http://192.168.0.105:8080/TeamApp/user/querypredefinedtravelinfo", params, new HttpRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
//                Toast.makeText(getActivity(), "测试成功："+result, Toast.LENGTH_SHORT).show();
                Message msg = new Message();
                msg.what = 1;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }

            @Override
            public void requestFailure(Request request, IOException e) {
                Toast.makeText(getActivity(), "请求", Toast.LENGTH_SHORT).show();
            }
        });


    }


    private void QueryMytour(){

        Map<String, String> params = new HashMap<>();
        try {
            params.put("username", URLEncoder.encode("张三丰", "UTF-8"));

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        HttpRequest.postFormRequest("http://192.168.0.105:8080/TeamApp/user/showowntravelinfo", params, new HttpRequest.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Message msg = new Message();
                msg.what = 2;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }

            @Override
            public void requestFailure(Request request, IOException e) {
                Toast.makeText(getActivity(), "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setTourViews() {

        list2 = getTourDatas();
        try {
            adapter2 = new MyTourSimpleAdapter(getActivity(), list2,
                    R.layout.mytour_list_item, new String[] { "toWhere",
                    "startTime", "peopleNum","descInfo"},
                    new int[] {
                            R.id.tv_myTour_toWhere,
                            R.id.tv_myTour_startTime,
                            R.id.tv_myTour_peopleNum,
                            R.id.tv_myTour_descInfo
                    });
            lv.setAdapter(adapter2);
        } catch (Exception ex) {

        }
    }


    private List<Map<String, Object>> getTourDatas() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {

            for (int i = 0; i < tourelist.size(); i++) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("id", tourelist.get(i).getId());
                map.put("username", tourelist.get(i).getUsername());
                map.put("toWhere", tourelist.get(i).getToWhere());
                map.put("startTime", tourelist.get(i).getStartTime());
                map.put("peopleNum", tourelist.get(i).getPeopleNum());
                map.put("process", tourelist.get(i).getProcess());
                map.put("descInfo", tourelist.get(i).getDescInfo());
                map.put("releaseTime", tourelist.get(i).getReleaseTime());
                map.put("usernameId", tourelist.get(i).getUsernameId());
                list.add(map);
            }
        } catch (Exception e) {

        }
        return list;
    }





}
