package com.alex.develop;

import com.alex.develop.ui.LoadingDialog;
import com.alex.develop.util.WindowHelper;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

/**
 * 基本Activity
 * @author Created by alex 2014/10/23
 */
public class BaseActivity extends FragmentActivity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		WindowHelper.initialize(this);
		
		loadingDialog = new LoadingDialog(this);
	}
	
	@SuppressLint("CommitTransaction")
	public FragmentTransaction getTransaction() {
		return getSupportFragmentManager().beginTransaction();
	}
	
	
	/**
	 * 加载数据Dialog，不可取消，加载完成后dismiss即可
	 */
	protected LoadingDialog loadingDialog;
}