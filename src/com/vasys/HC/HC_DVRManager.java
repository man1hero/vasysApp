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
 * ���ú���SDK
 * 
 * @author lin
 * 
 */
public class HC_DVRManager {

	private final static String TAG = "HC_DEBUG";
	public final static String ACTION_START_RENDERING = "action_start_rendering";
	public final static String ACTION_DVR_OUTLINE = "action_dvr_outline";

	/**
	 * �豸��Ϣ ģ��ͨ����bychannum ����ͨ����byipchanum
	 */
	private NET_DVR_DEVICEINFO_V30 deviceInfo_v30 = null;

	/**
	 * ������ -1δ���룬0�ѵ���
	 */
	private int m_iLogID = -1;
	/**
	 * ���ű�� -1δ���ţ�0 ���ڲ���
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
	 * ���ڷ��㲥��������
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
	 * ���ò����豸��Ϣ
	 */
	public void setDeviceBean(DeviceBean bean) {
		this.ip = bean.getIp();
		this.port = Integer.parseInt(bean.getPort());
		this.username = bean.getUserName();
		this.password = bean.getPassWord();
		this.channel = Integer.parseInt(bean.getChannel());
	}

	/**
	 * ���ò����ӿ�
	 */
	public void setSurfaceHolder(SurfaceHolder holder) {
		this.holder = holder;
	}

	/**
	 * ���ڷ��ɹ㲥��������
	 */
	public void setContext(Context context) {
		this.context = context;
	}

	public void initSDK() {
		if (m_iPlayID >= 0) {
			stopPlay();
		}
		if (HCNetSDK.getInstance().NET_DVR_Init()) {
			Log.i(TAG, "��ʼ��SDK�ɹ�");
		} else {
			Log.e(TAG, "��ʼ��SDKʧ��");
		}
	}

	/**
	 * ��¼�豸
	 */
	public void loginDevice() {
		deviceInfo_v30 = new NET_DVR_DEVICEINFO_V30();
		m_iLogID = HCNetSDK.getInstance().NET_DVR_Login_V30(ip, port, username,
				password, deviceInfo_v30);
		System.out.println("�������豸��Ϣ************************");
		System.out.println("userId=" + m_iLogID);
		System.out.println("ͨ����ʼ=" + deviceInfo_v30.byStartChan);
		System.out.println("ͨ������=" + deviceInfo_v30.byChanNum);
		System.out.println("�豸����=" + deviceInfo_v30.byDVRType);
		System.out.println("ipͨ������=" + deviceInfo_v30.byIPChanNum);
		if (m_iLogID < 0) {
			Log.e(TAG,
					"�����豸ʧ�ܣ�"
							+ ErrorMsg.getErrorMsg(HCNetSDK.getInstance()
									.NET_DVR_GetLastError()));
		} else {
			Log.i(TAG, "����ɹ���");
		}
	}

	public synchronized void realPlay() {
		try {
			if (m_iLogID < 0) {
				Log.e(TAG, "�������µ���");
				int count = 0;
				while (count < 5) {
					Log.i(TAG, "���ڵ�" + (count + 1) + "�����µ���");
					loginDevice();
					if (m_iLogID < 0) {
						count++;
						Thread.sleep(200);
					} else {
						Log.i(TAG, "��" + (count + 1) + "�ε���ɹ�");
						break;
					}
				}
				if (m_iLogID < 0) {
					Log.e(TAG, "���Ե���" + count + "�ξ�ʧ�ܣ�");
					return;
				}
			}

			if (m_iPlayID < 0) {
				// Ԥ����������
				NET_DVR_CLIENTINFO ClientInfo = new NET_DVR_CLIENTINFO();
				ClientInfo.lChannel = channel + deviceInfo_v30.byStartChan;
				ClientInfo.lLinkMode = 0;
				// �ಥ��ַ����Ҫ�ಥԤ��ʱ����
				ClientInfo.sMultiCastIP = null;

				m_iPlayID = HCNetSDK.getInstance().NET_DVR_RealPlay_V30(
						m_iLogID, ClientInfo, getRealPlayerCallBack(), true);
				if (m_iPlayID < 0) {
					Log.e(TAG,
							"ʵʱ����ʧ�ܣ�"
									+ ErrorMsg.getErrorMsg(HCNetSDK
											.getInstance()
											.NET_DVR_GetLastError()));
					if (HCNetSDK.getInstance().NET_DVR_GetLastError() == 416) {
						// ���͹㲥
						context.sendBroadcast(new Intent(ACTION_DVR_OUTLINE));
					}
					return;
				} else {
					Log.i(TAG, "��ʼʵʱ���ţ�");
				}
			} else {
				Log.d(TAG, "���ڲ����У�");
			}
		} catch (Exception e) {
			Log.e(TAG, "�쳣��" + e.toString());
		}
	}

	/**
	 * ��ȡʵʱ���Żص�
	 * 
	 * @return
	 */
	private RealPlayCallBack getRealPlayerCallBack() {
		return new RealPlayCallBack() {
			/**
			 * iRealHandle ��ǰ��Ԥ����� iDataType �������� pDataBuffer ������ݵĻ�����ָ��
			 * iDataSize ��������С
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
	 * ץJPEGͼƬ����
	 * @return 
	 */
	public boolean captureJpegPicture(){
		NET_DVR_JPEGPARA jpeg=new NET_DVR_JPEGPARA();
		INT_PTR a=new INT_PTR();
		System.out.println("���س��ȣ�"+a);
		byte[] num=new byte[1024*1024];
		//����ͼƬ�ķֱ���
		jpeg.wPicSize=2;
		//����ͼƬ����
		jpeg.wPicQuality=2;
		//�����ļ�Ŀ¼
		File file=new File(device.filePath+"/a.jpg");
		file.mkdir();
		//1.userId����ֵ 2��ͨ���� 3.ͼ����� 4.·��
		boolean is=HCNetSDK.getInstance().NET_DVR_CaptureJPEGPicture_NEW(0, 0, jpeg, num, 1024*1024, a);
		Log.e(TAG, is+" "+HCNetSDK.getInstance().NET_DVR_GetLastError());
		
		//�洢����
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
	 * ��ʼ¼����
	 * @author lin
	 */
	public void StartRecord(){
		int arg0=0;
		int arg1=0;
		int arg2=0;
		HCNetSDK.getInstance().NET_DVR_StartDVRRecord(arg0, arg1, arg2);
	}
	
	/**
	 * ֹͣ¼��
	 */
	public void StopRecord(){
		int arg1=0;
		int arg2=0;
		HCNetSDK.getInstance().NET_DVR_StopDVRRecord(arg1, arg2);
	}
	
	/**
	 * �򿪶Խ�����
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
	 * �رնԽ�����
	 */
	public  void  StopVoice(){
		if(m_iPlayID<0){
			Log.e(TAG,"����Ԥ������");
			return;
		}else{
			if(HCNetSDK.getInstance().NET_DVR_StopVoiceCom(0)){
				Log.e(TAG, "�رնԽ��ɹ���");
			}else{
				Log.e(TAG, "�رնԽ�ʧ�ܣ�");
			}
		}
		
	}
	
	
	/**
	 * ֹͣ����
	 */
	public synchronized void stopPlay() {
		if (m_iPlayID < 0) {
			Log.e(TAG, "�Ѿ�ֹͣ��");
			return;
		}
		// ֹͣ���粥��
		if (HCNetSDK.getInstance().NET_DVR_StopRealPlay(m_iPlayID)) {
			Log.e(TAG, "ֹͣʵʱ���ųɹ���");
		} else {
			Log.e(TAG,
					"ֹͣʵʱ����ʧ�ܣ�"
							+ ErrorMsg.getErrorMsg(HCNetSDK.getInstance()
									.NET_DVR_GetLastError()));
			return;
		}
		// ֹͣ���ز���
		if (Player.getInstance().stop(m_iPort)) {
			Log.i(TAG, "ֹͣ���ز��ųɹ���");
		} else {
			Log.e(TAG, "ֹͣ���ز���ʧ�ܣ�");
			return;
		}
		// �ر���Ƶ��
		if (Player.getInstance().closeStream(m_iPort)) {
			Log.i(TAG, "�ر���Ƶ���ɹ���");
		} else {
			Log.e(TAG, "�ر���Ƶ��ʧ�ܣ�");
			return;
		}
		// �ͷŲ��Ŷ˿�
		if (Player.getInstance().freePort(m_iPort)) {
			Log.i(TAG, "�ͷŲ��Ŷ˿ڳɹ���");
		} else {
			Log.e(TAG, "�ͷŲ��Ŷ˿�ʧ�ܣ�");
			return;
		}
		// ���Ŷ˿ڸ�λ
		m_iPort = -1;
		// ���ڲ��ű�Ǹ�λ
		m_iPlayID = -1;
		Log.i(TAG, "ֹͣ���ųɹ���");
	}

	/**
	 * �ǳ��豸
	 */
	public void logoutDevice() {
		if (HCNetSDK.getInstance().NET_DVR_Logout_V30(m_iLogID)) {
			m_iLogID = -1;
			Log.i(TAG, "�ǳ��豸�ɹ���");
		} else {
			m_iLogID = 0;
			Log.e(TAG,
					"�ǳ��豸ʧ�ܣ�"
							+ ErrorMsg.getErrorMsg(HCNetSDK.getInstance()
									.NET_DVR_GetLastError()));
		}
	}

	/**
	 * �ͷź���SDK
	 */
	public void freeSDK() {
		// ������
		if (HCNetSDK.getInstance().NET_DVR_Cleanup()) {
			Log.i(TAG, "�ͷ�SDK��Դ�ɹ���");
		} else {
			Log.e(TAG, "�ͷ�SDK��Դʧ�ܣ�");
		}
	}

	/**
	 * ��Ƶ������
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
				Log.d(TAG, "����ͷ����");
				if (m_iPort >= 0) {
					break;
				}
				m_iPort = Player.getInstance().getPort();
				if (m_iPort == -1) {
					Log.e(TAG, "��ȡ���Ŷ˿�ʧ�ܣ�");
					break;
				} else {
					Log.i(TAG, "��ȡ���Ŷ˿ڳɹ���");
				}
				if (iDataSize > 0) {
					if (Player.getInstance().setStreamOpenMode(m_iPort,
							iStreamMode)) {
						Log.i(TAG, "������Ƶ���ɹ���");
					} else {
						Log.e(TAG, "������Ƶ��ʧ�ܣ�");
						break;
					}

					// ץͼ�ص�����
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
										"ContextΪ�գ�û��setContext(Context context)��");
							}
							Log.d(TAG, "��ʼ������Ⱦ");
							if (Player.getInstance()
									.setDisplayCB(m_iPort, null)) {
								Log.i(TAG, "�Ƴ���ʾ�ص��ɹ���");
							} else {
								Log.e(TAG, "�Ƴ���ʾ�ص�ʧ�ܣ�");
							}
						}
					};
					if (Player.getInstance().setDisplayCB(m_iPort, displayCB)) {
						Log.i(TAG, "������ʾ�ص��ɹ���");
					} else {
						Log.e(TAG, "������ʾ�ص�ʧ�ܣ�");
						break;
					}
					if (Player.getInstance().setDisplayBuf(m_iPort, 10)) { // ֡�ʣ�������ΪĬ��15
						Log.i(TAG, "���ò��Ż�������󻺳�֡���ɹ���");
					} else {
						Log.e(TAG, "���ò��Ż�������󻺳�֡��ʧ�ܣ�");
						break;
					}
					if (Player.getInstance().openStream(m_iPort, pDataBuffer,
							iDataSize, 2 * 1024 * 1024)) {
						Log.i(TAG, "����Ƶ���ɹ���");
					} else {
						Log.e(TAG, "����Ƶ��ʧ�ܣ�");
						break;
					}
					if (Player.getInstance().play(m_iPort, holder)) {
						Log.i(TAG, "���ز��ųɹ���");
					} else {
						Log.e(TAG, "���ز���ʧ�ܣ�");
						break;
					}
				} else {
					Log.e(TAG, "��Ƶ�������ݣ�");
				}
				break;
			case HCNetSDK.NET_DVR_STREAMDATA:
			case HCNetSDK.NET_DVR_STD_AUDIODATA:
			case HCNetSDK.NET_DVR_STD_VIDEODATA:
				// Log.i(TAG, "����������");
				if (iDataSize > 0 && m_iPort != -1) {
					for (i = 0; i < 400; i++) {
						if (Player.getInstance().inputData(m_iPort,
								pDataBuffer, iDataSize)) {
							Log.i(TAG, "�������ݳɹ���");
							break;
						}
						Thread.sleep(10);
					}
					if (i == 400) {
						Log.e(TAG, "��������ʧ�ܣ�");
					}
				}
				break;
			default:
				break;
			}
		} catch (Exception e) {
			Log.e(TAG, "��Ƶ�������쳣��" + e.toString());
		}
	}

}
