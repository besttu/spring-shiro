package com.shiro.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.shiro.pojo.MusicJson;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath:spring/applicationContext-*.xml" })
public class TestAdmin {

	//
	// @Autowired
	// private RoleService sysRoleService;
	// @Autowired
	// private RoleMenuService sysRoleMenuService;
	// @Autowired
	// private MenuService sysMenuService;
	// @Autowired
	// private UserService sysUserService;
	//
	// /**
	// * 创建一个Admin用户
	// */
	// @Test
	// public void addAdmin() {
	// // sysMenuService.getAllTree();
	// }
	//
	// /**
	// * 修改Admin的密码位123456
	// */
	// @Test
	// public void changeAdminPwd() {
	// // MD5,"密码","盐",加密次数
	// String pwd = new SimpleHash("MD5", "123456", "admin", 1024).toString();
	// System.out.println(pwd);
	// }
	//
	public static void main(String[] args) {
		String[] arguments = new String[] { "python", "d:\\lib\\KuWo.py ", "穿越" };
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
			System.out.println(re == 0);
			List<MusicJson> fromJson = g.fromJson(sb.toString(), ArrayList.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
