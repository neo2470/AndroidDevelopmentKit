package com.alex.develop;

import java.util.Calendar;

import com.alex.develop.settings.Remote;
import com.alex.develop.ui.ConfirmDialog;
import com.alex.develop.ui.LoadingDialog;
import com.alex.develop.ui.ConfirmDialog.OnConfirmListener;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
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

		initialize();
		
		// 根据时间自动更改主题
		changeThemeByTime();
		
		// 创建LoadingDialog
		loadingDialog = new LoadingDialog(this);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		
		// Activity不可见的时候，亦取消Back Toast的提示信息
		if(backToast != null) {
			backToast.cancel();
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		// 屏蔽Back键
		if(blockBack) {
			return blockBack;
		}

		// Back两次退出App
		if (backTwice2Exit && keyCode == KeyEvent.KEYCODE_BACK) {
			if (System.currentTimeMillis() - exitTime > getResources().getInteger(R.integer.back_twice_duration)) {

				// 呈现于屏幕中央的Toast
				backToast = Toast.makeText(this, getString(R.string.back_twice_to_exit), Toast.LENGTH_SHORT);
				backToast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
				backToast.show();

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
	 * 是否允许自动切换主题（根据时间）
	 * @return true，允许；false，不允许；默认为true
	 */
	public boolean isAutoChangeTheme() {
		return autoChangeTheme;
	}

	/**
	 * 设置是否允许自动切换主题（根据时间）
	 * @param autoChangeTheme true，允许；false，不允许；默认为true
	 */
	public void setAutoChangeTheme(boolean autoChangeTheme) {
		this.autoChangeTheme = autoChangeTheme;
	}
	
	/**
	 * 检查远程服务器该App是否有更新版本
	 */
	protected void checkForUpdate() {
		Log.d("Debug-VersionCode", pkgInfo.versionCode + ", " + Remote.versionCode);
		if(pkgInfo.versionCode < Remote.versionCode) {
			
			//弹出更新对话框
			ConfirmDialog updateDialog = new ConfirmDialog(this);
			updateDialog.setTitle(getString(R.string.software_update));
			updateDialog.setContent(getString(R.string.update_features));
			updateDialog.setCancelable(false);
			updateDialog.setOnConfirmListener(new OnConfirmListener() {

				@Override
				public void positive(DialogInterface dialog, int which) {
					// TODO 取消时后的操作
				}

				@Override
				public void negative(DialogInterface dialog, int which) {
					// TODO 确认后联网下载App
				}

			});
			updateDialog.show();
		}
	}
	
	private void changeThemeByTime() {
		
		// 不允许自动切换主题
		if(!autoChangeTheme) {
			Log.d("Debug", "不允许更改主题");
			return ;
		}
		
		Calendar calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		
		if(getResources().getInteger(R.integer.light_theme_start) <= hour && getResources().getInteger(R.integer.dark_theme_start) > hour) {
			Log.d("Debug", "亮色主题");
			// 亮色主题
			setTheme(R.style.AppThemeLight);
		} else {
			Log.d("Debug", "暗色主题");
			// 暗色主题
			setTheme(R.style.AppTheme);
		}
	}

	/**
	 * 数据初始化
	 */
	private void initialize() {
		
		backTwice2Exit = true;
		blockBack = false;
		autoChangeTheme = true;
		
		try {
			PackageManager pm = getPackageManager();
			pkgInfo = pm.getPackageInfo(getPackageName(), 0);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
	}

	protected LoadingDialog loadingDialog;// 加载数据Dialog，不可取消，加载完成后dismiss即可
	protected PackageInfo pkgInfo;// App的Package信息
	private boolean backTwice2Exit;// 是否Back2次退出App
	private boolean blockBack;// 是否屏蔽Back
	private boolean autoChangeTheme;// 是否自动切换主题
	private Toast backToast;// 点击Back键时，显示的Toast
	private long exitTime;// 记录第一次点击Back键的时间
}