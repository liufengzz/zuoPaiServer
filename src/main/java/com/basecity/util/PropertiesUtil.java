package com.basecity.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;

/**
 * 读取服务器配置信息
 * @author liufeng
 *
 */
public class PropertiesUtil {

	private static Map<String,String> config = readConfig();
	private static long lastModify = new File("script.properties").lastModified();//监听配置文件更改
	private static Log log = LogUtil.getLog(PropertiesUtil.class);
	public static String getStrProperty(String key) {
		refresh();
		return config.get(key);
	}
	public static int getIntegerProperty(String key) {
		try {
			return Integer.parseInt(getStrProperty(key));
		} catch (Exception e) {
			return -1;
		}
	}
	/**
	 * 配置文件更改时，重新读取配置文件
	 */
	private static void refresh() {
		long tmp = new File("script.properties").lastModified();
		if(lastModify<tmp) {
			config = readConfig();
			lastModify = tmp;
		}
	}
	/**
	 * 读取配置文件
	 * @return
	 */
	private static Map<String,String> readConfig() {
		Map<String,String> result = new HashMap<String, String>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("script.properties"));
			String tmp = null;
			while((tmp=br.readLine())!=null) {
				tmp = tmp.trim();
				if(tmp.startsWith("#")) continue;
				if(tmp.length()==0)
					continue;
				if(tmp.indexOf("=")==-1)
					continue;
				String[] arr = tmp.split("=");
				result.put(arr[0], arr[1]);
			}
		} catch (Exception e) {
			log.error("config file("+System.getProperty("user.src")+"/script.properties) reading error!");
		} finally {
			if(br != null) 
				try {
					br.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
		}
		return result;
	}
}
