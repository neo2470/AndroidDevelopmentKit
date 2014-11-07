package com.alex.demo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.alex.develop.BaseActivity;
import com.alex.develop.R;

public class Splash extends BaseActivity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				startActivity();
			}
		}, SPLASH_DISPLAY_LENGTH);
	}
	
	private void startActivity() {
		boolean isFirst = true;
		if(isFirst) {
			intent = new Intent(this, Feature.class);
		} else {
			intent = new Intent(this, MainActivity.class);
		}
		startActivity(intent);
	}
	
	private Intent intent;
	private final int SPLASH_DISPLAY_LENGTH = 2000;
}
