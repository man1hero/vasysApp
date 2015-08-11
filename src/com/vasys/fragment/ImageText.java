package com.vasys.fragment;

import com.vasys.R;
import com.vasys.util.Constant;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * �ϱ���ͼƬ���±�������
 * 
 * @date 2015-6-11 10:32:29
 * @author lin
 * 
 */
public class ImageText extends LinearLayout {
	private Context mContext = null;
	private ImageView mImageView = null;
	private TextView mTextView = null;
	private final static int DEFAULT_IMAGE_WIDTH = 64;// Ĭ��ͼ����
	private final static int DEFAULT_IMAGE_HEIGHT = 64;// Ĭ��ͼƬ�߶�
	private int CHECKED_COLOR = Color.rgb(29, 118, 199);// ѡ����ɫ
	private int UNCHECKED_COLOR = Color.GRAY;// ��Ȼ��ɫ

	public ImageText(Context context) {
		// TODO Auto-generated constructor stub
		super(context);
		mContext = context;
	}

	public ImageText(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		LayoutInflater inflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View parentView = inflater.inflate(R.layout.image_text_layout, this,
				true);
		mImageView = (ImageView) findViewById(R.id.image_iamge_text);
		mTextView = (TextView) findViewById(R.id.text_iamge_text);

	}

	public void setImage(int id) {// ����ͼ��
		if (mImageView != null) {
			mImageView.setImageResource(id);
			setImageSize(DEFAULT_IMAGE_WIDTH, DEFAULT_IMAGE_HEIGHT);
		}
	}

	public void setText(String s) {// �����ı�
		if (mTextView != null) {
			mTextView.setText(s);
			mTextView.setTextColor(UNCHECKED_COLOR);// ����δѡ����ɫ

		}

	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		return true;
	}

	private void setImageSize(int w, int h) {// ����ͼ���С
		if (mImageView != null) {
			ViewGroup.LayoutParams params = mImageView.getLayoutParams();
			params.width = w;
			params.height = h;
			mImageView.setLayoutParams(params);
		}
	}

	public void setChecked(int itemID) {// ����ѡ��֮�����ʽ
		if (mTextView != null) {
			mTextView.setTextColor(CHECKED_COLOR);
		}
		int checkDrawableId = -1;
		switch (itemID) {
		case Constant.BTN_FLAG_MESSAGE:
			checkDrawableId = R.drawable.cam_selected;
			break;
		case Constant.BTN_FLAG_CONTACTS:
			checkDrawableId = R.drawable.alarm_selected;
			break;
		case Constant.BTN_FLAG_NEWS:
			checkDrawableId = R.drawable.report_selected;
			break;
		case Constant.BTN_FLAG_SETTINGS:
			checkDrawableId = R.drawable.setting_selected;

		default:
			break;
		}
		if (mImageView != null) {
			mImageView.setImageResource(checkDrawableId);
		}
	}

}
