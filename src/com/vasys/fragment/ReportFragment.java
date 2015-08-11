package com.vasys.fragment;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.json.JSONException;
import org.json.JSONObject;

import com.vasys.R;
import com.vasys.activity.MainActivity;
import com.vasys.util.Constant;
import com.vasys.webservice.ReportService;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

public class ReportFragment extends BaseFragment {
	private Spinner yearSpinner;//月报警年
	private Spinner yearAlarmSpinner;//年报警下拉菜单
	private Spinner monthSpinner;
	private Button monthSearchBtn;//月报警查询按钮
	private Button yearSearchBtn;//年报警查询按钮
	
	GraphicalView graphicalViewMonth=null;//月报警统计饼图
	GraphicalView graphicalViewYear=null;//年报警统计饼图
	DefaultRenderer renderer;
	private static final String[] year={"2014","2015","2016"};
	private static final String[] month={"01","02","03","04","05","06","07","08","09","11","12"};
	 private ArrayAdapter<String> yearAdapter;
	 private ArrayAdapter<String> monthAdapter;
	int[] colors={Color.BLUE,Color.GREEN};
	double[] monthValues=new double[2];//值
	double[] yearValues=new double[2];//值
	String[] monthKey=new String[2];//键
	String[] yearKey=new String[2];//键
	View reportLayout;
	LinearLayout monthlayout;
	LinearLayout yearlayout;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		reportLayout = inflater.inflate(R.layout.report_layout, container,
				false);
		yearSpinner=(Spinner) reportLayout.findViewById(R.id.spinner_year);//
		yearAlarmSpinner=(Spinner)reportLayout.findViewById(R.id.spinner_alarm_year);//年报警下拉菜单
		monthSpinner=(Spinner) reportLayout.findViewById(R.id.spinner_month);
		monthSearchBtn=(Button)reportLayout.findViewById(R.id.btn_report_month);
		yearSearchBtn=(Button)reportLayout.findViewById(R.id.btn_report_year);//年报警查询按钮
		monthSearchBtn.setOnClickListener(searchBtnListener);
		yearSearchBtn.setOnClickListener(yearSearchBtnListener);
		
		//强可选内容与ArrayAdapter连接起来
		yearAdapter=new ArrayAdapter<String>(getActivity(), android.R.layout.preference_category,year);
		monthAdapter=new ArrayAdapter<String>(getActivity(), android.R.layout.preference_category,month);
		//设置下拉列表风格
		yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); 
		//将adapter 添加到spinner中  
		yearSpinner.setAdapter(yearAdapter);
		yearAlarmSpinner.setAdapter(yearAdapter);
		monthSpinner.setAdapter(monthAdapter);
		
		yearSpinner.setVisibility(View.VISIBLE); 
		monthSpinner.setVisibility(View.VISIBLE); 
		
	
		return reportLayout;
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		MainActivity.currFragTag = Constant.FRAGMENT_FLAG_REPORT;
	}

	//设置查询月报警数据按钮点击事件
	private  OnClickListener searchBtnListener=new OnClickListener() {
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			//访问网络获取数据、画饼图线程
			if(graphicalViewMonth!=null){
			monthlayout.removeView(graphicalViewMonth);
			}
			if(!initMonthData()){
						Toast.makeText(getActivity(), "没有查询到该月数据！", Toast.LENGTH_SHORT).show();
					}else{
					drawMonthChart();
					}
		}
		
	};
	
	private  OnClickListener yearSearchBtnListener=new OnClickListener() {
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			//访问网络获取数据、画饼图线程
			if(graphicalViewYear!=null){
				yearlayout.removeView(graphicalViewYear);
				}
			if(!initYearData()){
				Toast.makeText(getActivity(), "没有查询到该年数据！", Toast.LENGTH_SHORT).show();
			}else{
				drawYearChart();			
			}					
					
			
		}
		
	};
	//初始化月报警数据,有数据的话返回true，没有数据则返回false
	public boolean initMonthData(){
		boolean flag=false;
		String year=yearSpinner.getSelectedItem().toString();
		String month=monthSpinner.getSelectedItem().toString();
		JSONObject jsonObject=ReportService.getMonthAlarm(year, month);
		Log.e("json：", jsonObject.toString());
		try {
		if(jsonObject.getString("smokeNum").equals("0")&&jsonObject.getString("fireNum").equals("0")){
			flag=false;
		}else{
				flag=true;
				monthKey[0]="烟雾报警数量";
				monthKey[1]="火焰报警数量";
				monthValues[0]=Double.parseDouble(jsonObject.getString("smokeNum"));
				monthValues[1]=Double.parseDouble(jsonObject.getString("fireNum"));
				Log.e("smokeNum", monthValues[0]+"'");
		}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return flag;
		
	}
	/**
	 * 初始化年报警数据
	 */
	public boolean initYearData(){
		boolean flag=false;
		//获取点击的下拉菜单的年份
		String year=yearAlarmSpinner.getSelectedItem().toString();
		//获取json数据转成JSONObject
		JSONObject jsonObject=ReportService.getYearAlarm(year);
		try {
			if(jsonObject.getString("smokeNum").equals("0")&&jsonObject.getString("fireNum").equals("0")){
				flag=false;
			}
			else{
				flag=true;
			yearKey[0]="烟雾报警数量";
			yearKey[1]="火焰报警数量";
			yearValues[0]=Double.parseDouble(jsonObject.getString("smokeNum"));
			yearValues[1]=Double.parseDouble(jsonObject.getString("fireNum"));
			Log.e("smokeNum", yearValues[0]+"'");
				}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	
	}
	
	public void drawMonthChart(){//画月报警饼图
		 renderer=buildCategoryRenderer(colors);
		CategorySeries dataset=buildCategoryDataset("月报警饼图",monthKey,monthValues);
		graphicalViewMonth=ChartFactory.getPieChartView(getActivity(), dataset, renderer);
		
		monthlayout=(LinearLayout)reportLayout.findViewById(R.id.month_report_liner);
		monthlayout.removeAllViews();
		monthlayout.setBackgroundColor(Color.WHITE);
		monthlayout.addView(graphicalViewMonth, new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
	}
	public void drawYearChart(){//画年报警饼图
		 renderer=buildCategoryRenderer(colors);
		CategorySeries dataset=buildCategoryDataset("年报警饼图",yearKey,yearValues);
		graphicalViewYear=ChartFactory.getPieChartView(getActivity(), dataset, renderer);
		
		 yearlayout=(LinearLayout)reportLayout.findViewById(R.id.year_report_liner);
		 yearlayout.removeAllViews();
		 yearlayout.setBackgroundColor(Color.WHITE);
		 yearlayout.addView(graphicalViewYear, new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
	}
	
	
	//设置月dataset的方法--饼图
	public CategorySeries buildCategoryDataset(String title,String[] key,double[] value){
		 CategorySeries series = new CategorySeries(title);
		 series.add(key[0],value[0]);
		 series.add(key[1],value[1]);
		 return series;
	}
	//设置renderer的方法--饼图
	 public DefaultRenderer buildCategoryRenderer(int[] colors) {
		          DefaultRenderer renderer = new DefaultRenderer();
		         
		         renderer.setLegendTextSize(20);//设置左下角表注的文字大小
		        renderer.setZoomButtonsVisible(true);//设置显示放大缩小按钮  
		          renderer.setZoomEnabled(true);//设置不允许放大缩小.  
		            renderer.setChartTitleTextSize(32);//设置图表标题的文字大小
		            renderer.setChartTitle("");//设置图表的标题  默认是居中顶部显示
		            renderer.setLabelsTextSize(24);//饼图上标记文字的字体大小
		           renderer.setLabelsColor(Color.BLACK);//饼图上标记文字的颜色
		           renderer.setPanEnabled(true);//设置是否可以平移
		           renderer.setDisplayValues(true);//是否显示值
		           renderer.setClickEnabled(true);//设置是否可以被点击
		         renderer.setMargins(new int[] { 20, 30, 15,0 });
		         //margins - an array containing the margin size values, in this order: top, left, bottom, right
		         for (int color : colors) {
		           SimpleSeriesRenderer r = new SimpleSeriesRenderer();
		           r.setColor(color);
		           renderer.addSeriesRenderer(r);
		         }
		         return renderer;
		       }
}
