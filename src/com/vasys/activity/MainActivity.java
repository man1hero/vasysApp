package com.vasys.activity;

import cn.jpush.android.api.JPushInterface;

import com.vasys.R;
import com.vasys.fragment.BaseFragment;
import com.vasys.fragment.BottomControlPanel;
import com.vasys.fragment.BottomControlPanel.BottomPanelCallback;
import com.vasys.fragment.HeadControlPanel;
import com.vasys.util.Constant;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * ������Activity
 * 
 * @author lin
 * @date 2015��6��9��09:37:21
 */
public class MainActivity extends Activity implements BottomPanelCallback {
	BottomControlPanel bottomPanel = null;// �ײ��˵���
	HeadControlPanel headPanel = null;// �����˵���
	private FragmentManager fragmentManager = null;
	private FragmentTransaction fragmentTransaction = null;
	public static String currFragTag = "";

	private PopupWindow mPopupWindow;//�����˵�
	private View mPopView;
	private TextView app_cancle;
	private TextView app_exit;
	private TextView app_change;
	private RelativeLayout buttomBarGroup;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
		initUI();//��ʼ��UI
		findView();
		init();// ��ʼ��������ť��
		fragmentManager = getFragmentManager();
		setDefaultFirstFragment(Constant.FRAGMENT_FLAG_CAM);

	}

	private void findView() {
		mPopView = LayoutInflater.from(this).inflate(R.layout.app_exit, null);
		buttomBarGroup = (RelativeLayout) findViewById(R.id.root_layout);
		app_cancle = (TextView) mPopView.findViewById(R.id.app_cancle);
		app_change = (TextView) mPopView.findViewById(R.id.app_change_user);
		app_exit = (TextView) mPopView.findViewById(R.id.app_exit);
	}
	// ��ʼ��������ť��
	private void init() {

		mPopupWindow = new PopupWindow(mPopView, LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT, true);

		app_cancle.setOnClickListener(new OnClickListener() {// ���ȡ����ť
					@Override
					public void onClick(View v) {
						mPopupWindow.dismiss();
					}
				});

		app_change.setOnClickListener(new OnClickListener() {// ���ע���û����˳�����¼����
					@Override
					public void onClick(View v) {
						AlertDialog.Builder builder = new Builder(
								MainActivity.this);
						builder.setTitle("ע����ʾ")
								.setMessage("ע������Ҫ���µ�¼���Ƿ������")
								.setPositiveButton("ȷ��",
										new DialogInterface.OnClickListener() {
											@Override
											public void onClick(
													DialogInterface dialog,
													int which) {
												dialog.dismiss();
												Intent intent = new Intent(MainActivity.this,
														LoginActivity.class);
												startActivity(intent);
												((Activity) MainActivity.this)
														.overridePendingTransition(R.anim.activity_up,
																R.anim.fade_out);
												finish();
											}
										}).setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
									           public void onClick(DialogInterface dialog, int id) {
									                dialog.cancel();
									           }
									       }).create().show();
						
					}
				});

		app_exit.setOnClickListener(new OnClickListener() {// ����˳�Ӧ�ó���
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new Builder(
						MainActivity.this);
				builder.setTitle("�˳���ʾ")
						.setMessage("�Ƿ��˳�Ӧ�ã�")
						.setPositiveButton("ȷ��",
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(
											DialogInterface dialog,
											int which) {
										dialog.dismiss();
										LoginActivity.loginActivity.finish();
										finish();
									}
								}).setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
							           public void onClick(DialogInterface dialog, int id) {
							                dialog.cancel();
							           }
							       }).create().show();
	
			}
		});
	}

	public boolean onCreateOptionMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;

	}

	/**
	 * ���Menu�˵������˵���
	 */
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_MENU) {
			mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color
					.parseColor("#b0000000")));
			mPopupWindow.showAtLocation(buttomBarGroup, Gravity.BOTTOM, 0, 0);
			mPopupWindow.setAnimationStyle(R.style.app_pop);
			mPopupWindow.setOutsideTouchable(true);
			mPopupWindow.setFocusable(true);
			mPopupWindow.update();
		}
		return super.onKeyDown(keyCode, event);

	}

	private void initUI() {// ��ʼ��UI
		bottomPanel = (BottomControlPanel) findViewById(R.id.bottom_layout);
		if (bottomPanel != null) {
			bottomPanel.initBottomPanel();
			bottomPanel.setBottomCallback(this);
		}
		headPanel = (HeadControlPanel) findViewById(R.id.head_layout);
		if (headPanel != null) {
			headPanel.initHeadPanel();
		}
	}

	// ����BottomControlPanel�Ļص�
	public void onBottomPanelClick(int itemId) {
		String tag = "";
		if ((itemId & Constant.BTN_FLAG_MESSAGE) != 0) {
			tag = Constant.FRAGMENT_FLAG_CAM;
		} else if ((itemId & Constant.BTN_FLAG_CONTACTS) != 0) {
			tag = Constant.FRAGMENT_FLAG_ALARM;
		} else if ((itemId & Constant.BTN_FLAG_NEWS) != 0) {
			tag = Constant.FRAGMENT_FLAG_REPORT;
		} else if ((itemId & Constant.BTN_FLAG_SETTINGS) != 0) {
			tag = Constant.FRAGMENT_FLAG_SETTINGS;
		}
		setTabSelection(tag); // �л�Fragment
		headPanel.setMiddleTitle(tag);// �л�����
	}

	private void setDefaultFirstFragment(String tag) {
		Log.i("vasys", "setDefaultFirstFragment enter... currFragTag = "
				+ currFragTag);
		setTabSelection(tag);
		bottomPanel.defaultBtnChecked();
		Log.i("yan", "setDefaultFirstFragment exit...");
	}

	private void commitTransactions(String tag) {
		if (fragmentTransaction != null && !fragmentTransaction.isEmpty()) {
			fragmentTransaction.commit();
			currFragTag = tag;
			fragmentTransaction = null;
		}
	}

	private FragmentTransaction ensureTransaction() {
		if (fragmentTransaction == null) {
			fragmentTransaction = fragmentManager.beginTransaction();
			fragmentTransaction
					.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

		}
		return fragmentTransaction;
	}

	private void attachFragment(int layout, Fragment f, String tag) {
		if (f != null) {
			if (f.isDetached()) {
				ensureTransaction();
				fragmentTransaction.attach(f);

			} else if (!f.isAdded()) {
				ensureTransaction();
				fragmentTransaction.add(layout, f, tag);
			}
		}
	}

	private Fragment getFragment(String tag) { // ��ȡfragment

		Fragment f = fragmentManager.findFragmentByTag(tag);
		if (f == null) {
	
			f = BaseFragment.newInstance(getApplicationContext(), tag);
		}
		return f;

	}

	private void detachFragment(Fragment f) {

		if (f != null && !f.isDetached()) {
			ensureTransaction();
			fragmentTransaction.detach(f);
		}
	}

	/**
	 * �л�fragment
	 * 
	 * @param tag
	 */
	private void switchFragment(String tag) {
		if (TextUtils.equals(tag, currFragTag)) {
			return;
		}
		// ����һ��fragment detach��
		if (currFragTag != null && !currFragTag.equals("")) {
			detachFragment(getFragment(currFragTag));
		}
		attachFragment(R.id.fragment_content, getFragment(tag), tag);
		commitTransactions(tag);
	}

	/**
	 * ����ѡ�е�Tag
	 * 
	 * @param tag
	 */
	public void setTabSelection(String tag) {
		// ����һ��Fragment����
		fragmentTransaction = fragmentManager.beginTransaction();
		/*
		 * if(TextUtils.equals(tag, Constant.FRAGMENT_FLAG_MESSAGE)){ if
		 * (messageFragment == null) { messageFragment = new MessageFragment();
		 * }
		 * 
		 * }else if(TextUtils.equals(tag, Constant.FRAGMENT_FLAG_CONTACTS)){ if
		 * (contactsFragment == null) { contactsFragment = new
		 * ContactsFragment(); }
		 * 
		 * }else if(TextUtils.equals(tag, Constant.FRAGMENT_FLAG_NEWS)){ if
		 * (newsFragment == null) { newsFragment = new NewsFragment(); }
		 * 
		 * }else if(TextUtils.equals(tag,Constant.FRAGMENT_FLAG_SETTING)){ if
		 * (settingFragment == null) { settingFragment = new SettingFragment();
		 * } }else if(TextUtils.equals(tag, Constant.FRAGMENT_FLAG_SIMPLE)){ if
		 * (simpleFragment == null) { simpleFragment = new SimpleFragment(); }
		 * 
		 * }
		 */
		switchFragment(tag);

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		 JPushInterface.onResume(this);
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		JPushInterface.onPause(this);
	}
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		currFragTag = "";
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
	}
	@Override
	protected void onDestroy() {
	super.onDestroy();
	//System.exit(0);
	//�����������ַ�ʽ
	//android.os.Process.killProcess(android.os.Process.myPid()); 
	}

}
