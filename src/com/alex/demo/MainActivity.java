package com.alex.demo;

import com.alex.develop.BaseActivity;
import com.alex.develop.R;
import android.os.Bundle;

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
	}
}