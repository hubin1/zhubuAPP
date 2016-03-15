package com.ms.action;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import cn.emay.eucp.client.EmaySDKClient;

/**
 * 使用 C/S结构进行短信的发送
 * 
 * @author louis
 *
 */
public class SendMsg {

	public static EmaySDKClient sender;
	public static int status;

	static {
		try {
			sender = EmaySDKClient.getInstance();
			status = 0;
			System.out.println("连接短信服务提供商成功");
		} catch (Exception ex) {
			System.out.println("连接短信服务提供商失败");
			status = 1;
		}
	}

	public static void main(String[] args) throws Exception {
		if (status == 0) {
			SendMsg msg = new SendMsg();
			msg.run();
		}
	}

	public void run() throws Exception {
		// 启动准备的工作
		ServerSocket server = new ServerSocket(11000);
		boolean loop = true;
		System.out.println("接受请求成功，短信发送平台工作中....");
		while (loop) {
			Socket client = server.accept();

			new Thread(new Runnable() {

				@Override
				public void run() {
					InputStream input = null;
					try {
						input = client.getInputStream();
					} catch (IOException e) {
						e.printStackTrace();
					}
					if (null != input) {
						BufferedReader reader = new BufferedReader(new InputStreamReader(input));
						try {
							String content = reader.readLine();
							// 接受的格式 是  手机号;内容
							String[] data = content.split(";");
							
							String[] phone = {data[0]};
							String msg = data[1]+" [逐步APP]";
							
							System.out.println(phone+"   "+msg);
							
							SendMsg.sender.sendSMS(phone, msg, null, 5);
							reader.close();
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				}
			}).start();

		}
	}
}
