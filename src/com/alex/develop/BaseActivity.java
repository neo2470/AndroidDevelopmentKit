package com.alex.develop;

import com.alex.develop.util.WindowHelper;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * 基本Activity
 * @author Created by alex 2014/10/23
 * @version v0.0.0
 * 
 * @fixed|bug
 * v0.0.0 创建文件<br />
 * v0.0.1 创建基本数据<br />
 */
public class BaseActivity extends FragmentActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		WindowHelper.initialize(this);
	}
}