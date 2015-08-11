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
 * ������־�鿴
 * @author lin
 *
 */
public class LogActivity extends Activity{

	private ListView mLogListView;// ListView
	private LogAdapter mLogAdapter;// ������
	private List<LogBean> logList = new ArrayList<LogBean>();// �����б�
	private ImageView backImg;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.log);
		mLogListView = (ListView) findViewById(R.id.listview_log);// �����б���Ϣ
		backImg=(ImageView)findViewById(R.id.log_back);//����ͼ��
		backImg.setOnClickListener(backListener);
		mLogAdapter = new LogAdapter(logList, this);
		mLogListView.setAdapter(mLogAdapter);//����adapter
		
		//�¿�һ���̼߳�������
		Runnable runnable=new Runnable() {
			@Override
			public void run() {

				//�ж�mAlarm�Ƿ������ݣ�û�еĻ������
				if(logList.size()==0){
					getData();// ��ȡ��������
				}
			}
			};
			runnable.run();
	}
	
	// ��ȡ���ݲ��󶨵�listview
	public void getData() {
		JSONObject json = LogService.getLog(Constant.USER);
		if (json != null) {
			
			// ����json������䵽ListView
			try {
				JSONArray logs = new JSONArray(json.getString("rows"));
				Log.e("JSONArray: ", logs.toString());
				for (int i = 0; i < logs.length(); i++) {
					LogBean log = new LogBean();
					log.setAction(logs.getJSONObject(i).getString("action"));//������¼
					//�����ڸ�ʽ���ݽ��и�ʽ����ȥ������.0
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
	
	//����ͼ�����
	private OnClickListener backListener =new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			finish();
		}
	};
}
