package com.basecity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ScriptBean {
	private List<String> strList = new ArrayList<String>();//做牌的队列格式：seat$=*,*,*, $表示位置，＊表示牌
	private String fileSrc;//修改的配置文件路径
	private String fileName;//修改的配置文件名
	public void addStr(String str) {
		strList.add(str);
	}
    public void addAllStr(Collection<? extends String> col) {
    	strList.addAll(col);
    }
	public List<String> getStrList() {
		return strList;
	}
	public String getFileSrc() {
		return fileSrc;
	}
	public void setFileSrc(String fileSrc) {
		this.fileSrc = fileSrc;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}
