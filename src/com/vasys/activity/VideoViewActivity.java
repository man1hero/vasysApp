package com.vasys.activity;

import com.vasys.R;
import com.vasys.util.CustomVideoView;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.SeekBar;
/**
 * ���ŷ������ϵ���Ƶ
 * @author lin
 *
 */
public class VideoViewActivity extends Activity{
	private ImageView back;//���ؼ�
	private Button btn_load;//����
	private Button btn_play;//����
	private Button btn_pause;//��ͣ
	private SeekBar sb_progress;//������
	private CustomVideoView vv_player;
	private boolean flag=true;
	
	Handler handler=new Handler(){
		public void handleMessage(Message msg){
			sb_progress.setProgress(msg.getData().getInt("current", 0)/1000);
		}
	};
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.video);
		loadView();
		addListener();
	}
	
	//��������view��ͼ
	public void loadView(){
		//���صײ�����button�ؼ�
		btn_load=(Button)findViewById(R.id.btn_load);
		btn_play = (Button)findViewById(R.id.btn_play);
    	btn_pause = (Button)findViewById(R.id.btn_pause);
    	back=(ImageView)findViewById(R.id.video_back);
    	//���ؽ������ؼ�
    	sb_progress = (SeekBar)findViewById(R.id.sb_progress);
    	//����videoview�ؼ�
    	vv_player = (CustomVideoView)findViewById(R.id.vv_player);
		
    	
	}
	//���пؼ��¼�����
	public void addListener(){
		//load�ؼ��¼���������
    	btn_load.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//���÷�������Ƶ��Դ·��
				Uri uri = Uri.parse("http://192.168.0.58:8080/WildDuck/Resources/video/tianjiang.mp4");
				vv_player.setVideoURI(uri);
				//���ñ�����Ƶ��Դ·��
				//vv_player.setVideoPath("/sdcard/video/test.mp4");
				vv_player.setMediaController(new MediaController(VideoViewActivity.this));
				//���ý���
				
				vv_player.requestFocus();
			}
		});
    	//play�ؼ��¼���������
    	btn_play.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				vv_player.start();
				sb_progress.setMax(vv_player.getDuration()/1000);
				//����һ���߳�����ͬ��seekbar����
				new Thread(){
					@Override
					public void run() {
						// TODO Auto-generated method stub
						super.run();
						while (flag) {
							Message msg = handler.obtainMessage();
							msg.getData().putInt("current", vv_player.getCurrentPosition());
							handler.sendMessage(msg);
							try {
								sleep(1000);
							} catch (Exception e) {
								// TODO: handle exception
							}
						}
					}
				}.start();
			}
		});
    	//pause�ؼ��¼���������
		btn_pause.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				vv_player.pause();
			}
		});
		//���ؼ�����¼���������
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
				
			}
		});
	}

}
