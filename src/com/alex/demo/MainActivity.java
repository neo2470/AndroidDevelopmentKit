package com.alex.demo;

import com.alex.develop.BaseActivity;
import com.alex.develop.R;
import com.alex.develop.fragment.Feature;
import com.alex.develop.fragment.Splash;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentTransaction;

/**
 * 一个简单的Demo
 * @author Created by alex 2014/10/23
 *
 */
public class MainActivity extends BaseActivity{
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		
		if(findViewById(R.id.container) != null) {
			
			if(savedInstanceState != null) {
				return ;
			}
			
			// 启动界面（Fragment）
			Splash splash = new Splash();
			splash.setArguments(getIntent().getExtras());
			FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
			transaction.add(R.id.container, splash);
			transaction.commit();
		}
		
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				
				// 新功能介绍（Fragment）
				Feature feature = new Feature();
				FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
				transaction.replace(R.id.container, feature);
				transaction.commit();
			}
		}, SPLASH_DISPLAY_LENGTH);
		
	}
	
	
	private final int SPLASH_DISPLAY_LENGTH = 2000;
}