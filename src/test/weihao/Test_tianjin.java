package test.weihao;

import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Test_tianjin {

	public static void main(String[] args) throws Exception {

		//		URL url = new URL("http://127.0.0.1:8080/My_car/weather/weather_indexAction");
		//		//URL url = new URL("http://www.baidu.com");
		//		HttpURLConnection httpconection = (HttpURLConnection) url.openConnection();
		//		httpconection.setDoInput(true);
		//		httpconection.setDoOutput(true);
		//		httpconection.setRequestMethod("GET");
		//		httpconection.setUseCaches(false);
		//		httpconection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.2; WOW64; rv:27.0) Gecko/20100101 Firefox/27.0");
		//		httpconection.connect();
		//		System.out.println(httpconection.getResponseCode());
		//		int codewe = httpconection.getResponseCode();
		//		InputStream in = httpconection.getInputStream();
		//		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		//		String str = null;
		//		StringBuffer sb = new StringBuffer();
		//		while ((str = br.readLine()) != null) {
		//			sb.append(str);
		//		}
		//		str = sb.toString();
		Document doc = Jsoup.parse(new URL("http://www.weather.com.cn/weather/101030100.shtml"), 1000);
		Elements el = doc.select("div.weihao");
		Element today = el.get(0).child(0);
		Element tomorrow = el.get(0).child(1);
		System.out.println(today.text() + "\n" + tomorrow.text());
	}
}
