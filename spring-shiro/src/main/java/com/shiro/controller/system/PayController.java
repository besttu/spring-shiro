package com.shiro.controller.system;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.api.AlipayApiException;
import com.shiro.anno.Log;
import com.shiro.common.alipay.AlipayConfig;
import com.shiro.controller.BaseController;
import com.shiro.pojo.Alipay;
import com.shiro.pojo.Order;
import com.shiro.util.ServerResponse;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

@Controller
@RequestMapping("/admin/pay")
public class PayController extends BaseController {
	@Autowired
	private EhCacheCacheManager cacheManager;

	@RequiresPermissions("alipaylist")
	@RequestMapping("/alipay")
	public String aliPay() {
		return "admin/pay/alipay";
	}

	@Log("开始支付")
	@RequiresPermissions("alipay")
	@PostMapping("/doAliPay")
	public void doPay(Alipay a, Order o, String m, ServletResponse resp, HttpServletRequest req) throws IOException {
		String html = null;
		Cache cache = cacheManager.getCache("publicKey");
		try {
			if (m.equals("1")) {
				html = AlipayController.payUtils(a, o);
				cache.put(o.getNumber(), a.getPublicKey());
			} else {
				html = AlipayController.payUtils(o);
				String key = AlipayConfig.alipay_public_key;
				cache.put(o.getNumber(), key);
			}
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		resp.setContentType("text/html;charset=utf-8");
		resp.getWriter().write(html);// 直接将完整的表单html输出到页面
		resp.getWriter().flush();
	}

	@PostMapping("ali_notify_url")
	public void aliNotifyUrl(HttpServletRequest reuqest) throws AlipayApiException, UnsupportedEncodingException {
		Cache cache = cacheManager.getCache("publicKey");
		String id = new String(reuqest.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
		reuqest.setAttribute("publicKey", cache.get(id, String.class));
		boolean validate = AlipayController.validate(reuqest);
		if (validate) {
			// 商户订单号
			String out_trade_no = new String(reuqest.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
			// 支付宝交易号
			String trade_no = new String(reuqest.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
			// 付款金额
			String total_amount = new String(reuqest.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");
			String s = "支付宝订单号:" + trade_no + "订单号:" + out_trade_no + "总价:" + total_amount;
			System.out.println(s);
			System.out.println("异步回调成功");
			cache.put(id + "order", s);
		} else {
			System.out.println("验签失败");
		}
	}

	@GetMapping("ali_return_url")
	public void aliReturnUrl(HttpServletRequest reuqest, HttpServletResponse resp)
			throws AlipayApiException, IOException {
		Cache cache = cacheManager.getCache("publicKey");
		String id = new String(reuqest.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
		reuqest.setAttribute("publicKey", cache.get(id, String.class));
		boolean validate = AlipayController.validate(reuqest);
		if (validate) {
			// 商户订单号
			String out_trade_no = new String(reuqest.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
			// 支付宝交易号
			String trade_no = new String(reuqest.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
			// 付款金额
			String total_amount = new String(reuqest.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");
			PrintWriter writer = resp.getWriter();
			writer.println(
					"trade_no:" + trade_no + "<br/>out_trade_no:" + out_trade_no + "<br/>total_amount:" + total_amount);
		} else {
			System.out.println("验签失败");
		}
	}

	@Log("查询订单")
	@RequiresPermissions("queryAli")
	@GetMapping("getAlipayStatus/{id}")
	@ResponseBody
	public ServerResponse<String> getAliPayStatus(@PathVariable String id) {
		Cache cache = cacheManager.getCache("publicKey");
		String string = cache.get(id + "order", String.class);
		if (string != null) {
			return ServerResponse.createBySuccessMessage(string);
		}
		return ServerResponse.createByError();
	}

	public static void main(String[] args) {
		CacheManager manager = CacheManager
				.newInstance("D:/github/spring-shiro/spring-shiro/src/main/resources/ehcache/ehcache.xml");
		net.sf.ehcache.Cache cache = manager.getCache("publicKey");
		cache.put(new Element("123", "asdfsdf"));
		System.out.println(cache.get("123").getValue());
	}

}
