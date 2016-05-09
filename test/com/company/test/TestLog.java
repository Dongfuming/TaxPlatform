package com.company.test;

import org.junit.Test;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 测试Log4j
 * @author Dongfuming
 * 2016-5-9 上午9:51:58
 */
public class TestLog {
	
	@Test
	public void testLog() {
		Log log = LogFactory.getLog(getClass());
		log.debug("log4j debug");
		log.info("log4j info");
		log.warn("log4j warn");
		log.error("log4j error");
		log.fatal("log4j fatal");
	}
}
