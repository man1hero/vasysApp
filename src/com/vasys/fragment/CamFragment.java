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
	private ImageText popImageText=null;//�����˵�
	private ImageText captureJpeg=null;//ץͼ
	private ImageText record=null;//¼��
	private ImageText talk=null;//�Խ�
	private ImageText fullscreen=null;//ȫ��
	private ImageView arrowUp;//���ϵļ�ͷ
	private ImageView arrowDown;//���µļ�ͷ
	
	private LinearLayout camControlLinear;//��������������Բ���
	
	

	Camera camera;
	View camLayout;
	View popupWindowView;
	static Context mMain;
	DeviceBean bean;
	
	private MainActivity mMainActivity;
	private ListView mCamListView;// ListView
	private CamAdapter mCamAdapter;// ������
	private List<Cam> mCamList = new ArrayList<Cam>();// ������б�
	Runnable runnable;
	private PopupWindow popupWindow;

	/**
	 * ���ر��
	 */
	private boolean backflag;
	private final StartRenderingReceiver receiver =new StartRenderingReceiver();

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		camLayout = inflater.inflate(R.layout.cam_layout, container,
				false);
		initView();
		
		//�������ڷ��㲥��������
		HC_DVRManager.getInstance().setContext(this.getActivity());
		
	
		popImageText=(ImageText)camLayout.findViewById(R.id.popwindow_btn);
		popImageText.setImage(R.drawable.document);
		//popImageText.setText("�б�");
		popImageText.setOnClickListener(popClickListener);

		captureJpeg=(ImageText)camLayout.findViewById(R.id.captureJpeg);
		captureJpeg.setImage(R.drawable.capture_jpeg);
		//captureJpeg.setText("ץͼ");
		captureJpeg.setOnClickListener(new OnClickListener() {//���ץͼ֮�����Ӧ���
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//���ý�ͼ����
				HC_DVRManager.getInstance().captureJpegPicture();
			}
		});
		
		record=(ImageText)camLayout.findViewById(R.id.start_record);
		record.setImage(R.drawable.start_record);
		//record.setText("¼��");
		
		talk=(ImageText)camLayout.findViewById(R.id.talk);
		talk.setImage(R.drawable.mic);
		//talk.setText("�Խ�");
		
		fullscreen=(ImageText)camLayout.findViewById(R.id.fullscreen);
		fullscreen.setImage(R.drawable.focus);
		//fullscreen.setText("ȫ��");
		
		
		return camLayout;
		
	}
	//��ʼ�������
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
	
	//��ʼ���������
	public void startPlay(final DeviceBean bean){
		Log.e("startPlay","��ʼ����");
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
	 * ֹͣ����
	 */
	public void stop(){
			HC_DVRManager.getInstance().stopPlay();
		}

	/**
	 * ��ʼ������
	 */
	public void initView(){
		//��ȡ�ֻ��ֱ���
		DisplayMetrics dm=new DisplayMetrics();
		this.getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
		tv_Loading=(TextView)camLayout.findViewById(R.id.tv_Loading);
		tv_Loading.setVisibility(View.INVISIBLE);//����������������ͷ����
		sf_VideoMonitor=(SurfaceView)camLayout.findViewById(R.id.sf_VideoMonitor);
		
		camControlLinear=(LinearLayout)camLayout.findViewById(R.id.cam_control_linear);
		arrowUp=(ImageView)camLayout.findViewById(R.id.image_arrow_up);
		arrowDown=(ImageView)camLayout.findViewById(R.id.image_arrow_down);
		//����������Ʋ˵�
		arrowUp.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				arrowUp.setVisibility(View.GONE);
				camControlLinear.setVisibility(View.VISIBLE);
				arrowDown.setVisibility(View.VISIBLE);
			}
		});
		
		//����������ؿ��Ʋ˵�����ʾ������ť
		arrowDown.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				arrowDown.setVisibility(View.GONE);
				camControlLinear.setVisibility(View.GONE);
				arrowUp.setVisibility(View.VISIBLE);
				
			}
		});
		
		//������Ƶ���ڴ�С
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
			Log.e("cams��", cams.toString());
			// ����json������䵽ListView
			try {
				
				for (int i = 0; i < cams.length(); i++) {
					Cam cam = new Cam();
					cam.setCamId(cams.getJSONObject(i).getString(
							"camId"));// �������
					cam.setCamIp(cams.getJSONObject(i).getString(
							"camIp"));// ����λ��
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
	//�㲥������
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
	 * �������������˵�
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
			//������λ����ʾ��ʽ������Ļ�����
			popupWindow.showAtLocation(v,Gravity.LEFT,0,0);
		}
		}
		
	};
	
	/**
	 * ����PopupWindow
	 */
	protected void initPopupWindow(){
		//��ȡlist����
		runnable=new Runnable() {
			@Override
			public void run() {

				//�ж�mCamList�Ƿ������ݣ�û�еĻ������
				if(mCamList.size()==0){
					getData();// ��ȡ��������
				}
			}
			};
			runnable.run();
		//��ȡ�Զ��岼���ļ�popupwindow_left.xml����ͼ
		popupWindowView=getActivity().getLayoutInflater().inflate(R.layout.popupwindow, null, false);
		mCamListView = (ListView) popupWindowView.findViewById(R.id.listview_cam);// �����б���Ϣ
		mMainActivity = (MainActivity) getActivity();
		mCamAdapter = new CamAdapter(mCamList, mMainActivity);
		mCamListView.setAdapter(mCamAdapter);//����������
		mCamListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
					Toast.makeText(mMainActivity, mCamList.get(position).getCamId().toString()+" "+mCamList.get(position).getCamIp().toString()+"",
							Toast.LENGTH_SHORT).show();

			}

		});

		//����PopupWindowʵ����200��LayoutParams.Match_parent�ֱ��ǿ�Ⱥ͸߶�
		popupWindow= new PopupWindow(popupWindowView,LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT);
		//���ö���Ч��
		popupWindow.setAnimationStyle(R.style.AnimationFade);
		//��������ط���ʧ
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
