package com.shiro.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * 音乐格式
 * 
 * @author admin
 *
 */
public class MusicJson {
	private String songname;
	private String name;
	private List<String> urls = new ArrayList<String>();

	public String getSongname() {
		return songname;
	}

	public void setSongname(String songname) {
		this.songname = songname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getUrls() {
		return urls;
	}

	public void setUrls(List<String> urls) {
		this.urls = urls;
	}

	@Override
	public String toString() {
		return "MusicJson [songname=" + songname + ", name=" + name + ", urls=" + urls + "]";
	}

}
