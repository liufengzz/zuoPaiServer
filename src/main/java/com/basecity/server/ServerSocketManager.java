package com.basecity.server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.commons.logging.Log;

import com.basecity.util.LogUtil;

public class ServerSocketManager {
	private Log log = LogUtil.getLog(ServerSocketManager.class);
	protected ServerSocket ssocket;
	/**
	 * 监听服务端口
	 */
	public void doListener() {

		BufferedWriter bw = null;
		try {
			ssocket = new ServerSocket(19002);
			while(true) {
				Socket client = ssocket.accept();
				new Thread(new SSocket(client)).start();
				String str ="connect to server...";//返回客户端消息，通知连接成功
				bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
				bw.write(str);
				bw.newLine();
				bw.flush();
			}
		} catch (IOException e) {
			log.error(e.getMessage());
		} finally {
			if(bw!=null)
				try {
					bw.close();
				} catch (Exception e2) {
				}
		}
		
	}
}
