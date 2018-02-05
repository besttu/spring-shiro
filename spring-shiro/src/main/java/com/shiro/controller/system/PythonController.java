package com.shiro.controller.system;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shiro.controller.BaseController;
import com.shiro.pojo.we.Movie;
import com.shiro.service.impl.MovieServiceImpl;

@Controller
@RequestMapping("/python")
public class PythonController extends BaseController {
	ExecutorService newCachedThreadPool = Executors.newSingleThreadExecutor();
	@Autowired
	MovieServiceImpl movieService;

	@GetMapping("/{path}/{arg}")
	public void index(@PathVariable String path, @PathVariable String arg, ServletResponse resp) {
		System.out.println("path:" + path);
		System.out.println("arg:" + arg);
		newCachedThreadPool.execute(new RunnableImple(path, arg));
		try {
			List<Movie> listByLike = movieService.getListByLike("周星驰", "成龙");
			System.out.println(listByLike.toString());
			resp.getOutputStream().write("success".getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

class RunnableImple implements Runnable {
	String arg;
	String path;
	Logger log = LoggerFactory.getLogger(RunnableImple.class);

	public RunnableImple(String path, String arg) {
		this.arg = arg;
		this.path = path;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("run");
		String[] arguments = null;
		if (arg.equals("nn")) {
			System.out.println("nn");
			arguments = new String[] { "python", "/root/python/" + path };
		} else {
			arguments = new String[] { "python", "/root/python/" + path, arg };
		}
		try {
			Runtime runtime = Runtime.getRuntime();
			runtime.exec(arguments);
			Process process = Runtime.getRuntime().exec(arguments);
			BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line = null;
			while ((line = in.readLine()) != null) {
				log.info(line);
			}
			in.close();
			int re = process.waitFor();
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

}
