package com.vasys.adapter;

import java.util.List;

import com.vasys.R;
import com.vasys.bean.LogBean;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class LogAdapter extends BaseAdapter{
	private List<LogBean> mLogList = null;
	private Context mContext;
	private LayoutInflater mInflater;
	public LogAdapter(List<LogBean> listAlarm, Context context) {
		// TODO Auto-generated constructor stub
		this.mLogList = listAlarm;
		this.mContext = context;
		mInflater = LayoutInflater.from(context);

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		 return mLogList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mLogList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View v = mInflater.inflate(R.layout.log_item_layout, null);

		TextView action = (TextView) v.findViewById(R.id.log_action);
		action.setText(mLogList.get(position).getAction());// …Ë÷√≤Ÿ◊˜

		TextView logTime = (TextView) v.findViewById(R.id.log_time);
		logTime.setText(mLogList.get(position).getLogTime());// …Ë÷√…„œÒª˙±‡∫≈
		return v;
	}

}
