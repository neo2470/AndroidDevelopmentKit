package com.alex.develop.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.alex.develop.R;
import com.alex.develop.settings.Remote;
import com.alex.develop.ui.ConfirmDialog;
import com.alex.develop.ui.ConfirmDialog.OnConfirmListener;

public class Main extends BaseFragment {
	
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		checkForUpdate();
	}

	/**
	 * 检测更新
	 */
	private void checkForUpdate() {
		
		if(pkgInfo.versionCode < Remote.versionCode) {
			
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
}
