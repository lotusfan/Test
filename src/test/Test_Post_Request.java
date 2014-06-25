package test;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Test_Post_Request {
	public static void main(String[] args) throws Throwable {

		String address = "http://www.csj.sh.gov.cn/wsbs/WSBSptFp_cxjg.jsp";
		String yzm = "2j3g";
		String JSESSIONID = "TZrGpNW7kYy8LyQpdTnTnqhY2X47PCYxTfr62kc2W1qmTFkmxRGV!-885052465; path=/";
		Map<String, String> attributeMap = new HashMap<String, String>();
		attributeMap.put("invoiceNo", "23100137025240011998");
		attributeMap.put("random", "-196483046");
		attributeMap.put("revenueRegisterId", "310108076431237");
		attributeMap.put("yzm", yzm);
		attributeMap.put("hidden", "");
		InputStream in = sendResultRequestPost(address, attributeMap, JSESSIONID);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		StringBuffer sb = new StringBuffer();
		String str = "";
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int len = -1;
		while ((len = in.read()) != -1) {
			baos.write(len);
		}
		/*
		 * while ((str = br.readLine()) != null) { sb.append(str); sb.append("\n"); }
		 */
		String str_return = new String(baos.toByteArray(), "UTF-8");
		System.out.println(str_return);
		System.out.println(str_return.substring(str_return.indexOf("<strong>") + 8, str_return.indexOf("</strong>")).replace("<br>", ""));
	}

	public static InputStream sendResultRequestPost(String requestAddress, Map attributeMap, String JSESSIONID) {

		InputStream in = null;
		OutputStream out = null;
		DataOutputStream dos = null;
		String attributeString = null;
		System.out.println(requestAddress);
		System.out.println(attributeMap);
		try {
			URL url = new URL(requestAddress);
			HttpURLConnection httpconection = (HttpURLConnection) url.openConnection();
			httpconection.setDoInput(true);
			httpconection.setDoOutput(true);
			httpconection.setRequestMethod("POST");
			httpconection.setUseCaches(false);
			httpconection.setRequestProperty("User-Agent",
					"Mozilla/5.0 (X11; U; Linux x86_64; zh-CN; rv:1.9.2.9) Gecko/20100827 Red Hat/3.6.9-2.el6 Firefox/3.6.9 ");
			httpconection.setRequestProperty("Cookie", "JSESSIONID=" + JSESSIONID);

			//构造POST参数
			StructureAttributePost sap = new StructureAttributePost();
			attributeString = sap.structureAttributePost(attributeMap);
			//发送参数流
			out = httpconection.getOutputStream();
			dos = new DataOutputStream(out);
			dos.writeBytes(attributeString);
			dos.flush();
			in = httpconection.getInputStream();//获取发票验真返回信息
			return in;
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				if (out != null) out.close();
				if (dos != null) dos.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return null;
	}
}
