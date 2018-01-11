package com.shiro.pojo.we;

import java.util.HashMap;
import java.util.Map;

/**
 * 客服向用户发送消息
 * 
 * @author admin
 *
 */
public class Message {
	private String touser;
	private String msgtype;
	private Map<String, String> text = new HashMap<String, String>();

	public String getTouser() {
		return touser;
	}

	public void setTouser(String touser) {
		this.touser = touser;
	}

	public String getMsgtype() {
		return msgtype;
	}

	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}

	public Map<String, String> getText() {
		return text;
	}

	public void setText(Map<String, String> text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "Message [touser=" + touser + ", msgtype=" + msgtype + ", text=" + text + "]";
	}

}
