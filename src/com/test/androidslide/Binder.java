package com.test.androidslide;

import android.app.Activity;
import android.os.Parcelable;

import com.test.androidslide.interfaces.IAdapter;
import com.test.androidslide.interfaces.IServiceBinder;

public class Binder extends android.os.Binder implements IServiceBinder
{
	
	private IAdapter adapter;
	
	@Override
	public void setActiveFrame (int id) 
	{
		/*TODO: implement*/
	}

	@Override
	public int getActiveFrame () 
	{
		return 0;
	}

	@Override
	public Class<? extends Activity> getActivityClass () 
	{
		
		return LockScreenActivity.class;
	}
	
	@Override
	public Class<? extends Activity> getMainActivityClass () 
	{
		
		return SliderActivity.class;
	}


	@Override
	public void setAdapter (IAdapter adapter)
	{
		this.adapter = adapter;
	}

	@Override
	public Parcelable getActiveFragment () 
	{
		Parcelable result = null;
		
		if (adapter == null ||
				(result = adapter.getFragment(getActiveFrame())) == null)
			result = new SlideFrameFragment(getActiveFrame());
		
		return result;
	}

}
