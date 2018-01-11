package com.shiro.util.we;

/**
 * 网盘线程
 * 
 * @author admin
 *
 */
public class CloudRunnable implements Runnable {
	private String name;

	public CloudRunnable(String name) {
		// TODO Auto-generated constructor stub
		this.name = name;
	}

	public void run() {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
