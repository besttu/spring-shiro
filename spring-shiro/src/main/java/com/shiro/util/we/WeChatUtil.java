package com.shiro.util.we;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.web.servlet.mvc.method.annotation.HttpEntityMethodProcessor;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shiro.pojo.MovieJson;
import com.shiro.pojo.MusicJson;
import com.shiro.pojo.we.AccessToken;
import com.shiro.pojo.we.Message;

public class WeChatUtil {
	private static Logger log = LoggerFactory.getLogger(WeChatUtil.class);
	private static String SEND_MESSAGE = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";
	private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
//	private static String APPID = "wxc9a0833e48b8a214";//
//	private static String APPSECRET = "0235493def8d74dd305fd5da85b95e8c";
//	private static String FILE_PATH = "/root/KuWo.py";
	private static String APPID = "wx35bc6249004e2471";//
	private static String APPSECRET = "da6558fee9afc2d85b97618a162c6af6";
	private static String FILE_PATH = "d://lib//KuWo.py";

	/**
	 * get请求
	 * 
	 * @param url
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	public static String doGetStr(String url) throws ParseException, IOException {
		DefaultHttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		HttpResponse httpResponse = client.execute(httpGet);
		HttpEntity entity = httpResponse.getEntity();
		return EntityUtils.toString(entity, "UTF-8");
	}

	public static void doPostStr(String url, String outStr) throws ParseException, IOException {
		DefaultHttpClient client = new DefaultHttpClient();
		HttpPost httpost = new HttpPost(url);
		// JSONObject jsonObject = null;
		httpost.setEntity(new StringEntity(outStr, "UTF-8"));
		HttpResponse response = client.execute(httpost);
		String result = EntityUtils.toString(response.getEntity(), "UTF-8");
		log.info("url:" + url);
		log.info("message:" + outStr);
		log.info("result:" + result);
		// jsonObject = gson.fromJson(result, classOfT);
	}

	/**
	 * 获取accessToken
	 * 
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	public static String getAccessToken(EhCacheCacheManager cacheManger) throws ParseException, IOException {
		Cache cache = null;
		try {
			cache = cacheManger.getCache("ACCESS_TOKEN");
			String value = cache.get("key1", String.class);
			if (value != null) {
				return value;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		String url = ACCESS_TOKEN_URL.replace("APPID", APPID).replace("APPSECRET", APPSECRET);
		String jsonObject = doGetStr(url);
		Gson gson = new Gson();
		AccessToken fromJson = gson.fromJson(jsonObject, AccessToken.class);
		String token = fromJson.getToken();
		log.info("................helloworld.........获取一次token：" + token);
		try {
			if (token != null) {
				cache.put("key1", token);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return token;
	}

	/**
	 * 微信发送消息
	 * 
	 * @throws IOException
	 * @throws ParseException
	 * 
	 */
	public static void sendMessage(String toUserName, String token, String content) throws ParseException, IOException {
		Message m = new Message();
		m.setMsgtype("text");
		m.setTouser(toUserName);
		m.getText().put("content", content);
		Gson g = new Gson();
		String json = g.toJson(m);
		String url = SEND_MESSAGE.replace("ACCESS_TOKEN", token);
		doPostStr(url, json);
	}

	/**
	 * 
	 * @param filePath
	 *            python文件路径
	 * @param bean
	 *            转化的json实体类
	 * @param word
	 *            关键词
	 * @return
	 */
	public static List<MusicJson> execFIile(String word, String filePath) {
		String[] arguments;
		if (filePath == null) {
			arguments = new String[] { "python", FILE_PATH, word };
		} else {
			arguments = new String[] { "python", filePath, word };
		}
		try {
			Runtime runtime = Runtime.getRuntime();
			runtime.exec(arguments);
			Process process = Runtime.getRuntime().exec(arguments);
			BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = in.readLine()) != null) {
				sb.append(line);
			}
			Gson g = new Gson();
			in.close();
			int re = process.waitFor();
			List<MusicJson> fromJson = g.fromJson(sb.toString(), new TypeToken<List<MusicJson>>() {
			}.getType());
			return fromJson;
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}

	public static List<MovieJson> execFIile1(String word, String filePath) {
		String[] arguments;
		if (filePath == null) {
			arguments = new String[] { "python", FILE_PATH, word };
		} else {
			arguments = new String[] { "python", filePath, word };
		}
		try {
			Runtime runtime = Runtime.getRuntime();
			runtime.exec(arguments);
			Process process = Runtime.getRuntime().exec(arguments);
			BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = in.readLine()) != null) {
				sb.append(line);
			}
			Gson g = new Gson();
			in.close();
			System.out.println("sb:" + sb.toString());
			if (sb.length() < 5) {
				return null;
			}
			int re = process.waitFor();
			List<MovieJson> fromJson = g.fromJson(sb.toString(), new TypeToken<List<MovieJson>>() {
			}.getType());
			return fromJson;
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}

	public static void testSendMessage() {
		Gson gson = new Gson();

		Message m = new Message();
		// m.setTouser("o_bLA02fUN_Hd5O-FMM1PsuiP85A");
		m.setTouser("o_bLA06rCgd-oPrN010aZakfN6cc");
		m.setMsgtype("text");
		m.getText().put("content", "你好");
		String json = gson.toJson(m);
		try {
			doPostStr(
					"https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=5_YixVkqiimxNSBMth9O0keCVADagz-A0bbnZ90xdyFetP4aCDnulMswgSTXwRs4JzzpKwy69yAUXNRB0RxPbZRipG78NwGIEQF7UV7Nm1jIre2bFTElKhVVN26bIKAChACAPFH",
					json);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			new WeChatUtil().getAccessToken(null);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
