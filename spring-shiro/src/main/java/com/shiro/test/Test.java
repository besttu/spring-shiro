package com.shiro.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class Test {
	public static void main(String[] args) {
		System.out.println("begin");
		System.out.println(new Test().execFIile("hello"));
	}

	public static String execFIile(String word) {
		String[] arguments = new String[] { "python", "d://lib//KuWo.py ", word };
		try {
			Runtime runtime = Runtime.getRuntime();
			runtime.exec(arguments);
			System.out.println(runtime);
			Process process = Runtime.getRuntime().exec(arguments);
			BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = in.readLine()) != null) {
				sb.append(line);
			}
			in.close();
			int re = process.waitFor();
			return re == 0 ? sb.toString() : null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
