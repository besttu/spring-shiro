package com.shiro.controller.system;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shiro.controller.BaseController;
import com.shiro.entity.SysLog;
import com.shiro.pojo.DataTable;
import com.shiro.service.LogService;

@Controller
@RequestMapping("admin/log/")
public class LogController extends BaseController {
	@Autowired
	LogService logService;

	@RequestMapping("/list")
	public String index() {
		return "admin/log/list";
	}

	@RequiresPermissions("listLog")
	@RequestMapping("getAll")
	@ResponseBody
	public DataTable<SysLog> getAll(int draw, @RequestParam(defaultValue = "0") int start, int length,
			HttpServletRequest req, Date start_date, Date end_date) {
		System.out.println(start_date);
		System.out.println(end_date);
		String search = req.getParameter("search[value]");
		return logService.getAll(draw, start, length, search, start_date, end_date);
	}
}
