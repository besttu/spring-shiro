package com.shiro.util.we;

import java.util.List;

import com.shiro.pojo.MusicJson;

/**
 * 音乐线程
 * 
 * @author admin
 *
 */
public class MusicRunnable implements Runnable {
	private String toUserName;
	private String token;
	private String content;

	public MusicRunnable(String toUserName, String token, String content) {
		// TODO Auto-generated constructor stub
		this.toUserName = toUserName;
		this.token = token;
		this.content = content;
	}

	public void run() {
		try {
			List<MusicJson> json = WeChatUtil.execFIile(content, null);
			StringBuilder sb = new StringBuilder();
			for (MusicJson j : json) {
				sb.append("歌曲:" + j.getSongname() + "\n");
				sb.append("歌手:" + j.getName() + "\n");
				sb.append("下载地址:" + "\n");
				int count = 1;
				for (String url : j.getUrls()) {
					sb.append("地址" + count++);
					sb.append(url + "\n");
				}
			}
			sb.append("请把地址复制到浏览器下载 ......");

			WeChatUtil.sendMessage(toUserName, token, sb.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
