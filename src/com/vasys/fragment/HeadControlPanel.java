package com.vasys.fragment;

import com.vasys.R;
import com.vasys.util.Constant;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class HeadControlPanel extends RelativeLayout {
	private TextView mMidleTitle;
	private static final float middle_title_size = 20f;
	private static final int default_background_color = Color.rgb(23, 124, 202);

	public HeadControlPanel(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onFinishInflate() {
		// TODO Auto-generated method stub
		mMidleTitle = (TextView) findViewById(R.id.midle_title);
		setBackgroundColor(default_background_color);
	}

	public void initHeadPanel() {// 初始化顶部面板
		if (mMidleTitle != null) {
			setMiddleTitle(Constant.FRAGMENT_FLAG_CAM);
		}
		
	}

	public void setMiddleTitle(String s) {// 设置中间标题
		mMidleTitle.setText(s);
		mMidleTitle.setTextSize(middle_title_size);
	}

}
