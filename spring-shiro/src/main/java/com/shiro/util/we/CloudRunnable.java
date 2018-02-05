package com.shiro.util.we;

import java.io.IOException;

import org.apache.http.ParseException;

/**
 * 网盘线程
 * 
 * @author admin
 *
 */
public class CloudRunnable implements Runnable {
	private String toUserName;
	private String token;
	private String content;

	public CloudRunnable(String toUserName, String token, String content) {
		// TODO Auto-generated constructor stub
		this.toUserName = toUserName;
		this.token = token;
		this.content = content;
	}

	public void run() {
		try {
			Thread.sleep(10000);
			try {
				WeChatUtil.sendMessage(toUserName, token, content);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
