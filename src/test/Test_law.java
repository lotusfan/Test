package test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Test_law {

	public static void main(String[] args) {

		InputStream in = null;
		OutputStream out = null;
		try {
			String str = "http://www.live.chinacourt.org/fygg/pdf/id/2097937.pdf";
			String str1 = "http://www.live.chinacourt.org/fygg/search.shtml?keyword=40200051&button2=%E6%8F%90%E4%BA%A4";
			URL url = new URL(str1);
			HttpURLConnection httpconection = (HttpURLConnection) url.openConnection();
			httpconection.setDoInput(true);
			httpconection.setDoOutput(true);
			httpconection.setRequestMethod("GET");
			httpconection.setUseCaches(false);
			httpconection.setRequestProperty("User-Agent",
					"Mozilla/5.0 (X11; U; Linux x86_64; zh-CN; rv:1.9.2.9) Gecko/20100827 Red Hat/3.6.9-2.el6 Firefox/3.6.9 ");

			in = httpconection.getInputStream();//获取发票验真返回信息
			out = new FileOutputStream(new File("D:\\adf.html"));
			int len = 0;
			while ((len = in.read()) != -1) {
				out.write(len);
			}
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) in.close();
				if (out != null) out.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
}
