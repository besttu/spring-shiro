package com.shiro.controller.system;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.shiro.common.alipay.AlipayConfig;
import com.shiro.controller.BaseController;
import com.shiro.pojo.Alipay;
import com.shiro.pojo.Order;

@Controller
@RequestMapping("/alipay")
public class AlipayController extends BaseController {
	public static String payUtils(Alipay ali, Order order) throws AlipayApiException {
		if (StringUtils.isAnyEmpty(ali.getAppId(), ali.getGatewayUrl(), ali.getPrivateKey(), ali.getPublicKey(),
				ali.getNotifyUrl())) {
			throw new RuntimeException("支付配置参数错误");
		}
		if (StringUtils.isAnyEmpty(order.getNumber(), order.getPrice(), order.getTitle())) {
			throw new RuntimeException("订单参数错误");
		}
		AlipayClient alipayClient = new DefaultAlipayClient(ali.getGatewayUrl(), ali.getAppId(), ali.getPrivateKey(),
				"json", ali.getCharSet(), ali.getPublicKey(), ali.getSignType());

		// 设置请求参数
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		alipayRequest.setReturnUrl(ali.getReturnUrl());
		alipayRequest.setNotifyUrl(ali.getNotifyUrl());
		String out_trade_no = order.getNumber();
		// 付款金额，必填
		String total_amount = order.getPrice();
		// 订单名称，必填
		String subject = order.getTitle();
		// 商品描述，可空
		String body = order.getDesc();
		alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\"," + "\"total_amount\":\"" + total_amount
				+ "\"," + "\"subject\":\"" + subject + "\"," + "\"body\":\"" + body + "\","
				+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

		return alipayClient.pageExecute(alipayRequest).getBody();

	}

	public static String payUtils(Order order) throws AlipayApiException {
		// 获得初始化的AlipayClient
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id,
				AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key,
				AlipayConfig.sign_type);
		// 设置请求参数
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		alipayRequest.setReturnUrl(AlipayConfig.return_url);
		alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
		if (StringUtils.isAnyEmpty(order.getNumber(), order.getPrice(), order.getTitle())) {
			throw new RuntimeException("订单参数错误");
		}

		String out_trade_no = order.getNumber();
		// 付款金额，必填
		String total_amount = order.getPrice();
		// 订单名称，必填
		String subject = order.getTitle();
		// 商品描述，可空
		String body = order.getDesc();
		alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\"," + "\"total_amount\":\"" + total_amount
				+ "\"," + "\"subject\":\"" + subject + "\"," + "\"body\":\"" + body + "\","
				+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

		return alipayClient.pageExecute(alipayRequest).getBody();

	}

	public String index() {
		return "admin/pay/alipay";
	}

	@RequestMapping("/pay")
	public void getCode(ServletResponse resp) throws AlipayApiException, IOException {
		// 获得初始化的AlipayClient
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id,
				AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key,
				AlipayConfig.sign_type);

		// 设置请求参数
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		alipayRequest.setReturnUrl(AlipayConfig.return_url);
		alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
		// 商户订单号，商户网站订单系统中唯一订单号，必填
		String out_trade_no = (int) (Math.random() * 100888) + "";
		// 付款金额，必填
		String total_amount = "1";
		// 订单名称，必填
		String subject = "订单名称";
		// 商品描述，可空
		String body = "商品描述";
		alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\"," + "\"total_amount\":\"" + total_amount
				+ "\"," + "\"subject\":\"" + subject + "\"," + "\"body\":\"" + body + "\","
				+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
		// 请求
		String result = alipayClient.pageExecute(alipayRequest).getBody();
		System.out.println(result);
		resp.setContentType("text/html;charset=utf-8");
		resp.getWriter().write(result);// 直接将完整的表单html输出到页面
		resp.getWriter().flush();
	}

	/**
	 * 异步通知
	 * 
	 * @throws UnsupportedEncodingException
	 */
	@PostMapping("/notify_url")
	public void notifyUrl(ServletRequest request) throws UnsupportedEncodingException {
		// 获取支付宝POST过来反馈信息
		Map<String, String> params = new HashMap<String, String>();
		Map<String, String[]> requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用
			// valueStr = new String(valueStr.getBytes("ISO-8859-1"),
			// "utf-8");
			params.put(name, valueStr);
		}
		boolean signVerified = false;
		try {
			signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset,
					AlipayConfig.sign_type);
			System.out.println("sign:" + signVerified);
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 调用SDK验证签名

		if (signVerified) {// 验证成功
			// 商户订单号
			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

			// 支付宝交易号
			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

			// 交易状态
			String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");

			if (trade_status.equals("TRADE_FINISHED")) {
				// 判断该笔订单是否在商户网站中已经做过处理
				// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				// 如果有做过处理，不执行商户的业务程序

				// 注意：
				// 退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
			} else if (trade_status.equals("TRADE_SUCCESS")) {
				// 判断该笔订单是否在商户网站中已经做过处理
				// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				// 如果有做过处理，不执行商户的业务程序

				// 注意：
				// 付款完成后，支付宝系统发送该交易状态通知
			}

			System.out.println("success");

		} else {// 验证失败
			System.out.println("fail");

			// 调试用，写文本函数记录程序运行情况是否正常
			// String sWord = AlipaySignature.getSignCheckContentV1(params);
			// AlipayConfig.logResult(sWord);
		}

	}

	public static boolean validate(HttpServletRequest reuqest) throws AlipayApiException {
		String key = (String) reuqest.getAttribute("publicKey");
		System.out.println("key:" + key);
		Map<String, String> params = new HashMap<String, String>();
		Map<String, String[]> requestParams = reuqest.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用
			// valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name.trim(), valueStr.trim());
		}
		boolean signVerified = AlipaySignature.rsaCheckV1(params, key, AlipayConfig.charset, AlipayConfig.sign_type); // 调用SDK验证签名
		return signVerified;
	}

	@GetMapping("return_url")
	public void returnUrl(HttpServletRequest reuqest) throws UnsupportedEncodingException, AlipayApiException {
		// 获取支付宝GET过来反馈信息
		Map<String, String> params = new HashMap<String, String>();
		Map<String, String[]> requestParams = reuqest.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用
			// valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name.trim(), valueStr.trim());
		}

		boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset,
				AlipayConfig.sign_type); // 调用SDK验证签名

		// ——请在这里编写您的程序（以下代码仅作参考）——
		if (signVerified) {
			// 商户订单号
			String out_trade_no = new String(reuqest.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

			// 支付宝交易号
			String trade_no = new String(reuqest.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

			// 付款金额
			String total_amount = new String(reuqest.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");

			System.out.println(
					"trade_no:" + trade_no + "<br/>out_trade_no:" + out_trade_no + "<br/>total_amount:" + total_amount);
		} else {
			System.out.println("验签失败");
		}
	}

	public static void main(String[] args) {

	}
}
