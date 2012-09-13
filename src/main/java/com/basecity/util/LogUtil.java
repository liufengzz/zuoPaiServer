package com.basecity.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Appender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class LogUtil {
	public static boolean start = false;
	public static Log getLog(String className) {
		init();
		return LogFactory.getLog(className);
	}
	private static void init() {
		if(start)
			return;
		start = true;
		setLog(PropertiesUtil.getStrProperty("log.file"),PropertiesUtil.getStrProperty("log.level"));
	}
	public static Log getLog(Class<?> cls) {
		return getLog(cls.getName());
	}
	public static void setLog(String file,String level) {
		if(file==null) file="test.log"; 
		Level l = Level.toLevel(level);
		if(level==null) 
			l=Level.INFO;
		Appender app = Logger.getRootLogger().getAppender("R");
		Logger lg = Logger.getRootLogger();
		lg.setLevel(l);
		FileAppender fApp = (FileAppender) app;
		fApp.setFile(file);
		fApp.activateOptions();
	}
}
