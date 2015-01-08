package com.test.androidslide;

import com.test.androidslide.interfaces.IServiceBinder;
import com.test.util.log.LogLevel;
import com.test.util.log.Logger;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class LockScreenReceiver extends BroadcastReceiver
{
	private static final Logger log = Logger.getLogger(LockScreenReceiver.class, LogLevel.DEBUG);

	private IServiceBinder binder;
	
	public LockScreenReceiver (IServiceBinder binder)
	{
		this.binder = binder;
	}
	
	@Override
	public void onReceive (Context context, Intent intent)
	{
		String action = intent.getAction();
		
		log.info("action: %s", action);
		
		if (action.equals(Intent.ACTION_SCREEN_ON) ||
				action.equals(Intent.ACTION_SCREEN_OFF))
			show(context, binder.getActivityClass());
		else if (action.equals(Intent.ACTION_USER_PRESENT))
			show(context, binder.getMainActivityClass());
	}

	private void show (Context context, Class<? extends Activity> activity)
	{
		Intent intent = new Intent(context, activity);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(IServiceBinder.ACTIVE_FRAME, binder.getActiveFragment());
        context.startActivity(intent);
	}
}
