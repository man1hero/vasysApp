package com.vasys.adapter;

import java.util.List;

import com.vasys.R;
import com.vasys.activity.MainActivity;
import com.vasys.bean.Cam;
import com.vasys.bean.DeviceBean;
import com.vasys.fragment.CamFragment;
import com.vasys.util.Constant;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
/**
 * ������������������ĳ�������ʱ��ʼԤ����ֹͣԤ��
 * @author lin
 *
 */
public class CamAdapter extends BaseAdapter{
	private List<Cam> mCamList = null;
	private MainActivity mContext;
	private LayoutInflater mInflater;
	
	public CamAdapter(List<Cam> listCam, MainActivity context) {// ���캯��
		this.mCamList = listCam;
		this.mContext = context;
		mInflater = LayoutInflater.from(context);

	}

	@Override
	public int getCount() {
		return mCamList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mCamList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final int i=position;
		// TODO Auto-generated method stub
				View v = mInflater.inflate(R.layout.cam_list_item_layout, null);

				TextView camId = (TextView) v.findViewById(R.id.cam_id_item);
				camId.setText(mCamList.get(position).getCamId());// ������������

				TextView camIp = (TextView) v.findViewById(R.id.cam_ip_item);
				camIp.setText(mCamList.get(position).getCamIp());// ���������IP 

				TextView camLocation = (TextView) v.findViewById(R.id.cam_location_item);
				camLocation.setText(mCamList.get(position).getCamLocation());// ���ñ���ʱ��
				
				 Button startBtn=(Button)v.findViewById(R.id.preview_btn);//��ʼԤ��button
					startBtn.setTag(position);
				startBtn.setOnClickListener(new OnClickListener() {//��Ԥ����ť�������ʱ��
					
				    @Override
				    public void onClick(View v) {//���ʱ��ʼ����
				    	//��ȡCamFragment����
				    	CamFragment cam=(CamFragment) mContext.getFragmentManager().findFragmentByTag(Constant.FRAGMENT_FLAG_CAM);
				    	DeviceBean bean=new DeviceBean();
				    	//�����豸bean
				    	bean.setIp(mCamList.get(i).getCamIp());
				    	bean.setPassWord("12345");
				    	bean.setUserName("admin");
				    	bean.setPort("8000");
				    	bean.setChannel("1");
				    	//��ʼ����
				    	//cam.startPlay(bean);
				    	cam.startCamera();
				    	//��ʾ���ڲ���
				    	Toast.makeText(mContext, "��ʼ����"+mCamList.get(i).getCamIp(), Toast.LENGTH_SHORT).show();
				    }
				   });
				
				Button stopBtn=(Button)v.findViewById(R.id.stop_btn);
				//���ֹͣ����
				stopBtn.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
				    	CamFragment cam=(CamFragment) mContext.getFragmentManager().findFragmentByTag(Constant.FRAGMENT_FLAG_CAM);
				    	//cam.stop();//ֹͣ
				    	cam.stopCamera();
				    	Toast.makeText(mContext, "ֹͣ����"+mCamList.get(i).getCamIp(), Toast.LENGTH_SHORT).show();
					}
				});

				return v;
			}
	}


