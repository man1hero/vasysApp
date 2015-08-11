package com.vasys.activity;

import com.vasys.R;
import com.vasys.util.Constant;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TableRow;

/**
 * 设置Activity
 * @author lin
 *
 */
public class SettingActivity extends Activity{
	private static final String[] rows={"10","15","20"};
	private TableRow serverUrlRow;
	private Spinner perRowsSpinner;//设置每页条目数
	private Button settingOk;//确认修改
	private ImageView back;
	private ArrayAdapter<String> rowsAdapter;
	View myServerView;
	private EditText serverEdit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.setting_activity);
		 
		init();
	}
	
	//初始化UI
	public void init(){
		perRowsSpinner=(Spinner)findViewById(R.id.spinner_per_rows);
		settingOk=(Button)findViewById(R.id.setting_ok);
		serverUrlRow=(TableRow)findViewById(R.id.setting_serverUrl);
		back=(ImageView)findViewById(R.id.setting_activity_back);
		
		settingOk.setOnClickListener(btnOkListener);
		back.setOnClickListener(backListener);
		serverUrlRow.setOnClickListener(serverUrlListener);
		
		rowsAdapter=new ArrayAdapter<String>(this, android.R.layout.preference_category,rows);
		rowsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		perRowsSpinner.setAdapter(rowsAdapter);
	}
	
	//点击保存
	private OnClickListener btnOkListener=new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Constant.setSERVER_URL(serverEdit.getText().toString());
			finish();
			
		}
	};
	
	private OnClickListener serverUrlListener=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			//弹出对话框
			setServer();
			serverEdit=(EditText)myServerView.findViewById(R.id.server_edit);
			serverEdit.setText(Constant.SERVER_URL);

			Dialog alertDialog = new AlertDialog.Builder(SettingActivity.this).
				    setTitle("设置服务器地址").
				    setView(myServerView).
				    setPositiveButton("确定", new DialogInterface.OnClickListener() {

				     @Override
				     public void onClick(DialogInterface dialog, int which) {
				    	 dialog.dismiss();
				     }
				    }).
				    create();
				  alertDialog.show();

		}
	};
	
	public void setServer(){
		LayoutInflater layoutInflater = LayoutInflater.from(this);
		  myServerView = layoutInflater.inflate(R.layout.server_dialog, (ViewGroup)findViewById(R.id.server_dialog));
	}
	private OnClickListener backListener=new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			finish();
			
		}
	};
	


}
