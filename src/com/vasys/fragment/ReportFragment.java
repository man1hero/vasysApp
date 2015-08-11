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
	private Spinner yearSpinner;//�±�����
	private Spinner yearAlarmSpinner;//�걨�������˵�
	private Spinner monthSpinner;
	private Button monthSearchBtn;//�±�����ѯ��ť
	private Button yearSearchBtn;//�걨����ѯ��ť
	
	GraphicalView graphicalViewMonth=null;//�±���ͳ�Ʊ�ͼ
	GraphicalView graphicalViewYear=null;//�걨��ͳ�Ʊ�ͼ
	DefaultRenderer renderer;
	private static final String[] year={"2014","2015","2016"};
	private static final String[] month={"01","02","03","04","05","06","07","08","09","11","12"};
	 private ArrayAdapter<String> yearAdapter;
	 private ArrayAdapter<String> monthAdapter;
	int[] colors={Color.BLUE,Color.GREEN};
	double[] monthValues=new double[2];//ֵ
	double[] yearValues=new double[2];//ֵ
	String[] monthKey=new String[2];//��
	String[] yearKey=new String[2];//��
	View reportLayout;
	LinearLayout monthlayout;
	LinearLayout yearlayout;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		reportLayout = inflater.inflate(R.layout.report_layout, container,
				false);
		yearSpinner=(Spinner) reportLayout.findViewById(R.id.spinner_year);//
		yearAlarmSpinner=(Spinner)reportLayout.findViewById(R.id.spinner_alarm_year);//�걨�������˵�
		monthSpinner=(Spinner) reportLayout.findViewById(R.id.spinner_month);
		monthSearchBtn=(Button)reportLayout.findViewById(R.id.btn_report_month);
		yearSearchBtn=(Button)reportLayout.findViewById(R.id.btn_report_year);//�걨����ѯ��ť
		monthSearchBtn.setOnClickListener(searchBtnListener);
		yearSearchBtn.setOnClickListener(yearSearchBtnListener);
		
		//ǿ��ѡ������ArrayAdapter��������
		yearAdapter=new ArrayAdapter<String>(getActivity(), android.R.layout.preference_category,year);
		monthAdapter=new ArrayAdapter<String>(getActivity(), android.R.layout.preference_category,month);
		//���������б���
		yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); 
		//��adapter ��ӵ�spinner��  
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

	//���ò�ѯ�±������ݰ�ť����¼�
	private  OnClickListener searchBtnListener=new OnClickListener() {
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			//���������ȡ���ݡ�����ͼ�߳�
			if(graphicalViewMonth!=null){
			monthlayout.removeView(graphicalViewMonth);
			}
			if(!initMonthData()){
						Toast.makeText(getActivity(), "û�в�ѯ���������ݣ�", Toast.LENGTH_SHORT).show();
					}else{
					drawMonthChart();
					}
		}
		
	};
	
	private  OnClickListener yearSearchBtnListener=new OnClickListener() {
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			//���������ȡ���ݡ�����ͼ�߳�
			if(graphicalViewYear!=null){
				yearlayout.removeView(graphicalViewYear);
				}
			if(!initYearData()){
				Toast.makeText(getActivity(), "û�в�ѯ���������ݣ�", Toast.LENGTH_SHORT).show();
			}else{
				drawYearChart();			
			}					
					
			
		}
		
	};
	//��ʼ���±�������,�����ݵĻ�����true��û�������򷵻�false
	public boolean initMonthData(){
		boolean flag=false;
		String year=yearSpinner.getSelectedItem().toString();
		String month=monthSpinner.getSelectedItem().toString();
		JSONObject jsonObject=ReportService.getMonthAlarm(year, month);
		Log.e("json��", jsonObject.toString());
		try {
		if(jsonObject.getString("smokeNum").equals("0")&&jsonObject.getString("fireNum").equals("0")){
			flag=false;
		}else{
				flag=true;
				monthKey[0]="����������";
				monthKey[1]="���汨������";
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
	 * ��ʼ���걨������
	 */
	public boolean initYearData(){
		boolean flag=false;
		//��ȡ����������˵������
		String year=yearAlarmSpinner.getSelectedItem().toString();
		//��ȡjson����ת��JSONObject
		JSONObject jsonObject=ReportService.getYearAlarm(year);
		try {
			if(jsonObject.getString("smokeNum").equals("0")&&jsonObject.getString("fireNum").equals("0")){
				flag=false;
			}
			else{
				flag=true;
			yearKey[0]="����������";
			yearKey[1]="���汨������";
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
	
	public void drawMonthChart(){//���±�����ͼ
		 renderer=buildCategoryRenderer(colors);
		CategorySeries dataset=buildCategoryDataset("�±�����ͼ",monthKey,monthValues);
		graphicalViewMonth=ChartFactory.getPieChartView(getActivity(), dataset, renderer);
		
		monthlayout=(LinearLayout)reportLayout.findViewById(R.id.month_report_liner);
		monthlayout.removeAllViews();
		monthlayout.setBackgroundColor(Color.WHITE);
		monthlayout.addView(graphicalViewMonth, new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
	}
	public void drawYearChart(){//���걨����ͼ
		 renderer=buildCategoryRenderer(colors);
		CategorySeries dataset=buildCategoryDataset("�걨����ͼ",yearKey,yearValues);
		graphicalViewYear=ChartFactory.getPieChartView(getActivity(), dataset, renderer);
		
		 yearlayout=(LinearLayout)reportLayout.findViewById(R.id.year_report_liner);
		 yearlayout.removeAllViews();
		 yearlayout.setBackgroundColor(Color.WHITE);
		 yearlayout.addView(graphicalViewYear, new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
	}
	
	
	//������dataset�ķ���--��ͼ
	public CategorySeries buildCategoryDataset(String title,String[] key,double[] value){
		 CategorySeries series = new CategorySeries(title);
		 series.add(key[0],value[0]);
		 series.add(key[1],value[1]);
		 return series;
	}
	//����renderer�ķ���--��ͼ
	 public DefaultRenderer buildCategoryRenderer(int[] colors) {
		          DefaultRenderer renderer = new DefaultRenderer();
		         
		         renderer.setLegendTextSize(20);//�������½Ǳ�ע�����ִ�С
		        renderer.setZoomButtonsVisible(true);//������ʾ�Ŵ���С��ť  
		          renderer.setZoomEnabled(true);//���ò�����Ŵ���С.  
		            renderer.setChartTitleTextSize(32);//����ͼ���������ִ�С
		            renderer.setChartTitle("");//����ͼ��ı���  Ĭ���Ǿ��ж�����ʾ
		            renderer.setLabelsTextSize(24);//��ͼ�ϱ�����ֵ������С
		           renderer.setLabelsColor(Color.BLACK);//��ͼ�ϱ�����ֵ���ɫ
		           renderer.setPanEnabled(true);//�����Ƿ����ƽ��
		           renderer.setDisplayValues(true);//�Ƿ���ʾֵ
		           renderer.setClickEnabled(true);//�����Ƿ���Ա����
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
