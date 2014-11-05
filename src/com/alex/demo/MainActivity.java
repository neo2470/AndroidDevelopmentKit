package com.alex.demo;

import com.alex.develop.BaseActivity;
import com.alex.develop.R;
import com.alex.develop.ui.ConfirmDialog;
import com.alex.develop.ui.ConfirmDialog.OnConfirmListener;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
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
		setContentView(R.layout.demo_main_activity);
		
//		checkForUpdate();
	}
	
	/**
	 * 检测更新
	 */
	private void checkForUpdate() {
		
		// TODO 联网操作取得更新信息
		int remoteVersionCode = 2;
		String remoteVersionName = "0.1.2";
		String remoteAppUrl = "";
		
		int localVersionCode = getAppVersionCode();
		String localVersionName = getAppVersionName();
		
		if(localVersionCode < remoteVersionCode) {
			
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

	/**
	 * 获取App的版本名称
	 * @return App版本名称 
	 */
	public String getAppVersionName() {
		String versionName = null;
		try {
			PackageManager pm = getPackageManager();
			PackageInfo pInfo = pm.getPackageInfo(getPackageName(), 0);
			versionName = pInfo.versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		
		return versionName;
	}
	
	/**
	 * 获取App的版本号
	 * @return App版本号
	 */
	public int getAppVersionCode() {
		int versionCode = -1;
		try {
			PackageManager pm = getPackageManager();
			PackageInfo pInfo = pm.getPackageInfo(getPackageName(), 0);
			versionCode = pInfo.versionCode;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return versionCode;
	}
}