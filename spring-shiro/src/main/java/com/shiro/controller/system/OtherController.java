package com.shiro.controller.system;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.baidu.aip.ocr.AipOcr;
import com.shiro.anno.Log;
import com.shiro.controller.BaseController;

@Controller
@RequestMapping("/admin/other")
public class OtherController extends BaseController {
	// 设置APPID/AK/SK
	public static final String APP_ID = "10779016";
	public static final String API_KEY = "AAcDVcD1VHhE5oiwj4O0OBy6";
	public static final String SECRET_KEY = "MiLlkd3ikGcKSsKhCpsr2QjsiNGO2awU";

	@GetMapping("/recognize")
	public String index() {
		return "admin/other/recogize";
	}

	@Log("上传图片")
	@PostMapping("/upload")
	public void uploadPicture(MultipartFile file, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws IllegalStateException, IOException {
		System.out.println("upload");
		System.out.println("upload" + file.getName());
		if (file != null) {// 判断上传的文件是否为空
			byte[] bytes = file.getBytes();
			// 初始化一个AipOcr
			AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

			// 可选：设置网络连接参数
			client.setConnectionTimeoutInMillis(2000);
			client.setSocketTimeoutInMillis(60000);
			JSONObject basicGeneral = client.basicGeneral(bytes, new HashMap<String, String>());
			JSONArray arrays = basicGeneral.getJSONArray("words_result");
			StringBuilder sb = new StringBuilder();
			for (Object s : arrays) {
				sb.append(s);
			}
			int number = basicGeneral.getInt("words_result_num");
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter pw = response.getWriter();
			pw.print("word:" + sb.toString() + "number:" + number);
			System.out.println(bytes.length);
		}
	}

}
