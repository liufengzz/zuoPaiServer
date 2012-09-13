package com.basecity.util;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

import com.basecity.ScriptBean;

public class CommandScript {
	/**
	 * 执行脚本
	 * @param script
	 * @throws Exception
	 */
	public static void commandScript(String script) throws Exception {
		ScriptBean sc = AnalysisScipt.analysisScipt(script);//将脚本解析为ScriptBean对象
		if(sc==null)
			throw new Exception("script analysises error!");
		BufferedWriter bw = null;
		File file = null;
		try {
			file = new File(sc.getFileSrc(),sc.getFileName());
			file.createNewFile();
			bw = new BufferedWriter(new FileWriter(file));
			List<String> list = sc.getStrList();
			for(String str:list) {
				bw.write(str);
				bw.newLine();
			}
		} catch (Exception e) {
			throw new Exception("file("+file.getAbsolutePath()+") writing error"+e.getMessage());
		} finally {
			if(bw!=null)
				try {
					bw.close();
				} catch (Exception e2) {
				}
		}
	}
}
