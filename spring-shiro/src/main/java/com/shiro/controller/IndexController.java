package com.shiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	@RequestMapping("/{path}")
	public String index(@PathVariable String path) {
		System.out.println("path:" + path);
		return path;
	}

}
