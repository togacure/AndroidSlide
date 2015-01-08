package com.test.androidslide.interfaces;

import android.app.Activity;
import android.os.Parcelable;

public interface IServiceBinder
{
	public static final String ACTIVE_FRAME = "active.frame";

	public void setActiveFrame (int id);
	public int getActiveFrame ();
	public Class<? extends Activity> getActivityClass ();
	public Class<? extends Activity> getMainActivityClass ();
	public void setAdapter (IAdapter adapter);
	public Parcelable getActiveFragment ();
	
}
