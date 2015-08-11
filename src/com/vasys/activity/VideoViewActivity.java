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
 * 播放服务器上的视频
 * @author lin
 *
 */
public class VideoViewActivity extends Activity{
	private ImageView back;//返回键
	private Button btn_load;//加载
	private Button btn_play;//播放
	private Button btn_pause;//暂停
	private SeekBar sb_progress;//进度条
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
	
	//加载整个view视图
	public void loadView(){
		//加载底部三个button控件
		btn_load=(Button)findViewById(R.id.btn_load);
		btn_play = (Button)findViewById(R.id.btn_play);
    	btn_pause = (Button)findViewById(R.id.btn_pause);
    	back=(ImageView)findViewById(R.id.video_back);
    	//加载进度条控件
    	sb_progress = (SeekBar)findViewById(R.id.sb_progress);
    	//加载videoview控件
    	vv_player = (CustomVideoView)findViewById(R.id.vv_player);
		
    	
	}
	//所有控件事件监听
	public void addListener(){
		//load控件事件监听方法
    	btn_load.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//设置服务器视频资源路径
				Uri uri = Uri.parse("http://192.168.0.58:8080/WildDuck/Resources/video/tianjiang.mp4");
				vv_player.setVideoURI(uri);
				//设置本地视频资源路径
				//vv_player.setVideoPath("/sdcard/video/test.mp4");
				vv_player.setMediaController(new MediaController(VideoViewActivity.this));
				//设置焦点
				
				vv_player.requestFocus();
			}
		});
    	//play控件事件监听方法
    	btn_play.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				vv_player.start();
				sb_progress.setMax(vv_player.getDuration()/1000);
				//创建一个线程用于同步seekbar进度
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
    	//pause控件事件监听方法
		btn_pause.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				vv_player.pause();
			}
		});
		//返回键点击事件监听方法
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
				
			}
		});
	}

}
