package test;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

public class Test_PassengerTicket {
	public static void main(String[] args) throws Exception {

		//427456378.20480.0000     e6bd9-5689-4e2561b8af900

		//String server_address = "http://www.travelsky.com/newsky/index.html";
		//String server_address = "http://www.travelsky.com";
		long time = System.currentTimeMillis();
		String server_address = "http://www.travelsky.com/tsky/servlet/CallYanServlet";
		System.out.println(server_address);
		URL url = new URL(server_address);
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
		System.out.println("-------response---->>" + response);
		InputStream is = httpconection.getInputStream();
		FileOutputStream fos = new FileOutputStream(new File("D://aa.jpg"));
		byte[] array_cache = new byte[1024];
		int len = 0;
		while ((len = is.read(array_cache)) > -1) {
			fos.write(array_cache, 0, len);
		}
		fos.flush();
		fos.close();
		System.out.println("--------½áÊø-------->>");
		System.out.println(cookielist.get(0));
		System.out.println(cookielist.get(1));
		//Thread.sleep(10000);
		getinformation(cookielist);
	}

	public static void getinformation(List<String> cookielist) throws Exception {

		long time = System.currentTimeMillis();
		String randCode = "wa4s";
		Scanner scan = new Scanner(System.in);
		randCode = scan.next();
		//7812148781836
		String server_address = "http://www.travelsky.com/tsky/validate";
		System.out.println(server_address);
		URL url = new URL(server_address);
		HttpURLConnection httpconection = (HttpURLConnection) url.openConnection();
		httpconection.setDoInput(true);
		httpconection.setDoOutput(true);
		httpconection.setRequestMethod("POST");
		httpconection.setUseCaches(false);
		httpconection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		httpconection.setRequestProperty("User-Agent",
				"Mozilla/5.0 (X11; U; Linux x86_64; zh-CN; rv:1.9.2.9) Gecko/20100827 Red Hat/3.6.9-2.el6 Firefox/3.6.9 ");
		String[] cookstr = cookielist.get(1).split(";");
		System.out.println(cookstr[0]);
		System.out.println(randCode);
		httpconection.setRequestProperty("Cookie", cookstr[0] + "; path=/");
		httpconection.connect();
		//String content = "validateFlag=0&eticketNo=8802170671173&invoiceNo=&imgSrc=%2Ftsky%2Fimages%2Floading.gif&eticketNoORIn=8802170671173&passengerName_src=%E9%9B%B7%E9%B8%A3&passengerName=%25E9%259B%25B7%25E9%25B8%25A3&randCode="
		//+ randCode + "&rcvFlag=";
		String content = "validateFlag=0&eticketNo=88021706671173&invoiceNo=&imgSrc=%2Ftsky%2Fimages%2Floading.gif&eticketNoORIn=88021706671173&passengerName_src=%E9%9B%B7%E9%B8%A3&passengerName=%25E9%259B%25B7%25E9%25B8%25A3&randCode="
				+ randCode + "&rcvFlag=";
		OutputStream out = httpconection.getOutputStream();
		DataOutputStream dos = new DataOutputStream(out);
		dos.writeBytes(content);
		dos.flush();
		System.out.println("************************ResponseHeaders******************************");
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
		//out.flush();
		System.out.println("\n************************Response******************************");
		int response = httpconection.getResponseCode();
		System.out.println("-------response---->>" + response);
		InputStream is = httpconection.getInputStream();
		/*
		 * ByteArrayOutputStream baos = new ByteArrayOutputStream(); byte[] array_cache = new byte[1024]; int len = 0; while ((len =
		 * is.read(array_cache)) > -1) { baos.write(array_cache, 0, len); } String str = new String(baos.toByteArray(), "utf-8");
		 * System.out.println(str);
		 */
		FileOutputStream fos = new FileOutputStream(new File("D://cc.xml"));
		byte[] array_cache = new byte[1024];
		int len = 0;
		while ((len = is.read(array_cache)) > -1) {
			fos.write(array_cache, 0, len);
		}
		fos.flush();
		fos.close();
		System.out.println("--------½áÊø-------->>");
		out.close();

	}
}
