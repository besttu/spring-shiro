package com.shiro.controller.system;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shiro.dao.SysSettingMapper;
import com.shiro.entity.SysSetting;
import com.shiro.service.SettingService;
import com.shiro.util.ServerResponse;

@Controller
@RequestMapping("admin/Setting")
public class SettingController {
	@Autowired
	SettingService settingService;

	@RequiresPermissions("listSetting")
	@GetMapping("/list")
	public String index(Model m) {
		List<SysSetting> selectByExample = settingService.getAll();
		m.addAttribute("lists", selectByExample);
		return "admin/setting";
	}

	@RequiresPermissions("doSetting")
	@PostMapping("save")
	@ResponseBody
	public ServerResponse<String> save(String[] id, String[] sysvalue) {
		for (int i = 0; i < id.length; i++) {
			SysSetting setting = new SysSetting();
			setting.setId(id[i]);
			setting.setSysvalue(sysvalue[i]);
			settingService.update(setting);
		}
		settingService.clearCacheSetting();
		return ServerResponse.createBySuccess();
	}
}
