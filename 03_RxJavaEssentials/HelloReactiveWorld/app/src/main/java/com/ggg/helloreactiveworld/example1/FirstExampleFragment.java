package com.ggg.helloreactiveworld.example1;

import com.ggg.helloreactiveworld.App;
import com.ggg.helloreactiveworld.R;
import com.ggg.helloreactiveworld.Utils;
import com.ggg.helloreactiveworld.apps.AppInfo;
import com.ggg.helloreactiveworld.apps.AppInfoRich;

import android.app.Fragment;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;

public class FirstExampleFragment extends Fragment {

	private File mFilesDir;

	public FirstExampleFragment() {
	}

	private Observable<AppInfo> getApps() {
		return Observable.create(subscriber -> {
			List<AppInfoRich> apps = new ArrayList<>();

			final Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
			mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);

			List<ResolveInfo> infos = getActivity().getPackageManager().queryIntentActivities(mainIntent, 0);
			for (ResolveInfo info : infos) {
				apps.add(new AppInfoRich(getActivity(), info));
			}

			for (AppInfoRich appInfo : apps) {
				Bitmap icon = Utils.drawableToBitmap(appInfo.getIcon());
				String name = appInfo.getName();
				String iconPath = mFilesDir + "/" + name;
				Utils.storeBitmap(App.instance, icon, name);

				if (subscriber.isUnsubscribed()) {
					return;
				}
				subscriber.onNext(new AppInfo(name, iconPath, appInfo.getLastUpdateTime()));
			}
			if (!subscriber.isUnsubscribed()) {
				subscriber.onCompleted();
			}
		});
	}
}
