package com.alex.develop;

import com.alex.develop.ui.LoadingDialog;
import com.alex.develop.util.WindowHelper;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.Toast;

/**
 * 基本Activity
 * @author Created by alex 2014/10/23
 */
public class BaseActivity extends FragmentActivity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		WindowHelper.initialize(this);
		initialize();
		
		loadingDialog = new LoadingDialog(this);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(backTwice2Exit && keyCode == KeyEvent.KEYCODE_BACK) {
			if(System.currentTimeMillis()-exitTime > BACK_TWICE_INTERVAL) {
				Toast.makeText(this, getString(R.string.back_twice_to_exit), Toast.LENGTH_SHORT).show();
				exitTime = System.currentTimeMillis();
			} else {
				exit();
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	@SuppressLint("CommitTransaction")
	public FragmentTransaction getTransaction() {
		return getSupportFragmentManager().beginTransaction();
	}
	
	public void exit() {
		finish();
	}
	
	public boolean isBackTwice2Exit() {
		return backTwice2Exit;
	}

	public void setBackTwice2Exit(boolean backTwice2Exit) {
		this.backTwice2Exit = backTwice2Exit;
	}
	
	private void initialize() {
		backTwice2Exit = false;
	}

	protected LoadingDialog loadingDialog;//加载数据Dialog，不可取消，加载完成后dismiss即可
	private boolean backTwice2Exit;//是否Back2次退出App
	private final static int BACK_TWICE_INTERVAL = 2500;
	private long exitTime;
}