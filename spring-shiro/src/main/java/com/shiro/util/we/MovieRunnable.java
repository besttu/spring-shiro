package com.shiro.util.we;

import java.io.PrintWriter;

/**
 * 电影线程
 * 
 * @author admin
 *
 */
public class MovieRunnable implements Runnable {
	private String toUserName;
	private String fromUserName;
	private String content;
	private PrintWriter out;

	public MovieRunnable(String toUserName, String fromUserName, String content, PrintWriter out) {
		// TODO Auto-generated constructor stub
		this.toUserName = toUserName;
		this.fromUserName = fromUserName;
		this.content = content;
		this.out = out;
	}

	public void run() {
		try {
			Thread.sleep(10000);
			// WeChatUtil.sendMessage(toUserName, fromUserName, content, out);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
