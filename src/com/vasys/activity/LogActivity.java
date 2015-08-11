package com.vasys.activity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.vasys.R;
import com.vasys.adapter.LogAdapter;
import com.vasys.bean.LogBean;
import com.vasys.util.Constant;
import com.vasys.util.DateFormat;
import com.vasys.webservice.LogService;
import android.util.Log;


import android.app.Activity;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;
/**
 * 操作日志查看
 * @author lin
 *
 */
public class LogActivity extends Activity{

	private ListView mLogListView;// ListView
	private LogAdapter mLogAdapter;// 适配器
	private List<LogBean> logList = new ArrayList<LogBean>();// 报警列表
	private ImageView backImg;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.log);
		mLogListView = (ListView) findViewById(R.id.listview_log);// 报警列表信息
		backImg=(ImageView)findViewById(R.id.log_back);//返回图标
		backImg.setOnClickListener(backListener);
		mLogAdapter = new LogAdapter(logList, this);
		mLogListView.setAdapter(mLogAdapter);//设置adapter
		
		//新开一个线程加载数据
		Runnable runnable=new Runnable() {
			@Override
			public void run() {

				//判断mAlarm是否有数据，没有的话则加载
				if(logList.size()==0){
					getData();// 获取报警数据
				}
			}
			};
			runnable.run();
	}
	
	// 获取数据并绑定到listview
	public void getData() {
		JSONObject json = LogService.getLog(Constant.USER);
		if (json != null) {
			
			// 解析json数据填充到ListView
			try {
				JSONArray logs = new JSONArray(json.getString("rows"));
				Log.e("JSONArray: ", logs.toString());
				for (int i = 0; i < logs.length(); i++) {
					LogBean log = new LogBean();
					log.setAction(logs.getJSONObject(i).getString("action"));//操作记录
					//对日期格式数据进行格式化，去掉最后的.0
					String date=DateFormat.format(logs.getJSONObject(i).getString("date"));
					log.setLogTime(date);
					
					logList.add(log);
				}
			} catch (JSONException e) {
				// TODO: handle exception
				Log.e("JSON Error: ", e.toString());
			}
		}
	}
	
	//返回图标监听
	private OnClickListener backListener =new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			finish();
		}
	};
}
