package com.alex.develop.ui;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.TextView;

import com.alex.develop.R;

public class ConfirmDialog extends DialogFragment {
	
	public interface OnDialogConfirmListener {
		
		/**
		 * 
		 * @param dialog
		 */
		public void positive(DialogFragment dialog);
		
		/**
		 * 
		 * @param dialog
		 */
		public void negative(DialogFragment dialog);
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		
		AlertDialog.Builder builder = new Builder(getActivity());
		
		view = getActivity().getLayoutInflater().inflate(R.layout.confirm_dialog, null);
		
		builder.setView(view);
		builder.setPositiveButton(R.string.ok, new OnClickListener(){

			@Override
			public void onClick(DialogInterface dialog, int which) {
				confirmListener.positive(ConfirmDialog.this);
			}
			
		});
		builder.setNegativeButton(R.string.cancel, new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				confirmListener.negative(ConfirmDialog.this);
			}
		});
		
		return builder.create();
	}
	
	public void setTitle(String title) {
		((TextView) view.findViewById(R.id.confirmTitle)).setText(title);
	}
	
	public void setContent(String content) {
		((TextView) view.findViewById(R.id.confirmContent)).setText(content);
	}
	
	public void setOnDialogConfirmListener(OnDialogConfirmListener confirmListener) {
		this.confirmListener = confirmListener;
	}
	
	private View view;
	private OnDialogConfirmListener confirmListener;
}
