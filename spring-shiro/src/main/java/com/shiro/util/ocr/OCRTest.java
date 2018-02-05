package com.shiro.util.ocr;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.util.Base64Utils;

import com.baidu.aip.ocr.AipOcr;
import com.baidu.aip.util.Base64Util;
import com.google.gson.Gson;

public class OCRTest {
	// 设置APPID/AK/SK
	public static final String APP_ID = "10779016";
	public static final String API_KEY = "AAcDVcD1VHhE5oiwj4O0OBy6";
	public static final String SECRET_KEY = "MiLlkd3ikGcKSsKhCpsr2QjsiNGO2awU";

	public static void main(String[] args) {
		// 初始化一个AipOcr
		AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

		// 可选：设置网络连接参数
		client.setConnectionTimeoutInMillis(2000);
		client.setSocketTimeoutInMillis(60000);
		String path = "d:/Users/Desktop/a.png";
		JSONObject basicGeneral = client.basicGeneral(path, new HashMap<String, String>());
		String s = basicGeneral.toString();
		Gson son = new Gson();
		BaiDu bd = son.fromJson(s, BaiDu.class);
		System.out.println(bd.toString());
		int int1 = basicGeneral.getInt("words_result_num");
	}

}
