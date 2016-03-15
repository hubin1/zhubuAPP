package com.ss.util;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 发送消息给  平台
 * @author louis
 *
 */
abstract public class SendMsgToPlatform {
	public static void sender(String phone, String content) {
		OutputStream output = null;
		BufferedWriter writer = null;
		Socket client = null;
		try {
			client = new Socket("127.0.0.1", 11000);
			output = client.getOutputStream();

			writer = new BufferedWriter(new OutputStreamWriter(output));
			writer.write(phone + ";" + content);

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(client != null){
				try {
					client.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
