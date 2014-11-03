package com.alex.develop;

import com.alex.develop.util.WindowHelper;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
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
		
		checkForUpdate();
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