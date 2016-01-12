package com.ggg.helloreactiveworld.apps;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * AppInfo Class
 */
@Data
@Accessors(prefix = "m")
public class AppInfo implements Comparable<Object> {

	// vars
	long mLastUpdateTime;
	String mName;
	String mIcon;

	/**
	 * Constructor
	 * @param name
	 * @param icon
	 * @param lastUpdateTime
	 */
	public AppInfo(String name, String icon, long lastUpdateTime) {
		mName = name;
		mIcon = icon;
		mLastUpdateTime = lastUpdateTime;
	}

	/**
	 * compareTo
	 * @param another
	 * @return
	 */
	@Override
	public int compareTo(Object another) {
		AppInfo anotherAppInfo = (AppInfo) another;
		return getName().compareTo(anotherAppInfo.getName());
	}
}
