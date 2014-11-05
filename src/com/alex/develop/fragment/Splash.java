package com.alex.develop.fragment;

import com.alex.develop.R;
import com.alex.develop.ui.ConfirmDialog;
import com.alex.develop.ui.ConfirmDialog.OnConfirmListener;

import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * App的启动界面
 * @author Created by alex
 */
public class Splash extends BaseFragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.splash, container, false);
	}
	
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		// TODO 联网读取数据
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
			ConfirmDialog updateDialog = new ConfirmDialog(act);
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
			PackageManager pm = act.getPackageManager();
			PackageInfo pInfo = pm.getPackageInfo(act.getPackageName(), 0);
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
			PackageManager pm = act.getPackageManager();
			PackageInfo pInfo = pm.getPackageInfo(act.getPackageName(), 0);
			versionCode = pInfo.versionCode;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return versionCode;
	}
	
}
