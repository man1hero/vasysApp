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
	private AlarmAdapter mAlarmAdapter;// 适配器
	private List<Alarm> mAlarm = new ArrayList<Alarm>();// 报警列表

	Runnable runnable;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View alarmLayout = inflater.inflate(R.layout.alarm_layout, container,
				false);
	
		mMainActivity = (MainActivity) getActivity();
		mFragmentManager = getActivity().getFragmentManager();
		mAlarmListView = (RefreshListView) alarmLayout.findViewById(R.id.listview_alarm);// 报警列表信息

		mAlarmAdapter = new AlarmAdapter(mAlarm, mMainActivity);
		mAlarmListView.setAdapter(mAlarmAdapter);//
		mAlarmListView.setOnRefreshListener(this);

		mAlarmListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if(mAlarm.get(position).getAlarmType().toString().equals("smoke")){
					Toast.makeText(mMainActivity, mAlarm.get(position).getAlarmTime().toString()+" "+mAlarm.get(position).getAlarmLocation().toString()+"发生烟雾报警",
							Toast.LENGTH_SHORT).show();
				}else{
					Toast.makeText(mMainActivity, mAlarm.get(position).getAlarmTime().toString()+" "+mAlarm.get(position).getAlarmLocation().toString()+"发生火灾报警",
							Toast.LENGTH_SHORT).show();
				}
				
			}

		});
		runnable=new Runnable() {
			@Override
			public void run() {

				//判断mAlarm是否有数据，没有的话则加载
				if(mAlarm.size()==0){				
					getData();// 获取报警数据	
				}
			}
			};
			runnable.run();
		return alarmLayout;
	}

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	public void getData() {// 获取数据并绑定到listview
		JSONObject json = AlarmService.getAlarm();
		if (json != null) {
			Log.e("JSONObject：", json.toString());
			// 解析json数据填充到ListView
			try {
				JSONArray alarms = new JSONArray(json.getString("rows"));
				Log.e("JSONArray: ", alarms.toString());
				for (int i = 0; i < alarms.length(); i++) {
					Alarm alarm = new Alarm();
					alarm.setAlarmId(alarms.getJSONObject(i).getString(
							"alarmId"));// 报警编号
					alarm.setAlarmLocation(alarms.getJSONObject(i).getString(
							"alarmLocation"));// 报警位置
					//对日期进行格式化
					String date=DateFormat.format(alarms.getJSONObject(i)
							.getString("date").toString());
					
					alarm.setAlarmTime(date);// 设置时间
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

	public void clearData() {// 清空数据
		mAlarm.clear();// 清空报警数据列表中的数据
		Log.e("mAlarmSize:",mAlarm.size()+"");//输出mAlarm大小

	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		MainActivity.currFragTag = Constant.FRAGMENT_FLAG_ALARM;
	}

	/**
	 * 下拉刷新
	 */
	@Override
	public void onDownPullRefresh() {
		// TODO Auto-generated method stub
		
		new AsyncTask<Void, Void, Void>() {

			@Override
			protected Void doInBackground(Void... params) {
				SystemClock.sleep(2000);
				Log.e("onDownPullRefresh","下拉刷新");
				
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
	 * 加载更多
	 */
	@Override
	public void onLoadingMore() {
		// TODO Auto-generated method stub
		new AsyncTask<Void, Void, Void>() {

			@Override
			protected Void doInBackground(Void... params) {
				SystemClock.sleep(3000);
				Log.e("onLoadingMore","加载更多");
				
				return null;
			}

			@Override
			protected void onPostExecute(Void result) {
				mAlarmAdapter.notifyDataSetChanged();

				// 控制脚布局隐藏
				mAlarmListView.hideFooterView();
			
			}
		}.execute(new Void[] {});
	}

}
