package com.basecity.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.basecity.ScriptBean;

public class AnalysisScipt {

	/**
	 * 格式： ncmj/新手入门1 zuopai 1'(1,1,1,2,2,2,3,3,3,4,4,4,5,5);
	 */
	public final static String scriptRegex = "^\\w+/[^\\s]+\\s+\\w+\\s+([1-4]\\'\\(\\d+[\\d|\\,]*\\)\\;)+$";
	/**
	 * 解析脚本
	 * @param script 脚本
	 * @return  
	 * @throws Exception
	 */
	public static ScriptBean analysisScipt(String script) throws Exception{
		if(script==null) return null;
		script = script.trim();
		if(!script.matches(scriptRegex))
			throw new Exception("script invalid,regex:[command] [seat(1-4)]'([value],[],...);[seat(1-4)]'([value],[],...);...!");
		String[] str = script.split("\\s+");
		if(str.length<3) 
          throw new Exception("data transfers error，script missing!");
		ScriptBean sc = getScriptBean(str[0], str[1]);
		sc.addAllStr(getResultString(str[2]));
		return sc;
	}
	/**
	 * 通过游戏名、脚本命令解析相应文件路径及文件名。
	 * @param str
	 * @param command
	 * @return
	 */
	private static ScriptBean getScriptBean(String str,String command) throws Exception{
		if(str==null) return null;
		String[] arr = str.split("/");
		String src = PropertiesUtil.getStrProperty(arr[0]+".src");
		if(src==null)
			throw new Exception(arr[0]+" path(.src) undefined");
		String index = PropertiesUtil.getStrProperty(arr[0]+"."+arr[1]);
		if(index==null) {
			throw new Exception(arr[0]+"."+arr[1]+" index undefined");
		}
		src = src.replaceAll("\\$\\{index\\}", index);
		String fileName = PropertiesUtil.getStrProperty(arr[0]+"."+command);
//		if(fileName==null) {
//			throw new Exception("服务器未定义"+arr[0]+"."+command+"命令文件");
//		}
		if(fileName==null)
			fileName = "fapai.properties";
		ScriptBean sc = new ScriptBean();
		sc.setFileName(fileName);
		sc.setFileSrc(src);
		return sc;
	}
	/**
	 * 解析脚本内容，返回实际需要写入的字符串数组。
	 * @param str
	 * @return
	 */
	private static List<String> getResultString(String str) {
		String[] arr = str.split(";");
		Map<String, String> map = new HashMap<String, String>();
		for(String iStr : arr) {
			int index = Integer.parseInt(iStr.charAt(0)+"");
			String result = iStr.substring(3,str.length()-2);
			map.put("seat"+index, result);
		}
		List<String> result = new ArrayList<String>();
		for(String key : map.keySet()) {
			result.add(key+"="+map.get(key));
		}
		return result;
	}
}
