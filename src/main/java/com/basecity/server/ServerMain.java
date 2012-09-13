package com.basecity.server;

import org.apache.commons.logging.Log;

import com.basecity.util.LogUtil;
/**
 * 
 * @author liufeng
 * 服务步骤： 1.启动服务，监听客户端发送的脚本请求。
 *          2.解析脚本，判断脚本是否合法，将脚本解析为ScriptBean对象。
 *          3.执行脚本，通过2生成的ScriptBean数据修改文件。
 */
public class ServerMain {
	private static Log log = LogUtil.getLog(ServerMain.class);
	public static void main(String[] args) {
		log.info("Server starting:::::::::");
		System.setProperty("file.encoding", "utf-8");
		ServerSocketManager ssm = new ServerSocketManager();
		ssm.doListener();
		log.info("Server started:::::::::");
	}
}
