package com.shiro.util.ocr;

import java.util.Arrays;

import com.google.gson.annotations.SerializedName;

public class BaiDu {
	@SerializedName("words_result")
	private String[] word;
	@SerializedName("log_id")
	private String LogId;
	@SerializedName("words_result_num")
	private Integer number;
	@Override
	public String toString() {
		return "BaiDu [word=" + Arrays.toString(word) + ", LogId=" + LogId + ", number=" + number + "]";
	}
	public String[] getWord() {
		return word;
	}
	public void setWord(String[] word) {
		this.word = word;
	}
	public String getLogId() {
		return LogId;
	}
	public void setLogId(String logId) {
		LogId = logId;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}

}
