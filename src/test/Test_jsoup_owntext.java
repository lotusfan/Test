package test;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Test_jsoup_owntext {

	public static void main(String[] args) throws IOException {

		File file = new File("D:\\bb.xml");
		Document doc = Jsoup.parse(file, "UTF-8");
		Elements element = doc.getElementsContainingText("<a¼ÓÈëÊÕ²Ø^[</a>>]");
		System.out.println(element.size() + "111");
	}
}
