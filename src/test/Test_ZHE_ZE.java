package test;

import java.io.IOException;

public class Test_ZHE_ZE {

	public static void main(String[] args) throws IOException {

		//		String str = "<strong> �÷�Ʊ23100137025213101975<br>��2013��06��13����<br>�ֶ�����˰��ֳ��ۣ�<br>��Ʊ��Ϊ�Ϻ�Ե�������������޹�˾�� </strong> ";
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
