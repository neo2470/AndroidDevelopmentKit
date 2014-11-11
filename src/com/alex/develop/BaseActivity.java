package com.alex.develop;

import com.alex.develop.ui.LoadingDialog;
import com.alex.develop.util.WindowHelper;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.KeyEvent;
import android.widget.Toast;

/**
 * 基本Activity
 * 
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

		// 屏蔽Back键
		if(blockBack) {
			return blockBack;
		}

		// Back两次退出App
		if (backTwice2Exit && keyCode == KeyEvent.KEYCODE_BACK) {
			if (System.currentTimeMillis() - exitTime > BACK_TWICE_INTERVAL) {

				// 呈现于屏幕中央的Toast
				Toast toast = Toast.makeText(this,
						getString(R.string.back_twice_to_exit),
						Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
				toast.show();

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

	/**
	 * 是否屏蔽Back键
	 * @param backable true，屏蔽；false，不屏蔽；默认为false
	 */
	public void blockBack(boolean blockBack) {
		this.blockBack = blockBack;
	}

	/**
	 * 退出Activity，目前仅仅是调用的finish方法
	 */
	public void exit() {
		finish();
	}

	/**
	 * 用户点击Back键两次，是否退出App
	 * 
	 * @return true，退出；false，退出；默认为true
	 */
	public boolean isBackTwice2Exit() {
		return backTwice2Exit;
	}

	/**
	 * 设置用户点击Back键两次，是否退出App
	 * 
	 * @param backTwice2Exit
	 *            true，退出；false，退出；默认为true
	 */
	public void setBackTwice2Exit(boolean backTwice2Exit) {
		this.backTwice2Exit = backTwice2Exit;
	}

	/**
	 * 数据初始化
	 */
	private void initialize() {
		backTwice2Exit = true;
		blockBack = false;
	}

	protected LoadingDialog loadingDialog;// 加载数据Dialog，不可取消，加载完成后dismiss即可
	private boolean backTwice2Exit;// 是否Back2次退出App
	private boolean blockBack;// 是否屏蔽Back
	private final static int BACK_TWICE_INTERVAL = 2500;
	private long exitTime;
}