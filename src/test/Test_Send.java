package test;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Test_Send {

	public static void testDom() {

		String path = "D://TestServer/w2.jpg";
		//String url1 = "http://192.168.3.52:8080/XCFH/usermanager/backupxml.action?uid=9c97fd4c108f4a9b970afb48a8ab6a5e";
		//String url1 = "http://192.168.3.52:8080/XCFH_ALIYUM/usermanager/backupxml.action?uid=9c97fd4c108f4a9b970afb48a8ab6a5e";
		//String url1 = "http://115.29.35.72/XCFH/usermanager/backupxml.action?uid=9c97fd4c108f4a9b970af";
		//String url1 = "http://127.0.0.1:8080/XCFH/userfeedback/userfeedback";
		String url1 = "http://127.0.0.1:8080/XCFH_INVOICE/invoiceproving/selectinvoice.action";
		URL url;
		OutputStream outputStream = null;
		String xmlData = "<?xml version='1.0' encoding='gb2312'?>" + "<version>" + "<apk_version>" + "4.088888888888888" + "</apk_version>" + "<config_version>" + "5.0" + "</config_version>"
				+ "</version>";
		xmlData = "<?xml version='1.0' encoding='gb2312'?><xcfh><feedback_content>中国</feedback_content><user_contact>185646975825</user_contact></xcfh>";
		byte[] xmld = xmlData.getBytes();
		try {
			/*
			 * 打印请求头 System.out.println("——————————请求头开始————————————"); Enumeration enumeration =
			 * ServletActionContext.getRequest().getHeaderNames(); while
			 * (enumeration.hasMoreElements()) { String str = (String) enumeration.nextElement();
			 * System.out.println(str + ":\t" + ServletActionContext.getRequest().getHeader(str)); }
			 * System.out.println("************************请求头结束————————————");
			 */
			url = new URL(url1);
			File file = new File(path);
			FileInputStream fis = new FileInputStream(file);
			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
			httpURLConnection.setConnectTimeout(3000);
			httpURLConnection.setDoInput(true);
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setRequestMethod("POST");
			httpURLConnection.setUseCaches(false);
			httpURLConnection.setRequestProperty("Content-Type", "text/xml");
			//httpURLConnection.setRequestProperty("Content-length", file.length() + "");
			httpURLConnection.setRequestProperty("Content-length",xmld.length +"");
			// String.valueOf(xmld.length));
			outputStream = httpURLConnection.getOutputStream();
			DataOutputStream dos = new DataOutputStream(outputStream);
			dos.write(xmld);
			/*
			 * byte[] cache_array = new byte[1024]; int len = 0; while ((len =
			 * fis.read(cache_array)) > -1) { dos.write(cache_array, 0, len); }
			 */
			dos.flush();
			dos.close();
			/*
			 * inputStream2 = httpURLConnection.getInputStream(); int len = 0; byte[] data = new
			 * byte[1024]; while ((len = inputStream.read(data)) != -1) { outputStream.write(data,
			 * 0, len); }
			 */
			int response = httpURLConnection.getResponseCode();
			System.out.println("-------response---->>" + response);
			InputStream is = httpURLConnection.getInputStream();
			int size = is.available();
			char[] ch = new char[size];
			for (int i = 0; i < size; i++) {
				ch[i] = (char) is.read();
			}
			String str = new String(ch);
			System.out.println(str);
			System.out.println("--------结束-------->>");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				outputStream.flush();
				outputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {

		int i = 0;
		while (i < 1) {
			testDom();
			i++;
			System.out.println("-----------------" + i + "------------------");
		}
	}
}