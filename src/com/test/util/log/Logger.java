package com.test.util.log;

import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;

import android.util.Log;

public final class Logger
{
	private static final Formatter formatter = new LogFormatter();
	
	private Level level = LogLevel.INFO;
	private final String name;
	
	private Logger (Class<?>clazz, Level lev)
	{
		name = clazz.getName();
		level = lev;
	}
	
	public static final Logger getLogger (Class<?>clazz, Level level)
	{
		Logger result = new Logger(clazz, level);
		result.level = LogLevel.LOG_ENABLED ? level : LogLevel.OFF;
		
		if(LogLevel.COMMON_LEVEL.intValue() < result.level.intValue())
		{
			result.level = LogLevel.COMMON_LEVEL;
		}
		
		return result;
	}
	
	public final void debug (String message, Object...args)
	{
		if (LogLevel.DEBUG.intValue() >= this.level.intValue()) 
		{
			Log.d(LogLevel.LOG_APPLICATION, getFormatedMessage(LogLevel.DEBUG, message, args));
        }
	}
	
	public final void warning (String message, Object...args)
	{
		if (LogLevel.WARNING.intValue() >= this.level.intValue()) 
		{
			Log.w(LogLevel.LOG_APPLICATION, getFormatedMessage(LogLevel.WARNING, message, args));
        }
	}
	
	public final void info (String message, Object...args)
	{
		if (LogLevel.INFO.intValue() >= this.level.intValue()) 
		{
			Log.i(LogLevel.LOG_APPLICATION, getFormatedMessage(LogLevel.INFO, message, args));
        }
	}
	
	public final void error (String message, Throwable e, Object...args)
	{
		if (LogLevel.ERROR.intValue() >= this.level.intValue()) 
		{
			Log.e(LogLevel.LOG_APPLICATION, getFormatedMessage(LogLevel.ERROR, message, args), e);
        }
	}
	
	private final String getFormatedMessage (Level lev, String message, Object...args)
	{
		String formatmess = String.format(message, args);
		LogRecord lr = new LogRecord(lev, formatmess);
        lr.setLoggerName(this.name);
        StackTraceElement[] st = Thread.currentThread().getStackTrace();
        if(st != null && st.length >= 5 && st[4] != null)
        {
        	lr.setSourceClassName(st[4].getClassName());
        	lr.setSourceMethodName(st[4].getMethodName());
        }
        
        return formatter.format(lr);
	}
}
