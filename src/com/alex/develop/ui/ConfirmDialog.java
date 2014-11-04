package com.alex.develop.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.TextView;

import com.alex.develop.R;

public class ConfirmDialog extends AlertDialog {
	

	public interface OnConfirmListener {
		public void positive(DialogInterface dialog, int which);
		public void negative(DialogInterface dialog, int which);
	}

	public ConfirmDialog(Context context) {
		super(context);
	}
	
	protected ConfirmDialog(Context context, int theme) {
		super(context, theme);
	}
	
	
	public void setTitle(String title) {
		((TextView) view.findViewById(R.id.confirmTitle)).setText(title);
	}
	
	public void setContent(String content) {
		((TextView) view.findViewById(R.id.confirmContent)).setText(content);
	}
	
	public void setBackgroundColor(String backgroundColor) {
	}
	
	public void setOnConfirmListener(OnConfirmListener confirmListener) {
		this.confirmListener = confirmListener;
	}
	
	private View view;
	private OnConfirmListener confirmListener;
}
