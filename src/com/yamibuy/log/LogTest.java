package com.yamibuy.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class LogTest {
	private static Logger logger = LogManager.getLogger("LogTest");
	static
	{
	     System.setProperty("mail.smtp.auth", "true");
	     System.setProperty("mail.smtp.socketFactory.port", "465");
	     System.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	     System.setProperty("mail.smtp.socketFactory.fallback", "false");
	     System.setProperty("mail.smtp.starttls.enable","true");
	     System.setProperty("mail.transport.protocol", "smtp");
	     System.setProperty("mail.smtp.starttls.enable", "true");
	     //System.setProperty("mail.smtp.host", "smtp.gmail.com");
	     //System.setProperty("mail.smtp.port", "465");
	     System.setProperty("mail.smtp.quitwait", "false");
	}
	public void testlogger() {
		logger.info("test info"); // does not log
		logger.error("test error"); // logs!
	}

	public static void main(String[] args) {
		LogTest app = new LogTest();
		app.testlogger();
	}
}
