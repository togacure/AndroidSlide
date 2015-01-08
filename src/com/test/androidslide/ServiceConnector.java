package com.test.androidslide;

import android.content.ComponentName;
import android.os.IBinder;

import com.test.androidslide.interfaces.IFramesHolder;
import com.test.androidslide.interfaces.IServiceBinder;

public class ServiceConnector implements android.content.ServiceConnection 
{
	
	private final IFramesHolder holder;
	
	public ServiceConnector (IFramesHolder holder)
	{
		this.holder = holder;
	}
	
	@Override
	public void onServiceConnected (ComponentName cname, IBinder binder)
	{
		if (binder == null || !(binder instanceof IServiceBinder)) return;
		
		holder.setBinder((IServiceBinder) binder);
	}

	@Override
	public void onServiceDisconnected (ComponentName name)
	{
		this.holder.setBinder(null);
	}

}
