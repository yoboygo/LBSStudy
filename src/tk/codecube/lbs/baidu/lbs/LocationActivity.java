package tk.codecube.lbs.baidu.lbs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import tk.codecube.lbs.baidu.LBSCommonDefine;
import tk.codecube.lbs.baidu.R;
import tk.codecube.lbs.baidu.application.LocationApplication;
import tk.codecube.lbs.utils.BdLBSListener;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.baidu.location.BDGeofence;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class LocationActivity extends Activity {

	
	private LocationClient locationClient;
	//高精度模式
	private LocationMode locationMode = LocationMode.Hight_Accuracy;
	private String coorType = BDGeofence.COORD_TYPE_GCJ;//"gcj02";//国测局加密经纬度坐标;bd09II:百度加密经纬度坐标;bd09:百度加密墨卡托
	
	@ViewInject(R.id.location_btn_start)
	private Button locationBtnStart;
	@ViewInject(R.id.location_tesult)
	private ListView resultView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_location);
		ViewUtils.inject(this);
		
		LocationApplication locationApplication = (LocationApplication)super.getApplication();
		//get the locationClient
		this.locationClient = locationApplication.getLbsClient();
//		this.resultView = (ListView) findViewById(R.id.location_tesult);
		
		//初始化ListView and item
		String[] itemContent = new String[]{
				LBSCommonDefine.STR_ERROR_CODE,
				LBSCommonDefine.STR_ADDR,
				LBSCommonDefine.STR_TIME,
				LBSCommonDefine.STR_LATITUDE,
				LBSCommonDefine.STR_LONTITUDE,
				LBSCommonDefine.STR_RADIUS};
		int[] itemValueKey = new int[]{
				R.id.list_item_code,
				R.id.list_item_title,
				R.id.list_item_time,
				R.id.list_tiem_latitude,
				R.id.list_tiem_lontitude,
				R.id.list_tiem_radius};
		
		List<Map<String,Object>> resultData = new ArrayList<Map<String,Object>>();
		SimpleAdapter resultAdapter = new SimpleAdapter(this, resultData,R.layout.location_list_item, itemContent, itemValueKey);
		this.resultView.setAdapter(resultAdapter);
		BdLBSListener locaLBSListener = new BdLBSListener(resultAdapter,resultData);
		this.locationClient.registerLocationListener(locaLBSListener);
		
		//button start
		//locationBtnStart = (Button) findViewById(R.id.location_btn_start);
		locationBtnStart.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				initLocationOption();
				if(getString(R.string.location_btn_start).equals(((Button)v).getText().toString()))
				{
					locationBtnStart.setText(getString(R.string.location_btn_state_stop));
					locationClient.start();
				}else{
					locationBtnStart.setText(getString(R.string.location_btn_start));
					locationClient.stop();
				}
			}
		});
		
		//add item onClick
		resultView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			}
		});
		
	}
	
	@Override
	public void onStart()
	{
		super.onStart();
		initLocationOption();
	}
	
	/**
	 * init location option
	 */
	protected void initLocationOption()
	{
		LocationClientOption locationClientOption = new LocationClientOption();
		locationClientOption.setLocationMode(this.locationMode);
		locationClientOption.setCoorType(this.coorType);
		locationClientOption.setIsNeedAddress(true);
		locationClientOption.setNeedDeviceDirect(true);
		
		locationClientOption.setProdName(getString((R.string.product_name)));
		locationClientOption.setScanSpan(1000*30);//间隔ms
		
		this.locationClient.setLocOption(locationClientOption);
	}
	
	@Override
	protected void onResume()
	{
		super.onResume();
		stopLocation();
	}
	
	public void startLocation()
	{
		locationBtnStart.setText(getString(R.string.location_btn_state_stop));
		locationClient.start();
	}
	public void stopLocation()
	{
		locationBtnStart.setText(getString(R.string.location_btn_start));
		locationClient.stop();
	}
}
