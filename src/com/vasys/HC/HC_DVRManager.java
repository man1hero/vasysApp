package com.vasys.HC;

import org.MediaPlayer.PlayM4.Player;
import org.MediaPlayer.PlayM4.PlayerCallBack.PlayerDisplayCB;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.SurfaceHolder;

import com.hikvision.netsdk.HCNetSDK;
import com.hikvision.netsdk.INT_PTR;
import com.hikvision.netsdk.NET_DVR_CLIENTINFO;
import com.hikvision.netsdk.NET_DVR_DEVICEINFO_V30;
import com.hikvision.netsdk.NET_DVR_JPEGPARA;
import com.hikvision.netsdk.RealPlayCallBack;
import com.hikvision.netsdk.VoiceDataCallBack;
import com.vasys.bean.DeviceBean;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * 调用海康SDK
 * 
 * @author lin
 * 
 */
public class HC_DVRManager {

	private final static String TAG = "HC_DEBUG";
	public final static String ACTION_START_RENDERING = "action_start_rendering";
	public final static String ACTION_DVR_OUTLINE = "action_dvr_outline";

	/**
	 * 设备信息 模拟通道数bychannum 数字通道数byipchanum
	 */
	private NET_DVR_DEVICEINFO_V30 deviceInfo_v30 = null;

	/**
	 * 登入标记 -1未登入，0已登入
	 */
	private int m_iLogID = -1;
	/**
	 * 播放标记 -1未播放，0 正在播放
	 */
	private int m_iPlayID = -1;
	private int m_iPort = -1;
	private String ip;
	private int port;
	private String username;
	private String password;
	private int channel;
	private SurfaceHolder holder;
	public DeviceBean device=null;

	/**
	 * 用于发广播的上下文
	 */
	private Context context;

	private static HC_DVRManager manager = null;

	private HC_DVRManager() {
	}

	public static synchronized HC_DVRManager getInstance() {
		if (manager == null) {
			manager = new HC_DVRManager();
		}
		return manager;
	}

	/**
	 * 设置播放设备信息
	 */
	public void setDeviceBean(DeviceBean bean) {
		this.ip = bean.getIp();
		this.port = Integer.parseInt(bean.getPort());
		this.username = bean.getUserName();
		this.password = bean.getPassWord();
		this.channel = Integer.parseInt(bean.getChannel());
	}

	/**
	 * 设置播放视口
	 */
	public void setSurfaceHolder(SurfaceHolder holder) {
		this.holder = holder;
	}

	/**
	 * 用于放松广播的上下文
	 */
	public void setContext(Context context) {
		this.context = context;
	}

	public void initSDK() {
		if (m_iPlayID >= 0) {
			stopPlay();
		}
		if (HCNetSDK.getInstance().NET_DVR_Init()) {
			Log.i(TAG, "初始化SDK成功");
		} else {
			Log.e(TAG, "初始化SDK失败");
		}
	}

	/**
	 * 登录设备
	 */
	public void loginDevice() {
		deviceInfo_v30 = new NET_DVR_DEVICEINFO_V30();
		m_iLogID = HCNetSDK.getInstance().NET_DVR_Login_V30(ip, port, username,
				password, deviceInfo_v30);
		System.out.println("下面是设备信息************************");
		System.out.println("userId=" + m_iLogID);
		System.out.println("通道开始=" + deviceInfo_v30.byStartChan);
		System.out.println("通道个数=" + deviceInfo_v30.byChanNum);
		System.out.println("设备类型=" + deviceInfo_v30.byDVRType);
		System.out.println("ip通道个数=" + deviceInfo_v30.byIPChanNum);
		if (m_iLogID < 0) {
			Log.e(TAG,
					"登入设备失败！"
							+ ErrorMsg.getErrorMsg(HCNetSDK.getInstance()
									.NET_DVR_GetLastError()));
		} else {
			Log.i(TAG, "登入成功！");
		}
	}

	public synchronized void realPlay() {
		try {
			if (m_iLogID < 0) {
				Log.e(TAG, "尝试重新登入");
				int count = 0;
				while (count < 5) {
					Log.i(TAG, "正在第" + (count + 1) + "次重新登入");
					loginDevice();
					if (m_iLogID < 0) {
						count++;
						Thread.sleep(200);
					} else {
						Log.i(TAG, "第" + (count + 1) + "次登入成功");
						break;
					}
				}
				if (m_iLogID < 0) {
					Log.e(TAG, "尝试登入" + count + "次均失败！");
					return;
				}
			}

			if (m_iPlayID < 0) {
				// 预览参数配置
				NET_DVR_CLIENTINFO ClientInfo = new NET_DVR_CLIENTINFO();
				ClientInfo.lChannel = channel + deviceInfo_v30.byStartChan;
				ClientInfo.lLinkMode = 0;
				// 多播地址，需要多播预览时配置
				ClientInfo.sMultiCastIP = null;

				m_iPlayID = HCNetSDK.getInstance().NET_DVR_RealPlay_V30(
						m_iLogID, ClientInfo, getRealPlayerCallBack(), true);
				if (m_iPlayID < 0) {
					Log.e(TAG,
							"实时播放失败！"
									+ ErrorMsg.getErrorMsg(HCNetSDK
											.getInstance()
											.NET_DVR_GetLastError()));
					if (HCNetSDK.getInstance().NET_DVR_GetLastError() == 416) {
						// 发送广播
						context.sendBroadcast(new Intent(ACTION_DVR_OUTLINE));
					}
					return;
				} else {
					Log.i(TAG, "开始实时播放！");
				}
			} else {
				Log.d(TAG, "正在播放中？");
			}
		} catch (Exception e) {
			Log.e(TAG, "异常：" + e.toString());
		}
	}

	/**
	 * 获取实时播放回调
	 * 
	 * @return
	 */
	private RealPlayCallBack getRealPlayerCallBack() {
		return new RealPlayCallBack() {
			/**
			 * iRealHandle 当前的预览句柄 iDataType 数据类型 pDataBuffer 存放数据的缓冲区指针
			 * iDataSize 缓冲区大小
			 */
			@Override
			public void fRealDataCallBack(int iRealHandle, int iDataType,
					byte[] pDataBuffer, int iDataSize) {
				processRealData(iDataType, pDataBuffer, iDataSize,
						Player.STREAM_REALTIME);
			}
		};
	}
	/**
	 * 抓JPEG图片功能
	 * @return 
	 */
	public boolean captureJpegPicture(){
		NET_DVR_JPEGPARA jpeg=new NET_DVR_JPEGPARA();
		INT_PTR a=new INT_PTR();
		System.out.println("返回长度："+a);
		byte[] num=new byte[1024*1024];
		//设置图片的分辨率
		jpeg.wPicSize=2;
		//设置图片质量
		jpeg.wPicQuality=2;
		//创建文件目录
		File file=new File(device.filePath+"/a.jpg");
		file.mkdir();
		//1.userId返回值 2。通道号 3.图像参数 4.路径
		boolean is=HCNetSDK.getInstance().NET_DVR_CaptureJPEGPicture_NEW(0, 0, jpeg, num, 1024*1024, a);
		Log.e(TAG, is+" "+HCNetSDK.getInstance().NET_DVR_GetLastError());
		
		//存储本地
		BufferedOutputStream outputStream =null;
		try{
			outputStream=new BufferedOutputStream(new FileOutputStream(file));
			outputStream.write(num);
			outputStream.flush();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(outputStream!=null){
				try{
					outputStream.close();
				}catch(IOException e){
					e.printStackTrace();
				}
			}
		}
		return is;
	}
	/**
	 * 开始录像功能
	 * @author lin
	 */
	public void StartRecord(){
		int arg0=0;
		int arg1=0;
		int arg2=0;
		HCNetSDK.getInstance().NET_DVR_StartDVRRecord(arg0, arg1, arg2);
	}
	
	/**
	 * 停止录像
	 */
	public void StopRecord(){
		int arg1=0;
		int arg2=0;
		HCNetSDK.getInstance().NET_DVR_StopDVRRecord(arg1, arg2);
	}
	
	/**
	 * 打开对讲功能
	 */
	public void StartVoice(){
		int arg0 = 0;
		int arg1 = 0;
		VoiceDataCallBack voiceCallback = new VoiceDataCallBack() {
			
			@Override
			public void fVoiceDataCallBack(int arg0, byte[] arg1, int arg2, int arg3) {
				// TODO Auto-generated method stub
				
			}
		};
		HCNetSDK.getInstance().NET_DVR_StartVoiceCom_MR_V30(arg0, arg1, voiceCallback);
		
	}
	/**
	 * 关闭对讲功能
	 */
	public  void  StopVoice(){
		if(m_iPlayID<0){
			Log.e(TAG,"请先预览！！");
			return;
		}else{
			if(HCNetSDK.getInstance().NET_DVR_StopVoiceCom(0)){
				Log.e(TAG, "关闭对讲成功！");
			}else{
				Log.e(TAG, "关闭对讲失败！");
			}
		}
		
	}
	
	
	/**
	 * 停止播放
	 */
	public synchronized void stopPlay() {
		if (m_iPlayID < 0) {
			Log.e(TAG, "已经停止？");
			return;
		}
		// 停止网络播放
		if (HCNetSDK.getInstance().NET_DVR_StopRealPlay(m_iPlayID)) {
			Log.e(TAG, "停止实时播放成功！");
		} else {
			Log.e(TAG,
					"停止实时播放失败！"
							+ ErrorMsg.getErrorMsg(HCNetSDK.getInstance()
									.NET_DVR_GetLastError()));
			return;
		}
		// 停止本地播放
		if (Player.getInstance().stop(m_iPort)) {
			Log.i(TAG, "停止本地播放成功！");
		} else {
			Log.e(TAG, "停止本地播放失败！");
			return;
		}
		// 关闭视频流
		if (Player.getInstance().closeStream(m_iPort)) {
			Log.i(TAG, "关闭视频流成功！");
		} else {
			Log.e(TAG, "关闭视频流失败！");
			return;
		}
		// 释放播放端口
		if (Player.getInstance().freePort(m_iPort)) {
			Log.i(TAG, "释放播放端口成功！");
		} else {
			Log.e(TAG, "释放播放端口失败！");
			return;
		}
		// 播放端口复位
		m_iPort = -1;
		// 正在播放标记复位
		m_iPlayID = -1;
		Log.i(TAG, "停止播放成功！");
	}

	/**
	 * 登出设备
	 */
	public void logoutDevice() {
		if (HCNetSDK.getInstance().NET_DVR_Logout_V30(m_iLogID)) {
			m_iLogID = -1;
			Log.i(TAG, "登出设备成功！");
		} else {
			m_iLogID = 0;
			Log.e(TAG,
					"登出设备失败！"
							+ ErrorMsg.getErrorMsg(HCNetSDK.getInstance()
									.NET_DVR_GetLastError()));
		}
	}

	/**
	 * 释放海康SDK
	 */
	public void freeSDK() {
		// 清理缓存
		if (HCNetSDK.getInstance().NET_DVR_Cleanup()) {
			Log.i(TAG, "释放SDK资源成功！");
		} else {
			Log.e(TAG, "释放SDK资源失败！");
		}
	}

	/**
	 * 视频流解码
	 * 
	 * @param iDataType
	 * @param pDataBuffer
	 * @param iDataSize
	 * @param iStreamMode
	 */
	private void processRealData(int iDataType, byte[] pDataBuffer,
			int iDataSize, int iStreamMode) {
		int i = 0;
		try {
			switch (iDataType) {
			case HCNetSDK.NET_DVR_SYSHEAD:
				Log.d(TAG, "处理头数据");
				if (m_iPort >= 0) {
					break;
				}
				m_iPort = Player.getInstance().getPort();
				if (m_iPort == -1) {
					Log.e(TAG, "获取播放端口失败！");
					break;
				} else {
					Log.i(TAG, "获取播放端口成功！");
				}
				if (iDataSize > 0) {
					if (Player.getInstance().setStreamOpenMode(m_iPort,
							iStreamMode)) {
						Log.i(TAG, "设置视频流成功！");
					} else {
						Log.e(TAG, "设置视频流失败！");
						break;
					}

					// 抓图回调函数
					PlayerDisplayCB displayCB = new PlayerDisplayCB() {
						@Override
						public void onDisplay(int arg0, ByteBuffer arg1,
								int arg2, int arg3, int arg4, int arg5,
								int arg6, int arg7) {
							if (null != context) {
								context.sendBroadcast(new Intent(
										ACTION_START_RENDERING));
							} else {
								Log.e(TAG,
										"Context为空！没有setContext(Context context)？");
							}
							Log.d(TAG, "开始画面渲染");
							if (Player.getInstance()
									.setDisplayCB(m_iPort, null)) {
								Log.i(TAG, "移除显示回调成功！");
							} else {
								Log.e(TAG, "移除显示回调失败！");
							}
						}
					};
					if (Player.getInstance().setDisplayCB(m_iPort, displayCB)) {
						Log.i(TAG, "设置显示回调成功！");
					} else {
						Log.e(TAG, "设置显示回调失败！");
						break;
					}
					if (Player.getInstance().setDisplayBuf(m_iPort, 10)) { // 帧率，不设置为默认15
						Log.i(TAG, "设置播放缓冲区最大缓冲帧数成功！");
					} else {
						Log.e(TAG, "设置播放缓冲区最大缓冲帧数失败！");
						break;
					}
					if (Player.getInstance().openStream(m_iPort, pDataBuffer,
							iDataSize, 2 * 1024 * 1024)) {
						Log.i(TAG, "打开视频流成功！");
					} else {
						Log.e(TAG, "打开视频流失败！");
						break;
					}
					if (Player.getInstance().play(m_iPort, holder)) {
						Log.i(TAG, "本地播放成功！");
					} else {
						Log.e(TAG, "本地播放失败！");
						break;
					}
				} else {
					Log.e(TAG, "视频流无数据！");
				}
				break;
			case HCNetSDK.NET_DVR_STREAMDATA:
			case HCNetSDK.NET_DVR_STD_AUDIODATA:
			case HCNetSDK.NET_DVR_STD_VIDEODATA:
				// Log.i(TAG, "处理流数据");
				if (iDataSize > 0 && m_iPort != -1) {
					for (i = 0; i < 400; i++) {
						if (Player.getInstance().inputData(m_iPort,
								pDataBuffer, iDataSize)) {
							Log.i(TAG, "输入数据成功！");
							break;
						}
						Thread.sleep(10);
					}
					if (i == 400) {
						Log.e(TAG, "输入数据失败！");
					}
				}
				break;
			default:
				break;
			}
		} catch (Exception e) {
			Log.e(TAG, "视频流解码异常！" + e.toString());
		}
	}

}
