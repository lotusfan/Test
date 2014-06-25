package test;

import org.apache.log4j.Logger;

public class Test_Log4j {
	public static void main(String[] args) {

		Test_Log4j tt = new Test_Log4j();
		tt.getClass().getName();
		Logger logger = Logger.getLogger("mylog");
		logger.info(tt.getClass().getName() + "---------------------info------------------");
		logger.error("--------------------error-----------------");
		logger.debug("------------------debug----------------");
	}
}
