package com.test.androidslide;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.WindowManager;

import com.test.androidslide.interfaces.IServiceBinder;
import com.test.util.log.LogLevel;
import com.test.util.log.Logger;

public class LockScreenActivity extends FragmentActivity 
{

	private static final Logger log = Logger.getLogger(LockScreenActivity.class, LogLevel.DEBUG);
	
	private static final String LOCK_SCREEN_FRAGMENT_TAG = "slider.frame";
	
	 
	@Override
    protected void onCreate (Bundle state)
    {
    	log.info("state: %s", state);
    	
    	super.onCreate(state);
    	
    	setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        
        getWindow().addFlags(
        		WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED | 
        		WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON |
        		WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        try
        {
	        Fragment fragment = getSupportFragmentManager().findFragmentByTag(LOCK_SCREEN_FRAGMENT_TAG);
	        
	        if (fragment == null)
	        {
	        	fragment = getIntent().getExtras().getParcelable(IServiceBinder.ACTIVE_FRAME);
	        }
	        
	        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
	        ft.replace(android.R.id.content, fragment, LOCK_SCREEN_FRAGMENT_TAG);
	        ft.commit();
        }
        catch (Exception e)
        {
        	log.error("", e);
        }
        
    }
	

}
