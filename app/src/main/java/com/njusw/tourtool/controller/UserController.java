package com.njusw.tourtool.controller;

import com.alibaba.fastjson.JSON;

import com.njusw.tourtool.bean.RResult;
import com.njusw.tourtool.cons.IdiyMessage;
import com.njusw.tourtool.cons.NetworkConst;
import com.njusw.tourtool.utils.NetworkUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;


public class UserController extends BaseController {

	@Override
	protected void handleMessage(int action, Object... values) {
		switch (action) {
			case IdiyMessage.LOGIN_ACTION:
				// 登录的请求 doPost doGet
				RResult rResult = loginOrRegist(NetworkConst.LOGIN_URL,
						(String) values[0], (String) values[1]);
				// 跟Activity说 数据加载完毕了
				mListener.onModeChanged(IdiyMessage.LOGIN_ACTION_RESULT, rResult);
				break;
			case IdiyMessage.REGIST_ACTION:
				RResult loginOrRegist = loginOrRegist(NetworkConst.REGIST_URL, (String) values[0],
						(String) values[1]);
				mListener.onModeChanged(IdiyMessage.REGIST_ACTION_RESULT, loginOrRegist);
				break;
		}
	}

	private RResult loginOrRegist(String url, String name, String pwd) {
		HashMap<String, String> params = new HashMap<String, String>();

		try {
			params.put("username", URLEncoder.encode(name, "UTF-8"));
			params.put("password",pwd);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String jsonStr = NetworkUtil.doPost(url, params);

//		HttpRequest.postFormRequest(url,params, new HttpRequest.DataCallBack() {
//			@Override
//			public void requestSuccess(String result) throws Exception {
//				Toast.makeText(getActivity(),result,Toast.LENGTH_SHORT).show();
//				Log.d("登陆", result);
//
//
//			}
//
//			@Override
//			public void requestFailure(Request request, IOException e) {
//				Toast.makeText(getActivity(),"请求失败",Toast.LENGTH_SHORT).show();
//
//			}
//		});

		RResult res= JSON.parseObject(jsonStr, RResult.class);
		return res;
	}

}
