package com.test.androidslide;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;

import com.test.androidslide.interfaces.IAdapter;
import com.test.androidslide.interfaces.IFramesHolder;
import com.test.androidslide.interfaces.IServiceBinder;
import com.test.util.log.LogLevel;
import com.test.util.log.Logger;

public class SliderActivity extends FragmentActivity implements IFramesHolder
{
	
	private static final Logger log = Logger.getLogger(SliderActivity.class, LogLevel.DEBUG);
	
	private ViewPager pager;
	private PagerAdapter pagerAdapter;
	private ServiceConnection connector;

    @Override
    protected void onCreate (Bundle state)
    {
    	log.info("state: %s", state);
    	
    	super.onCreate(state);
    	
    	setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    	
        setContentView(R.layout.sactivity);
        
        connector = new ServiceConnector(this);
        pager = (ViewPager) findViewById(R.id.pager);
        pagerAdapter = new SliderAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);
        pager.setCurrentItem(0);
        
        Intent intent = new Intent(this, LockScreenService.class);
        
        startService(intent);
        bindService(
        		intent, 
        		connector, 
        		Context.BIND_IMPORTANT);
        
    }

    
    @Override
    public boolean onCreateOptionsMenu (Menu menu)
    {
        return false;
    }
    
    @Override
    public void onBackPressed ()
    {
    	log.info("item: %d", pager.getCurrentItem());
    	
        if (pager.getCurrentItem() == 0) 
        	super.onBackPressed();
        else 
        	pager.setCurrentItem(pager.getCurrentItem() - 1);
    }


	@Override
	public int getActiveFrameId () 
	{
		return pager.getCurrentItem();
	}


	@Override
	public void setBinder (IServiceBinder binder)
	{
		log.info("binder: %s", binder);
		
		if (binder == null) return;
		
		binder.setAdapter((IAdapter) pagerAdapter);
	}
    
}
