package com.shiro.test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.google.gson.Gson;

public class ThreadPoolExecutorTest {
	public static void main(String[] args) {
		System.out.println("helo");
		Map c = new HashMap<>();
		c.put("content", "hello world");
		Map j = new HashMap();
		j.put("touser", "oxks8we2IMJbkjO3o3R5KF4w4UUU");
		j.put("text", c);
		j.put("msgtype", "text");
		String json = new Gson().toJson(j);
		System.out.println(json);

	}
}