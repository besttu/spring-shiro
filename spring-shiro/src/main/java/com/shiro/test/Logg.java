package com.shiro.test;

import org.apache.log4j.spi.LoggerFactory;
import org.slf4j.Logger;

public class Logg {

	public static void main(String[] args) {
		Logger log = org.slf4j.LoggerFactory.getLogger(Logg.class);
		log.info("zhagnsan");
	}

}
