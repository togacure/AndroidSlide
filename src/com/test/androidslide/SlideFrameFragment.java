package com.test.androidslide;

import com.test.util.log.LogLevel;
import com.test.util.log.Logger;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SlideFrameFragment extends Fragment implements Parcelable
{
	private static final Logger log = Logger.getLogger(SlideFrameFragment.class, LogLevel.DEBUG);
	
	private final int id;
	
	public static final Parcelable.Creator<SlideFrameFragment> CREATOR = new Parcelable.Creator<SlideFrameFragment>() 
	{
		@Override
        public SlideFrameFragment createFromParcel (Parcel in ) 
        {
            return new SlideFrameFragment(in);
        }

		@Override
        public SlideFrameFragment[] newArray (int size) 
        {
            return new SlideFrameFragment[size];
        }
    };
    
	
	public SlideFrameFragment (int id)
	{
		this.id = id;
	}
	
	public SlideFrameFragment (Parcel in)
	{
		this.id = in.readInt();
	}
	
	@Override
    public final void onCreate (Bundle state)
	{
		log.info("id: %d", id);
		super.onCreate(state);
		
	}
	
	@Override
    public View onCreateView (
			    		LayoutInflater inflater, 
			    		ViewGroup container,
			            Bundle state)
	{
		log.info("id: %d", id);		
		ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.sfragment, container, false);
		
		drawSelf(rootView);
		
		return rootView;
    }
	

	private final void drawSelf (View view)
	{
		TextView text = (TextView) view.findViewById(R.id.drawtext);
		
		text.setText(String.format("FRAME #%d", this.id));
	}

	@Override
	public int describeContents () 
	{
		return 0;
	}

	@Override
	public void writeToParcel (Parcel parcel, int flags)
	{
		parcel.writeInt(id);
	}
}
