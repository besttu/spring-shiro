package com.shiro.pojo.we;

public class Movie {
	private String id;
	private String title;
	private String name;
	private String img;
	private String url;
	private String date;
	private String type;
	private String category;
	private String region;
	private String other;

	@Override
	public String toString() {
		return "Movie [id=" + id + ", title=" + title + ", name=" + name + ", img=" + img + ", url=" + url + ", date="
				+ date + ", type=" + type + ", category=" + category + ", region=" + region + ", other=" + other + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

}
