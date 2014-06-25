package test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test_SLF4J {
	public static void main(String[] args) {

		Logger logger = LoggerFactory.getLogger(Test_SLF4J.class);
		logger.error(" Temperature set to {}. Old temperature was {}. ", 55, "sjldkfjlksajdflkjl");

	}
}
