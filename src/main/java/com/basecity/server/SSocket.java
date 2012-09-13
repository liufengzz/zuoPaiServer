package com.basecity.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import org.apache.commons.logging.Log;

import com.basecity.util.CommandScript;
import com.basecity.util.LogUtil;

public class SSocket implements Runnable {
	private Log log = LogUtil.getLog(SSocket.class);
	Socket socket;
	public SSocket(Socket socket) {
		this.socket = socket;
	}
	public void run() {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String script = br.readLine();//fapai 1,(1,1,1,2,2,2,3,3,3,4,4,4,5,5)
			CommandScript.commandScript(script);
			response(socket,"command success:"+script);
			log.info("command success:"+script);
		} catch (Exception e) {
			log.error(e.getMessage());
			response(socket, e.getMessage());
		} finally {
			if(br!=null)
				try {
					br.close();
				} catch (Exception e2) {
				}
			if(socket!=null)
				try {
					socket.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
		}
	}
	public void response(Socket sck,String str) {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new OutputStreamWriter(sck.getOutputStream()));
			bw.write(str);
			bw.newLine();
			bw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(bw!=null)
				try {
					bw.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
		}
	}
}
