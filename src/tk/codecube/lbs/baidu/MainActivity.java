package tk.codecube.lbs.baidu;

import java.util.ArrayList;
import java.util.List;

import tk.codecube.lbs.baidu.lbs.LocationActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {
	
	
	/**
	 * Main LlistView
	 */
	private ListView mainListView;
	
	
	/* (non-Javadoc)
	 * @see android.support.v7.app.ActionBarActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Main ListView Object
		mainListView = (ListView) findViewById(R.id.main_lsit);
		ListAdapter mainListAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,getListValues());
		mainListView.setAdapter(mainListAdapter);
		
	}
	
	@Override
	public void onStart()
	{
		super.onStart();

		//bind listener to ListView
		this.mainListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Class<?> targetClass = LocationActivity.class;
				switch(position)
				{
					case 0:targetClass = LocationActivity.class;break;
					case 1:targetClass = EagleEyeActivity.class;break;
					default:targetClass = LocationActivity.class;break;
				}
				
				// start another activity
				Intent intent = new Intent(MainActivity.this, targetClass);
				startActivity(intent);
			}
		});
	}

	
	/**
	 * @return List<String> 
	 */
	public List<String> getListValues()
	{
		List<String> listValues = new ArrayList<String>();
		listValues.add("定位");
		listValues.add("鹰眼");
		return listValues;
	}
}
