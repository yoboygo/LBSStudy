package tk.codecube.lbs.baidu;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EagleEyeActivity extends Activity {

	private Button start;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eagle_eye);
		
		start = (Button)findViewById(R.id.eagle_bt_start);
		start.setOnClickListener( new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}
