package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class Parse_XCFH_Properties {
	Properties properties = new Properties();

	public Parse_XCFH_Properties() {

		try {
			this.parsePrperties();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public Properties parsePrperties() throws Exception {

		InputStream is = this.getClass().getResourceAsStream("/xcfh.properties");
		properties.load(new FileInputStream(new File("D://xcfh.properties")));
		properties.setProperty("lkjasdlkfj", "sadfsjfkjshdfkjh");
		System.out.println(properties.size());
		is.close();
		File file = new File("D://xcfh.properties");
		System.out.println(file.exists());
		OutputStream os = new FileOutputStream(file);

		properties.store(new FileOutputStream(file), null);
		os.flush();
		os.close();
		return null;
	}

	public String getProperties(String str) {

		return properties.getProperty(str);
	}

	public static void main(String[] args) {

		try {
			Parse_XCFH_Properties pr = new Parse_XCFH_Properties();
			System.out.println(pr.getProperties("LOCALPATH"));

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
