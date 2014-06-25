package test;

import java.io.File;

public class CopyXML {
	public static void main(String[] args) throws Exception {

		File file = new File("D://TDDOWNLOAD/rhel-server-6.0-x86_64-boot.iso");
		//if(!file.exists()) file.mkdirs();
		//file.createNewFile();
		System.out.println(file.getName());
		System.out.println((int) file.length());
		System.out.println(file.getPath());
		System.currentTimeMillis();
		System.out.println(Runtime.getRuntime().availableProcessors());
	}
}
