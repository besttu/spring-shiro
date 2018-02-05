package com.shiro.util.we;

import java.util.List;

import com.shiro.pojo.MusicJson;
import com.shiro.pojo.we.Movie;

/**
 * 电影线程
 * 
 * @author admin
 *
 */
public class MovieRunnable implements Runnable {
	private String toUserName;
	private String token;
	private String content;
	private List<Movie> lists;

	public MovieRunnable(String toUserName, String token, String content, List<Movie> lists) {
		// TODO Auto-generated constructor stub
		this.toUserName = toUserName;
		this.token = token;
		this.content = content;
		this.lists = lists;
	}

	public void run() {
		try {
			StringBuilder sb = new StringBuilder();
			for (Movie j : lists) {
				sb.append("电影名字:" + j.getTitle() + "\n");
				sb.append("演员:" + j.getName() + "\n");
				sb.append("下载地址:" + "\n");
				sb.append(j.getUrl());
			}
			sb.append("请把地址复制到浏览器下载 ......");

			WeChatUtil.sendMessage(toUserName, token, sb.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
