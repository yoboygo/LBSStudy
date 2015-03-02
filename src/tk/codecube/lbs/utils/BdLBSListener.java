package tk.codecube.lbs.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tk.codecube.lbs.baidu.LBSCommonDefine;
import android.util.Log;
import android.widget.SimpleAdapter;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;

/**
 * @author Aimy 请求返回是调用
 */
public class BdLBSListener implements BDLocationListener {


	private List<Map<String, Object>> itemList;
	private SimpleAdapter resultAdapter;


	public BdLBSListener(SimpleAdapter simpleAdapter,
			List<Map<String, Object>> valueList) {
		this.itemList = valueList;
		this.resultAdapter = simpleAdapter;
	}

	@Override
	public void onReceiveLocation(BDLocation location) {

			Map<String, Object> resultObject = new HashMap<String, Object>();

			resultObject.put(LBSCommonDefine.STR_ERROR_CODE,
					location.getLocType());
			resultObject.put(LBSCommonDefine.STR_LATITUDE,
					" 经度："+location.getLatitude());
			resultObject.put(LBSCommonDefine.STR_LONTITUDE,
					" 纬度："+location.getLongitude());
			resultObject.put(LBSCommonDefine.STR_RADIUS, " 半径："+location.getRadius());
			resultObject.put(LBSCommonDefine.STR_TIME, location.getTime());

			switch (location.getLocType()) {
			case BDLocation.TypeGpsLocation:
				resultObject
						.put(LBSCommonDefine.STR_SPEED, location.getSpeed());
				resultObject.put(LBSCommonDefine.STR_STATELLITE,
						location.getSatelliteNumber());
				resultObject.put(LBSCommonDefine.STR_ADDR,
						location.getAddrStr());
				resultObject.put(LBSCommonDefine.STR_DIRECTION,
						location.getDirection());
				break;
			case BDLocation.TypeNetWorkException:
				resultObject.put(LBSCommonDefine.STR_ADDR,
						location.getAddrStr());
				resultObject.put(LBSCommonDefine.STR_OPERSION,
						location.getOperators());
				break;
			default:
				resultObject
						.put(LBSCommonDefine.STR_SPEED, location.getSpeed());
				resultObject.put(LBSCommonDefine.STR_OPERSION,
						location.getOperators());
				resultObject.put(LBSCommonDefine.STR_STATELLITE,
						location.getSatelliteNumber());
				resultObject.put(LBSCommonDefine.STR_ADDR,
						location.getAddrStr());
				resultObject.put(LBSCommonDefine.STR_DIRECTION,
						location.getDirection());
				break;
			}
			
			//添加数据更改，刷新
			Log.i("LBS", resultObject.toString());
			itemList.add(resultObject);
			resultAdapter.notifyDataSetChanged();

	}

}
