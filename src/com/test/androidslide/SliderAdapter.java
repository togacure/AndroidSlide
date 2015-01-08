package com.test.androidslide;

import com.test.androidslide.interfaces.IAdapter;
import com.test.util.log.LogLevel;
import com.test.util.log.Logger;

import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class SliderAdapter extends FragmentStatePagerAdapter implements IAdapter
{
	private static final Logger log = Logger.getLogger(SliderAdapter.class, LogLevel.DEBUG);

	private static final int FRAMES_SIZE = 2;
	
	private final Fragment[] frames = new Fragment[FRAMES_SIZE];
	
	public SliderAdapter (FragmentManager fm) 
	{
		super(fm);
		frames[0] = new SlideFrameFragment(0);
		frames[1] = new SlideFrameFragment(1);
	}
	
	@Override
	public int getItemPosition (Object item)
	{
		log.info("item: %s", item);
		
		int result = 0;
		
		for (int i = 0; i < frames.length; i++)
		{
			if (!frames[i].equals(item)) continue;
			
			result = i;
		}
		
		return result;
	}

	@Override
	public Fragment getItem (int item) 
	{
		log.info("item: %d", item);
		
		if (item > FRAMES_SIZE) return null;
		
		log.debug("result: %s", frames[item]);
		
		return frames[item];
	}

	@Override
	public int getCount () 
	{
		return FRAMES_SIZE;
	}

	@Override
	public Parcelable getFragment (int idx)
	{
		return (Parcelable) getItem(idx);
	}

}
