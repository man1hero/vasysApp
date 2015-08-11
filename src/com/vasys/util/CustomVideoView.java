package com.vasys.util;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;
/**
 * ����VideoView�࣬��дonMeasure������ʵ���ڲ鿴��Ƶʱ����ȫ������
 * @author lin
 *
 */
public class CustomVideoView extends VideoView{
	private int mVideoWidth;
	private int mVideoHeight;

	public CustomVideoView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	public CustomVideoView(Context context,AttributeSet attrs) {
		super(context,attrs);
		// TODO Auto-generated constructor stub
	}
	public CustomVideoView(Context context,AttributeSet attrs,int defStyle){
		super(context,attrs,defStyle);
	}
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		int width=getDefaultSize(mVideoWidth, widthMeasureSpec);
		int height=getDefaultSize(mVideoHeight, heightMeasureSpec);
		setMeasuredDimension(width, height);
	}

}
