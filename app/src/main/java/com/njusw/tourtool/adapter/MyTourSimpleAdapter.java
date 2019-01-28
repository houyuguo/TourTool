package com.njusw.tourtool.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.njusw.tourtool.R;

import java.util.List;
import java.util.Map;

public class MyTourSimpleAdapter extends SimpleAdapter {
	/*需要绑定的控件资源id*/
	private int[] mTo;
	/*map集合关键字数组*/
	private String[] mFrom;
	/*需要绑定的数据*/
	private List<? extends Map<String, ?>> mData;

	private LayoutInflater mInflater;
	Context context = null;

	public MyTourSimpleAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
		super(context, data, resource, from, to);
		mTo = to;
		mFrom = from;
		mData = data;
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.context= context;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		/*第一次装载这个view时=null,就新建一个调用inflate宣誓一个view*/
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.mytour_list_item, null);
			holder = new ViewHolder();
			try {
				/*绑定该view各个控件*/
				holder.tv_myTour_toWhere = (TextView)convertView.findViewById(R.id.tv_myTour_toWhere);
				holder.tv_myTour_startTime = (TextView)convertView.findViewById(R.id.tv_myTour_startTime);
				holder.tv_myTour_peopleNum = (TextView)convertView.findViewById(R.id.tv_myTour_peopleNum);
				holder.tv_myTour_descInfo = (TextView)convertView.findViewById(R.id.tv_myTour_descInfo);

			} catch(Exception ex) {}
			/*标记这个view*/
			convertView.setTag(holder);
		}else{
			/*直接取出标记的view*/
			holder = (ViewHolder)convertView.getTag();
		}
		/*设置各个控件的展示内容*/
		holder.tv_myTour_toWhere.setText("目的地：" + mData.get(position).get("toWhere").toString());
		holder.tv_myTour_startTime.setText("出游时间：" + mData.get(position).get("startTime").toString());
		holder.tv_myTour_peopleNum.setText("预计人数：" + mData.get(position).get("peopleNum").toString());
		holder.tv_myTour_descInfo.setText("具体描述：" + mData.get(position).get("descInfo").toString());
		/*返回修改好的view*/
		return convertView;
	}

	static class ViewHolder{
		TextView tv_myTour_toWhere;
		TextView tv_myTour_startTime;
		TextView tv_myTour_peopleNum;
		TextView tv_myTour_descInfo;
	}
}

