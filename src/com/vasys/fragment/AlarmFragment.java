package com.vasys.fragment;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.vasys.R;
import com.vasys.Interface.OnRefreshListener;
import com.vasys.activity.MainActivity;
import com.vasys.adapter.AlarmAdapter;
import com.vasys.bean.Alarm;
import com.vasys.util.Constant;
import com.vasys.util.DateFormat;
import com.vasys.util.RefreshListView;
import com.vasys.webservice.AlarmService;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

/**
 * 
 * @author lin
 * 
 */
public class AlarmFragment extends BaseFragment implements OnRefreshListener{
	private MainActivity mMainActivity;
	private RefreshListView mAlarmListView;// ListView
	private AlarmAdapter mAlarmAdapter;// ������
	private List<Alarm> mAlarm = new ArrayList<Alarm>();// �����б�

	Runnable runnable;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View alarmLayout = inflater.inflate(R.layout.alarm_layout, container,
				false);
	
		mMainActivity = (MainActivity) getActivity();
		mFragmentManager = getActivity().getFragmentManager();
		mAlarmListView = (RefreshListView) alarmLayout.findViewById(R.id.listview_alarm);// �����б���Ϣ

		mAlarmAdapter = new AlarmAdapter(mAlarm, mMainActivity);
		mAlarmListView.setAdapter(mAlarmAdapter);//
		mAlarmListView.setOnRefreshListener(this);

		mAlarmListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if(mAlarm.get(position).getAlarmType().toString().equals("smoke")){
					Toast.makeText(mMainActivity, mAlarm.get(position).getAlarmTime().toString()+" "+mAlarm.get(position).getAlarmLocation().toString()+"����������",
							Toast.LENGTH_SHORT).show();
				}else{
					Toast.makeText(mMainActivity, mAlarm.get(position).getAlarmTime().toString()+" "+mAlarm.get(position).getAlarmLocation().toString()+"�������ֱ���",
							Toast.LENGTH_SHORT).show();
				}
				
			}

		});
		runnable=new Runnable() {
			@Override
			public void run() {

				//�ж�mAlarm�Ƿ������ݣ�û�еĻ������
				if(mAlarm.size()==0){				
					getData();// ��ȡ��������	
				}
			}
			};
			runnable.run();
		return alarmLayout;
	}

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	public void getData() {// ��ȡ���ݲ��󶨵�listview
		JSONObject json = AlarmService.getAlarm();
		if (json != null) {
			Log.e("JSONObject��", json.toString());
			// ����json������䵽ListView
			try {
				JSONArray alarms = new JSONArray(json.getString("rows"));
				Log.e("JSONArray: ", alarms.toString());
				for (int i = 0; i < alarms.length(); i++) {
					Alarm alarm = new Alarm();
					alarm.setAlarmId(alarms.getJSONObject(i).getString(
							"alarmId"));// �������
					alarm.setAlarmLocation(alarms.getJSONObject(i).getString(
							"alarmLocation"));// ����λ��
					//�����ڽ��и�ʽ��
					String date=DateFormat.format(alarms.getJSONObject(i)
							.getString("date").toString());
					
					alarm.setAlarmTime(date);// ����ʱ��
					alarm.setAlarmType(alarms.getJSONObject(i).getString(
							"alarmType"));
					alarm.setCamId(alarms.getJSONObject(i).getString("camId"));
					alarm.setDuration(alarms.getJSONObject(i).getString(
							"duration"));
					mAlarm.add(alarm);
				}
			} catch (JSONException e) {
				// TODO: handle exception
				Log.e("JSON Error: ", e.toString());
			}
		}
	}

	public void clearData() {// �������
		mAlarm.clear();// ��ձ��������б��е�����
		Log.e("mAlarmSize:",mAlarm.size()+"");//���mAlarm��С

	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		MainActivity.currFragTag = Constant.FRAGMENT_FLAG_ALARM;
	}

	/**
	 * ����ˢ��
	 */
	@Override
	public void onDownPullRefresh() {
		// TODO Auto-generated method stub
		
		new AsyncTask<Void, Void, Void>() {

			@Override
			protected Void doInBackground(Void... params) {
				SystemClock.sleep(2000);
				Log.e("onDownPullRefresh","����ˢ��");
				
				return null;
			}
			
			@Override
			protected void onPostExecute(Void result) {
				mAlarmAdapter.notifyDataSetChanged();
				mAlarmListView.hideHeaderView();
			}
		}.execute(new Void[] {});
		clearData();
		getData();
	}

	/**
	 * ���ظ���
	 */
	@Override
	public void onLoadingMore() {
		// TODO Auto-generated method stub
		new AsyncTask<Void, Void, Void>() {

			@Override
			protected Void doInBackground(Void... params) {
				SystemClock.sleep(3000);
				Log.e("onLoadingMore","���ظ���");
				
				return null;
			}

			@Override
			protected void onPostExecute(Void result) {
				mAlarmAdapter.notifyDataSetChanged();

				// ���ƽŲ�������
				mAlarmListView.hideFooterView();
			
			}
		}.execute(new Void[] {});
	}

}
