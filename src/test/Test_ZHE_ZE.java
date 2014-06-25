package test;

import java.io.IOException;

public class Test_ZHE_ZE {

	public static void main(String[] args) throws IOException {

		//		String str = "<strong> 该发票23100137025213101975<br>于2013年06月13日由<br>浦东新区税务局出售，<br>购票方为上海缘澍餐饮管理有限公司。 </strong> ";
		//		str = str.replaceAll("<.?strong>", "").replaceAll("<br>", "");
		//		System.out.println(str);
		//
		//		Document doc = Jsoup.parse(new File("D://shhai.html"), "UTF-8");
		//
		//		Elements eles = doc.select("#jgms");
		//		System.out.println(eles.text());

		String str = "asdgdg&nbsp;sdfsadf";
		System.out.println(str.replaceAll("&nbsp;", ""));
	}
}
