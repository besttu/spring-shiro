package com.shiro.service;

import java.util.List;

import com.shiro.entity.SysMenu;
import com.shiro.pojo.TreeMenu;

public interface MenuService {

	List<TreeMenu> getRootMenu();

}
