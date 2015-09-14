package com.tatian.up.ui;

import com.tatian.up.utils.UniversalPlugString;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class ExitDialog {

	public interface ExitCallback {

		public void onPositive();

		public void onNegative();

	}

	public void exit(final Activity activity, final ExitCallback callback) {

		activity.runOnUiThread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				show(activity, callback);
			}

		});

	}

	private void show(Activity activity, final ExitCallback callback) {

		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		builder.setMessage(UniversalPlugString.exit_title)
				.setCancelable(false)
				.setPositiveButton(UniversalPlugString.exit_confirm,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								callback.onPositive();
							}
						})
				.setNegativeButton(UniversalPlugString.exit_cancel, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {

						callback.onNegative();
					}
				});

		AlertDialog alert = builder.create();
		alert.show();

	}

}
