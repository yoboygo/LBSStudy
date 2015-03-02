package tk.codecube.lbs.baidu.application;

import android.app.Application;

import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;

public class LocationApplication extends Application {
	
	/**
	 * Bind Listener
	 */
	private BDLocationListener lbsListener;
	
	/**
	 * Main Location Client
	 */
	private LocationClient lbsClient;
	
	/* (non-Javadoc)
	 * @see android.app.Application#onCreate()
	 */
	public void onCreate() {
		
		//new a LocationClient
		this.setLbsClient(new LocationClient(this.getApplicationContext()));
		//set the bdListener
//		this.setLbsListener(new BdLBSListener());
    }

	public LocationClient getLbsClient() {
		return lbsClient;
	}

	public void setLbsClient(LocationClient lbsClient) {
		this.lbsClient = lbsClient;
	}

	public BDLocationListener getLbsListener() {
		return lbsListener;
	}

	public void setLbsListener(BDLocationListener lbsListener) {
		this.lbsListener = lbsListener;
	}


	
}
