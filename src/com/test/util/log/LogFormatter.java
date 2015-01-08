package com.test.util.log;


import java.io.*;
import java.util.logging.*;

public class LogFormatter extends Formatter 
{

	private Formatter formating = new SimpleFormatter();

    
	@Override
	public String format (LogRecord record) 
	{

		StringBuffer sb = new StringBuffer();
		
		sb.append(": [thread: " + Thread.currentThread().getName() + " id: " + Thread.currentThread().getId() + "]");
		
		sb.append(record.getLevel().getLocalizedName());
		sb.append(": ");
		
		if (record.getSourceClassName() != null) 
		{	
		    sb.append(record.getSourceClassName());
		} else 
		{
		    sb.append(record.getLoggerName());
		}
		if (record.getSourceMethodName() != null) 
		{	
		    sb.append(".");
		    sb.append(record.getSourceMethodName());
		}
		sb.append(": ");
		String message = formating.formatMessage(record);
		
		sb.append(message);
		if (record.getThrown() != null)
		{
			try 
			{
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw);
				record.getThrown().printStackTrace(pw);
				pw.close();
				sb.append(sw.toString());
			} 
			catch (Exception ex) 
			{
			}
		}
		return sb.toString();
	}

}

