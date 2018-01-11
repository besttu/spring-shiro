package com.shiro.common.alipay;

import java.io.FileWriter;
import java.io.IOException;

public class AlipayConfig {

	// ↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016082700320930";

	// 商户私钥，您的PKCS8格式RSA2私钥
	public static String merchant_private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCWsiGh/GfPeQxdZ8Efz3c94mIha/99nj9t9gaSIN1gxXVQPAQydwubyyO3fdUj4i+3dTGgIkyzf629SSuMdiF41mjS8hK1csPoSufrLmojWcT3mVMEGYPDPylHJSd0R3IqyfAT4rtWxGfi8iAa6FWsroyG7XDv4olh7bojDedWjNMpZs0BpnA4sk5vAe6c3+nqfRusakMnftK83wY7Cht81qpx5G+n4z0RpS2QrQB35c2KQmwBEAtpuVUFYzv+nkvlz2kzUqrxLSIBbFlgR2suJzzx7zB4xKnxFQOcWS/pdk4QV3Es0BzYsFbbO3pmz0hbK7c/U3t0FnqpKx2Fs+WtAgMBAAECggEADmYa3JV3fdV9Hveo9lcmd51H7xWYpfIW8XTOj+vf+z0ixnXVmNTTISG52fCGCxWzpX3nLfvZBFWwziNNq/AlppwmC/HhY8EBgxKVMl2O+q27KkVWL1AYzPEeCpQ8PtoMyJXfAKdraxD9NCwacTe95/ANsV7Cqcklg1vpqyvK7PQGJaX9+f/ITIYxTSLqMTXy2RFMs3u2VDf+xf3Ce4k66iwlmMeWEq+htEyB4zPcPLNreoEcH5EpOt6Mf4Bxk52tAsPFY+5wvqkE7vqyCse3Ve7br4yQJuJuCBcOiBRpPYRaQawVHm0jmndF9U18QDTxqj7fnTZ2g0IHnciGKegRaQKBgQDRCNkbtSj3d6KV88CHdM9E92iXL57eJKCXUb9LEYGy8c1bHQdjia+0a55R7KGIKpQy4pzACrtlYeLCHXLuVdD1Y6+LZFrP17rOUTwXbPSoBjphEMZ3VF/JveE1/gDLp/GGeIqik2IvkqIFytLeK1YgiHeTzoh8tONoph03qZ2cMwKBgQC4jch+2fMywKHrh23o09fl/cj6W5TytZa7DRMex0P0TO9RlT6ORggu9RU9mtDeZq3oQEZPdekEDo1CH0D1mSJYiMwNkfrae2iILh5kOwoR/WaJsOiiG4o0Oyuu6TOgRd5ms+mjFKK+mmZGvQh5qL3Xe6+QwRTBrmp4ThtkFY6WnwKBgB7jQ9GIT2lh0DCAOC7L3+S8+kN80ejZWLGDbtTU3WlnuYSBIZpfugra7CYG5UeNoyBxw9/Mtiwg7rToTRdSrvHTrRjX12Nm6T+bWVnMy8oYQYAeTnVLjmvtFt43jj6JrnLEVRf410VYUYCxm78ggDWGdMTue9SRlhjckuyqr5NpAoGACwNzDpblG4dBYnPfjEBwJuqBPueOOPm7OAhstq5/kKmysnSsmgL15A9+KAIAKvWHtU94oq9Pq41Owr5ouv9bfFsVik3G4ZjxwQa3i5W2YSJ1JwxmpZ3tHnbVgYerLApfXlOoQrItkCBYsCoBwnjec2iPLp1HJtPIyMsoeU2MB9MCgYAHsA3CwiiZlAU0pU9Hu+0+BAUZd74Po+zyVZX5wHYYel9FSmWdyDQIvAJAS7NBe0zQfzbOJ8gFospy3pzmr8EddPzN1x54yLJW59YCHsztPxWz5nb9AZG9x8vlQ9ojUgU9oy7BvC+S/jToD15IzhiE37/mGvgWQmpTeNleHBXSwg==";
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm
	// 对应APPID下的支付宝公钥。
	public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqBxG6dpUcW91VYL/hNzvdtNpNDEFqVxSlnYxbix+qP0ZiQMtHoKyUUvwb9zCW70AD5Sax+sJDcGsB8SttW7T/H4ElxCURw43eEuc466BcEaMHfgkXMREJ2hFwZZJPFyqP+cWN/LWO3QVJT6qk26lq/h7ndn35jXLHu0QB/HJB+ifwettmJ4YlQeO62QSI/U0h1FRZ9v6PlxyuykpSdsPy5pJRH0E8ghdRlOkwGq2XGuSCh35ngrfh2H3rFy05SOH5oKVpqdHptSeLqjWyOzIRUI4hvfczjIGt6mBvO50+3SLSTSJfJz3tzujBzKyVLTE7K41Ggl2dB/QM9Z1qmV60QIDAQAB"; // 服务器异步通知页面路径
																																																																																																															// 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://kjgj84.natappfree.cc/admin/pay/ali_notify_url";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://kjgj84.natappfree.cc/admin/pay/ali_return_url";

	// 签名方式
	public static String sign_type = "RSA2";

	// 字符编码格式
	public static String charset = "utf-8";

	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

	// 支付宝网关
	public static String log_path = "https://openapi.alipaydev.com/gateway.do";

	// ↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

	/**
	 * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
	 * 
	 * @param sWord
	 *            要写入日志里的文本内容
	 */
	public static void logResult(String sWord) {
		FileWriter writer = null;
		try {
			writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis() + ".txt");
			writer.write(sWord);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
