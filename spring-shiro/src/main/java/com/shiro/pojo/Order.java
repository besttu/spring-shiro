package com.shiro.pojo;

/**
 * 订单
 * 
 * @author admin
 *
 */
public class Order {
	private String title;
	private String price;
	private String desc;
	private String number;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "Order [title=" + title + ", price=" + price + ", desc=" + desc + ", number=" + number + "]";
	}

}
