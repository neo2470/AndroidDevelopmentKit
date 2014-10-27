package com.alex.demo;

import com.alex.develop.BaseActivity;
import com.alex.develop.R;
import com.alex.develop.util.WindowHelper;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;

/**
 * 一个简单的Demo
 * @author Created by alex 2014/10/23
 *
 */
public class MainActivity extends BaseActivity{
	
	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		WindowHelper.stickyImmersion();
		setContentView(R.layout.demo_main_activity);
		
		Switch navigationBar = (Switch) findViewById(R.id.navigationBar);
		navigationBar.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				
				if(isChecked) {
				} else {
					WindowHelper.revealStatusAndNabigationBar();
				}
			}
		});
	}
}