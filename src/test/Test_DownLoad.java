package test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Test_DownLoad {
	public static void main(String[] args) {

		String path = "w2.jpg";
		String url1 = "http://127.0.0.1:8080/XCFH/usermanager/backupimage?uid=9c97fd4c108f4a9b970afb48a8ab6a5e";
		URL url;
		OutputStream outputStream = null;
		try {
			url = new URL(url1);
			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
			httpURLConnection.setConnectTimeout(3000);
			httpURLConnection.setDoInput(true);
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setRequestMethod("POST");
			httpURLConnection.setUseCaches(false);
			// httpURLConnection.setRequestProperty("Content-Type", "text/xml");
			// 文件打印
			int response = httpURLConnection.getResponseCode();
			System.out.println("-------response---->>" + response);
			InputStream is = httpURLConnection.getInputStream();
			System.out.println("——————————请求头开始————————————");
			System.out.println(httpURLConnection.getContentLength());
			Map hfs = httpURLConnection.getHeaderFields();
			Set<String> keys = hfs.keySet();
			for (String str : keys) {
				List<String> vs = (List) hfs.get(str);
				System.out.print(str + ":");
				for (String v : vs) {
					System.out.print(v + "\t");
				}
				System.out.println();
			}
			System.out.println("************************请求头结束————————————");
			FileOutputStream fos = new FileOutputStream(new File("D://copy" + path));
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			BufferedInputStream bis = new BufferedInputStream(is);
			byte[] cache_array = new byte[1024];
			int len = 0;
			int size = 0;
			while ((len = bis.read(cache_array)) > -1) {
				size += len;
				bos.write(cache_array, 0, len);
			}
			bos.flush();
			System.out.println("______________________________size_____________" + size);
			fos.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
