package test.invoice.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Test_Invoice {
	public List getCode(String codeIP) throws Exception {

		System.out.println("----------------------------getCode---START-------------------------------");
		System.out.println("codeIP============" + codeIP);
		URL url = new URL(codeIP);
		HttpURLConnection httpconection = (HttpURLConnection) url.openConnection();
		List cookielist = null;
		httpconection.setDoInput(true);
		httpconection.setDoOutput(true);
		httpconection.setRequestMethod("GET");
		httpconection.setUseCaches(false);
		httpconection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.2; WOW64; rv:27.0) Gecko/20100101 Firefox/27.0");

		System.out.println("************************Response******************************");
		Map topheader = httpconection.getHeaderFields();
		Set headerSet = topheader.entrySet();
		Iterator it = headerSet.iterator();
		while (it.hasNext()) {
			System.out.println();
			Entry<String, List> en = (Entry<String, List>) it.next();
			System.out.print((String) en.getKey());
			if ("set-cookie".equalsIgnoreCase(en.getKey())) {
				cookielist = en.getValue();
			}
			for (int i = 0; i < en.getValue().size(); i++) {
				System.out.print("\t:" + en.getValue().get(i));
			}
		}
		System.out.println();
		System.out.println("************************ResponseBody******************************");
		int response = httpconection.getResponseCode();
		InputStream is = httpconection.getInputStream();
		FileOutputStream fos = new FileOutputStream(new File("D://aa.jpg"));
		byte[] array_cache = new byte[1024];
		int len = 0;
		while ((len = is.read(array_cache)) > -1) {
			fos.write(array_cache, 0, len);
		}
		fos.flush();
		fos.close();
		System.out.println("----------------------------getCode---END-------------------------------");
		return cookielist;
	}

	public void getInformation(String invoiceIP, List cookielist) throws Exception {

		System.out.println("----------------------------getInvormation---START------------------------");
		System.out.println("invoiceIP============" + invoiceIP);
		URL url = new URL(invoiceIP);
		HttpURLConnection httpconection = (HttpURLConnection) url.openConnection();
		httpconection.setDoInput(true);
		httpconection.setDoOutput(true);
		httpconection.setRequestMethod("GET");
		httpconection.setUseCaches(false);
		httpconection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.2; WOW64; rv:27.0) Gecko/20100101 Firefox/27.0");
		StringBuffer cookieStr = new StringBuffer();
		int j = 0;
		do {
			cookieStr.append(cookielist.get(j) + ";");
			j++;
		} while (j < cookielist.size());

		httpconection.setRequestProperty("Cookie", cookieStr.toString());
		System.out.println("************************ResponseHeaders******************************");
		Map topheader = httpconection.getHeaderFields();
		Set headerSet = topheader.entrySet();
		Iterator it = headerSet.iterator();
		while (it.hasNext()) {
			System.out.println();
			Entry<String, List> en = (Entry<String, List>) it.next();
			System.out.print((String) en.getKey());
			for (int i = 0; i < en.getValue().size(); i++) {
				System.out.print("\t:" + en.getValue().get(i));
			}
		}
		System.out.println("************************Response******************************");
		int response = httpconection.getResponseCode();
		InputStream is = httpconection.getInputStream();
		FileOutputStream fos = new FileOutputStream(new File("D://bb.xml"));
		byte[] array_cache = new byte[1024];
		int len = 0;
		while ((len = is.read(array_cache)) > -1) {
			fos.write(array_cache, 0, len);
		}
		fos.flush();
		fos.close();
		System.out.println("----------------------------getInvormation---END------------------------");

	}
}
