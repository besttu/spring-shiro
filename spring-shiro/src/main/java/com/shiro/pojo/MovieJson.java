package com.shiro.pojo;

public class MovieJson {
	private String title;
	private String img;
	private String url;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "MovieJson [title=" + title + ", img=" + img + ", url=" + url + "]";
	}

}
