package com.test.androidslide;

import com.test.util.log.LogLevel;
import com.test.util.log.Logger;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

public class LockScreenService extends Service 
{
	private static final Logger log = Logger.getLogger(LockScreenService.class, LogLevel.DEBUG);
	
	private Binder binder;
	private BroadcastReceiver lockScreenReceiver;
	
	@Override
    public void onCreate () 
    {
    	log.info("");
        super.onCreate();
     
        binder = new Binder();
        lockScreenReceiver = new LockScreenReceiver(binder);
        
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_USER_PRESENT);
        filter.setPriority(999);
        registerReceiver(lockScreenReceiver, filter);
        
    }
	
	
	@Override
    public void onDestroy()
	{
		log.info("");
		
		unregisterReceiver(lockScreenReceiver);
		
        super.onDestroy();
    }
	
	
	@Override
	public IBinder onBind (Intent intent) 
	{
		return binder;
	}

}
