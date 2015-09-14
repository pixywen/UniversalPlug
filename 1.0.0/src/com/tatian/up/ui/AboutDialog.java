package com.tatian.up.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.util.Linkify;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.tatian.up.utils.UniversalPlugString;

public class AboutDialog {

	private AlertDialog alert = null;
	private TextView content = null;
	private static AboutDialog instance = null;

	public static AboutDialog getInstance() {

		if (instance == null)
			instance = new AboutDialog();

		return instance;

	}

	public void show(final Activity activity) {

		activity.runOnUiThread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				showAbout(activity);
			}

		});

	}

	private void showAbout(final Activity activity) {

		if (alert == null)
			alert = create(activity);

		content.setText(ToDBC(UniversalPlugString.about_content));
		alert.show();
	}

	public AlertDialog create(final Activity activity) {

		RelativeLayout.LayoutParams screen_params = new RelativeLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

		ScrollView root = new ScrollView(activity);

		TextView text = new TextView(activity);
		content = text;
		root.setLayoutParams(screen_params);
		text.setLayoutParams(screen_params);
		text.setPadding(5, 5, 5, 5);
		text.setAutoLinkMask(Linkify.WEB_URLS | Linkify.PHONE_NUMBERS);
		root.addView(text);

		TextView title = new TextView(activity);
		title.setGravity(Gravity.CENTER_HORIZONTAL);
		title.setTextSize(25);
		title.setText(UniversalPlugString.about_title);
		title.setPadding(5, 5, 5, 5);

		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		builder.setCustomTitle(title);
		builder.setView(root);
		builder.setPositiveButton(UniversalPlugString.exit_confirm,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
		AlertDialog alert = builder.create();
		return alert;
	}

	private String ToDBC(String input) {
		char[] c = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == 12288) {
				c[i] = (char) 32;
				continue;
			}
			if (c[i] > 65280 && c[i] < 65375)
				c[i] = (char) (c[i] - 65248);
		}
		return new String(c);
	}

}
