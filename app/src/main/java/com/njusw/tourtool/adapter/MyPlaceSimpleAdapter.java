package com.njusw.tourtool.adapter;

import java.util.List;  
import java.util.Map;


import android.content.Context;
import android.view.LayoutInflater; 
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter; 
import android.widget.TextView;

import com.njusw.tourtool.R;

public class MyPlaceSimpleAdapter extends SimpleAdapter {
	/*需要绑定的控件资源id*/
	private int[] mTo;
	/*map集合关键字数组*/
	private String[] mFrom;
	/*需要绑定的数据*/
	private List<? extends Map<String, ?>> mData;

	private LayoutInflater mInflater;
	Context context = null;

	public MyPlaceSimpleAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
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
			convertView = mInflater.inflate(R.layout.myplace_list_item, null);
			holder = new ViewHolder();
			try {
				/*绑定该view各个控件*/
				holder.tv_activityname = (TextView)convertView.findViewById(R.id.tv_activityname);
				holder.tv_area = (TextView)convertView.findViewById(R.id.tv_area);
				holder.tv_specificLocation = (TextView)convertView.findViewById(R.id.tv_specificLocation);

			} catch(Exception ex) {}
			/*标记这个view*/
			convertView.setTag(holder);
		}else{
			/*直接取出标记的view*/
			holder = (ViewHolder)convertView.getTag();
		}
		/*设置各个控件的展示内容*/
		holder.tv_activityname.setText("标签：" + mData.get(position).get("activityName").toString());
		holder.tv_area.setText("地区：" + mData.get(position).get("area").toString());
		holder.tv_specificLocation.setText("描述：" + mData.get(position).get("specificLocation").toString());
		/*返回修改好的view*/
		return convertView;
	}

	static class ViewHolder{
		TextView tv_activityname;
		TextView tv_area;
		TextView tv_specificLocation;
	}
}

