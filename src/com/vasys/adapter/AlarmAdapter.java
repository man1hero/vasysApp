package com.vasys.adapter;

import java.lang.ref.SoftReference;
import java.util.List;

import com.vasys.R;
import com.vasys.bean.Alarm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * 报警信息适配器
 * 
 * @date 2015-6-15 10:14:23
 * @author lin
 * 
 */
public class AlarmAdapter extends BaseAdapter {
	private List<Alarm> mAlarmList = null;
	private Context mContext;
	private LayoutInflater mInflater;

	public AlarmAdapter(List<Alarm> listAlarm, Context context) {// 构造函数
		this.mAlarmList = listAlarm;
		this.mContext = context;
		mInflater = LayoutInflater.from(context);

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mAlarmList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mAlarmList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View v=null;
		if(convertView!=null){
			v=convertView;
		}else{
			v = mInflater.inflate(R.layout.alarm_item_layout, null);
		}
		

		TextView alarmId = (TextView) v.findViewById(R.id.alarmid_item);
		alarmId.setText(mAlarmList.get(position).getAlarmId());// 设置文字

		TextView camId = (TextView) v.findViewById(R.id.camid_item);
		camId.setText(mAlarmList.get(position).getCamId());// 设置摄像机编号

		TextView alarmTime = (TextView) v.findViewById(R.id.alarmtime_item);
		alarmTime.setText(mAlarmList.get(position).getAlarmTime().toString());// 设置报警时间
		TextView alarmType = (TextView) v.findViewById(R.id.alarmtype_item);
		alarmType.setText(mAlarmList.get(position).getAlarmType().toString());// 设置报警类型
		TextView duration = (TextView) v.findViewById(R.id.duration_item);
		duration.setText(mAlarmList.get(position).getDuration().toString());// 设置持续时间
		TextView alarmLocation = (TextView) v
				.findViewById(R.id.alarmlocation_item);
		alarmLocation.setText(mAlarmList.get(position).getAlarmLocation());// 设置报警位置

		return v;
	}

}
