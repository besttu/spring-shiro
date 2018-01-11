package com.shiro.controller.system;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shiro.controller.BaseController;
import com.shiro.pojo.MusicJson;
import com.shiro.util.we.CheckUtil;
import com.shiro.util.we.MessageUtil;
import com.shiro.util.we.MusicRunnable;
import com.shiro.util.we.WeChatUtil;

/**
 * 微信公众号开发
 * 
 * @author admin
 *
 */
@Controller
@RequestMapping("/admin/we")
public class WeController extends BaseController {
	ExecutorService pool = Executors.newFixedThreadPool(3);
	@Autowired
	EhCacheCacheManager cacheManger;
	// 创建一个线程池
	ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);

	@GetMapping("/t")
	public void getMessge(ServletRequest req, HttpServletResponse resp) throws IOException {
		String signature = req.getParameter("si" + "gnature");
		String timestamp = req.getParameter("timestamp");
		String nonce = req.getParameter("nonce");
		String echostr = req.getParameter("echostr");
		PrintWriter out = resp.getWriter();
		if (CheckUtil.checkSignature(signature, timestamp, nonce)) {
			out.print(echostr);
			System.out.println("echostr:" + echostr);
		}

	}

	@PostMapping("/t")
	public void getMessge1(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		Cache cache = cacheManger.getCache("wechatCache");
		try {
			Map<String, String> map = MessageUtil.xmlToMap(req);
			String fromUserName = map.get("FromUserName");
			String toUserName = map.get("ToUserName");
			String msgType = map.get("MsgType");
			String content = map.get("Content");
			String message = null;
			System.out.println("type:" + msgType);
			String key = cache.get(fromUserName, String.class);
			logger.info("key:" + key);
			if (MessageUtil.MESSAGE_TEXT.equals(msgType)) {
				if (key != null) {
					if (content.equals("0")) {
						message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.menuText());
						cache.evict(fromUserName);
					} else {
						switch (key) {
						case "1":
							message = "电影开始查找:" + content;
							message = MessageUtil.initText(toUserName, fromUserName, "公众号正在完善.....");
							break;
						case "2":
							message = "音乐开始查找:" + content;
							pool.execute(
									new MusicRunnable(fromUserName, WeChatUtil.getAccessToken(cacheManger), content));
							message = MessageUtil.initText(toUserName, fromUserName, message);
							break;
						case "3":
							message = "稍后将发送给你:" + content;
							pool.execute(
									new MusicRunnable(fromUserName, WeChatUtil.getAccessToken(cacheManger), "接口正对接中"));
							message = MessageUtil.initText(toUserName, fromUserName, message);
							break;
						}
					}
					// message = MessageUtil.initText(toUserName, fromUserName,
					// message);

				} else {
					switch (content) {
					case "1":
						message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.movieMessage());
						cache.put(fromUserName, "1");
						break;
					case "2":
						message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.musicMessage());
						cache.put(fromUserName, "2");
						break;
					case "3":
						message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.cloudMessage());
						cache.put(fromUserName, "3");
						break;
					default:
						message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.menuText());
						cache.evict(fromUserName);
						break;
					}
				}

			} else if (MessageUtil.MESSAGE_EVNET.equals(msgType)) {
				String eventType = map.get("Event");
				// 用户关注事件
				if (MessageUtil.MESSAGE_SUBSCRIBE.equals(eventType)) {
					message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.menuText());
					// message = MessageUtil.initMusicMessage(toUserName,
					// fromUserName);
				} else if (MessageUtil.MESSAGE_CLICK.equals(eventType)) {
					message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.menuText());
				} else if (MessageUtil.MESSAGE_VIEW.equals(eventType)) {
					String url = map.get("EventKey");
					message = MessageUtil.initText(toUserName, fromUserName, url);
				}
			} else if (MessageUtil.MESSAGE_LOCATION.equals(msgType)) {
				String label = map.get("Label");
				message = MessageUtil.initText(toUserName, fromUserName, label);
			}
			System.out.println(message);
			out.println(message);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
