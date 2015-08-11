package com.vasys.fragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import com.vasys.R;
import com.vasys.activity.MainActivity;
import com.vasys.adapter.CamAdapter;
import com.vasys.bean.Cam;
import com.vasys.bean.DeviceBean;
import com.vasys.HC.HC_DVRManager;
import com.vasys.util.Constant;
import com.vasys.webservice.CamService;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.hardware.Camera;
import android.opengl.Visibility;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.SurfaceHolder.Callback;
import android.view.ViewGroup.LayoutParams;

import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class CamFragment extends BaseFragment {

	private TextView tv_Loading;
	private SurfaceView sf_VideoMonitor;
	private ImageText popImageText=null;//弹出菜单
	private ImageText captureJpeg=null;//抓图
	private ImageText record=null;//录像
	private ImageText talk=null;//对讲
	private ImageText fullscreen=null;//全屏
	private ImageView arrowUp;//向上的箭头
	private ImageView arrowDown;//向下的箭头
	
	private LinearLayout camControlLinear;//控制摄像机的线性布局
	
	

	Camera camera;
	View camLayout;
	View popupWindowView;
	static Context mMain;
	DeviceBean bean;
	
	private MainActivity mMainActivity;
	private ListView mCamListView;// ListView
	private CamAdapter mCamAdapter;// 适配器
	private List<Cam> mCamList = new ArrayList<Cam>();// 摄像机列表
	Runnable runnable;
	private PopupWindow popupWindow;

	/**
	 * 返回标记
	 */
	private boolean backflag;
	private final StartRenderingReceiver receiver =new StartRenderingReceiver();

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		camLayout = inflater.inflate(R.layout.cam_layout, container,
				false);
		initView();
		
		//设置用于发广播的上下文
		HC_DVRManager.getInstance().setContext(this.getActivity());
		
	
		popImageText=(ImageText)camLayout.findViewById(R.id.popwindow_btn);
		popImageText.setImage(R.drawable.document);
		//popImageText.setText("列表");
		popImageText.setOnClickListener(popClickListener);

		captureJpeg=(ImageText)camLayout.findViewById(R.id.captureJpeg);
		captureJpeg.setImage(R.drawable.capture_jpeg);
		//captureJpeg.setText("抓图");
		captureJpeg.setOnClickListener(new OnClickListener() {//点击抓图之后的相应结果
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//调用截图功能
				HC_DVRManager.getInstance().captureJpegPicture();
			}
		});
		
		record=(ImageText)camLayout.findViewById(R.id.start_record);
		record.setImage(R.drawable.start_record);
		//record.setText("录像");
		
		talk=(ImageText)camLayout.findViewById(R.id.talk);
		talk.setImage(R.drawable.mic);
		//talk.setText("对讲");
		
		fullscreen=(ImageText)camLayout.findViewById(R.id.fullscreen);
		fullscreen.setImage(R.drawable.focus);
		//fullscreen.setText("全屏");
		
		
		return camLayout;
		
	}
	//开始播放相机
	public void startCamera(){
		try{
			camera.setPreviewDisplay(sf_VideoMonitor.getHolder());
			
		}catch(IOException e){
			e.printStackTrace();
		}
		camera.startPreview();
	}
	public  void stopCamera(){
		camera.stopPreview();
		//camera.release();
	}
	
	//开始播放摄像机
	public void startPlay(final DeviceBean bean){
		Log.e("startPlay","开始播放");
		IntentFilter filter=new IntentFilter();
		filter.addAction(HC_DVRManager.ACTION_START_RENDERING);
		filter.addAction(HC_DVRManager.ACTION_DVR_OUTLINE);
		this.getActivity().registerReceiver(receiver,filter);
		
		tv_Loading.setVisibility(View.VISIBLE);
		tv_Loading.setText(getString(R.string.tv_connect_cam));
		if (backflag) {
			backflag = false;
			new Thread() {
				@Override
				public void run() {
					HC_DVRManager.getInstance().setSurfaceHolder(sf_VideoMonitor.getHolder());
					HC_DVRManager.getInstance().realPlay();
				}
			}.start();
		} else {
			new Thread() {
				@Override
				public void run() {
					HC_DVRManager.getInstance().setDeviceBean(bean);
					HC_DVRManager.getInstance().setSurfaceHolder(sf_VideoMonitor.getHolder());
					HC_DVRManager.getInstance().initSDK();
					HC_DVRManager.getInstance().loginDevice();
					HC_DVRManager.getInstance().realPlay();
				}
			}.start();
		}
		}
	/**
	 * 停止播放
	 */
	public void stop(){
			HC_DVRManager.getInstance().stopPlay();
		}

	/**
	 * 初始化界面
	 */
	public void initView(){
		//获取手机分辨率
		DisplayMetrics dm=new DisplayMetrics();
		this.getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
		tv_Loading=(TextView)camLayout.findViewById(R.id.tv_Loading);
		tv_Loading.setVisibility(View.INVISIBLE);//隐藏正在连接摄像头文字
		sf_VideoMonitor=(SurfaceView)camLayout.findViewById(R.id.sf_VideoMonitor);
		
		camControlLinear=(LinearLayout)camLayout.findViewById(R.id.cam_control_linear);
		arrowUp=(ImageView)camLayout.findViewById(R.id.image_arrow_up);
		arrowDown=(ImageView)camLayout.findViewById(R.id.image_arrow_down);
		//点击弹出控制菜单
		arrowUp.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				arrowUp.setVisibility(View.GONE);
				camControlLinear.setVisibility(View.VISIBLE);
				arrowDown.setVisibility(View.VISIBLE);
			}
		});
		
		//点击下拉隐藏控制菜单，显示上拉按钮
		arrowDown.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				arrowDown.setVisibility(View.GONE);
				camControlLinear.setVisibility(View.GONE);
				arrowUp.setVisibility(View.VISIBLE);
				
			}
		});
		
		//设置视频窗口大小
		LayoutParams lp=sf_VideoMonitor.getLayoutParams();
		lp.width=dm.widthPixels-50;
		lp.height=lp.width/9*16;
		sf_VideoMonitor.setLayoutParams(lp);
		tv_Loading.setLayoutParams(lp);
		
		sf_VideoMonitor.getHolder().addCallback(new Callback() {
			
			@Override
			public void surfaceDestroyed(SurfaceHolder arg0) {
				// TODO Auto-generated method stub
				sf_VideoMonitor.destroyDrawingCache();
				camera.stopPreview();
				camera.release();
			}
			
			@Override
			public void surfaceCreated(SurfaceHolder arg0) {
				// TODO Auto-generated method stub
				camera=Camera.open();
				try{
					Camera.Parameters parameters=camera.getParameters();
					if(getActivity().getResources().getConfiguration().orientation!=Configuration.ORIENTATION_LANDSCAPE){
						parameters.set("orientation", "portrait");
						camera.setDisplayOrientation(90);
					}else{
						parameters.set("orientation", "landscape");

					}
					camera.setParameters(parameters);
					camera.setPreviewDisplay(sf_VideoMonitor.getHolder());
					
							
				}catch(IOException e){
					camera.release();
					Log.e("ss",e.getMessage());
				}
			}
			
			@Override
			public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
				// TODO Auto-generated method stub
				
			}
		});
	
	}

	
	private void getData(){
		JSONArray cams = CamService.getCam();
		if (cams != null) {
			Log.e("cams：", cams.toString());
			// 解析json数据填充到ListView
			try {
				
				for (int i = 0; i < cams.length(); i++) {
					Cam cam = new Cam();
					cam.setCamId(cams.getJSONObject(i).getString(
							"camId"));// 报警编号
					cam.setCamIp(cams.getJSONObject(i).getString(
							"camIp"));// 报警位置
					Log.e("camIp", cam.getCamIp());
					cam.setCamLocation(cams.getJSONObject(i).getString(
							"camLocation"));
					cam.setCamFunction(cams.getJSONObject(i).getString("camFunction"));
					
					mCamList.add(cam);
				
				}
			} catch (JSONException e) {
				// TODO: handle exception
				Log.e("JSON Error: ", e.toString());
			}
	}
	}
	//广播接收器
	private class StartRenderingReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context arg0, Intent intent) {
			// TODO Auto-generated method stub
			if (HC_DVRManager.ACTION_START_RENDERING.equals(intent.getAction())) {
				tv_Loading.setVisibility(View.GONE);
			}
			if (HC_DVRManager.ACTION_DVR_OUTLINE.equals(intent.getAction())) {
				tv_Loading.setText(getString(R.string.tv_connect_cam_error));
			}
		}
		
	}
	/**
	 * 点击弹出摄像机菜单
	 */
	OnClickListener popClickListener =new OnClickListener(){

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(null!=popupWindow&& popupWindow.isShowing()){
				popupWindow.dismiss();
				popupWindow=null;
			}else{
			initPopupWindow();
			//这里是位置显示方式，在屏幕的左侧
			popupWindow.showAtLocation(v,Gravity.LEFT,0,0);
		}
		}
		
	};
	
	/**
	 * 创建PopupWindow
	 */
	protected void initPopupWindow(){
		//获取list数据
		runnable=new Runnable() {
			@Override
			public void run() {

				//判断mCamList是否有数据，没有的话则加载
				if(mCamList.size()==0){
					getData();// 获取报警数据
				}
			}
			};
			runnable.run();
		//获取自定义布局文件popupwindow_left.xml的视图
		popupWindowView=getActivity().getLayoutInflater().inflate(R.layout.popupwindow, null, false);
		mCamListView = (ListView) popupWindowView.findViewById(R.id.listview_cam);// 报警列表信息
		mMainActivity = (MainActivity) getActivity();
		mCamAdapter = new CamAdapter(mCamList, mMainActivity);
		mCamListView.setAdapter(mCamAdapter);//设置适配器
		mCamListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
					Toast.makeText(mMainActivity, mCamList.get(position).getCamId().toString()+" "+mCamList.get(position).getCamIp().toString()+"",
							Toast.LENGTH_SHORT).show();

			}

		});

		//创建PopupWindow实例，200，LayoutParams.Match_parent分别是宽度和高度
		popupWindow= new PopupWindow(popupWindowView,LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT);
		//设置动画效果
		popupWindow.setAnimationStyle(R.style.AnimationFade);
		//点击其他地方消失
		popupWindowView.setOnTouchListener(new OnTouchListener() {  
            @Override  
            public boolean onTouch(View v, MotionEvent event) {  
                // TODO Auto-generated method stub  
                if (popupWindow != null && popupWindow.isShowing()) {  
                    popupWindow.dismiss();  
                    popupWindow = null;  
                }  
                return false;  
            }

        });  
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		MainActivity.currFragTag = Constant.FRAGMENT_FLAG_CAM;

	}
	}
