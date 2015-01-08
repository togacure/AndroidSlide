package com.test.util.log;

import java.util.logging.Level;

@SuppressWarnings("serial")
public class LogLevel extends Level
{
	public static final String LOG_APPLICATION = "AndroidSlider";
	
	public static final Level OFF = new LogLevel("OFF", 500);
	public static final Level ERROR = new LogLevel("ERROR", 400);
	public static final Level WARNING = new LogLevel("WARNING", 300);
	public static final Level INFO = new LogLevel("INFO", 200);
	public static final Level DEBUG = new LogLevel("DEBUG", 100);
	
	
	static boolean LOG_ENABLED = false;
	static Level COMMON_LEVEL = OFF;

	protected LogLevel (String name, int value)
	{
		super(name, value);
	}
	
	public static final void setEnabled (boolean enabled)
	{
		LOG_ENABLED = enabled;
	}
	
	public static final void setCommonLevel (Level level)
	{
		if (level == null)return;
		
		COMMON_LEVEL = level;
	}
	
	static
	{
		setEnabled(true);
		setCommonLevel(LogLevel.DEBUG);
	}

}
