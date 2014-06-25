package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasChildFilter;
import org.htmlparser.filters.StringFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.Span;
import org.htmlparser.util.NodeList;

public class Test_htmlparser {
	public static void main(String[] args) throws Exception {

		File file = new File("D://adf.html");
		System.out.println(file.exists());
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		StringBuffer sb = new StringBuffer();
		String string_cache = "";
		while ((string_cache = br.readLine()) != null) {
			sb.append(string_cache + "\n");
		}
		String htmlData = new String(sb.toString().getBytes(), "UTF-8");
		Parser htmlParser = new Parser(htmlData);
		//AndFilter ta = new AndFilter(new TagNameFilter("input"), new HasAttributeFilter("type", "hidden"));
		NodeFilter[] nfarray = new NodeFilter[2];
		nfarray[0] = new TagNameFilter("a");
		nfarray[1] = new HasChildFilter(new TagNameFilter("img"));
		//nfarray[2] = new HasAttributeFilter("href", "/fygg/pdf/id/2097937.pdf");
		AndFilter ta = new AndFilter(nfarray);
		AndFilter af = new AndFilter(new TagNameFilter("a"), new HasChildFilter(new TagNameFilter("img")));
		StringFilter sf = new StringFilter("strong");
		HasChildFilter childFilter = new HasChildFilter();
		childFilter.setChildFilter(new TagNameFilter("span"));
		//childFilter.setChildFilter(new HasAttributeFilter("name", "form1"));
		//TagNameFilter ta = new TagNameFilter("strong");
		NodeList list = htmlParser.extractAllNodesThatMatch(ta);
		Node inputNode = null;
		for (int i = 0; i < list.size(); i++) {
			inputNode = list.elementAt(i);
			NodeList nl = inputNode.getChildren();
			if (nl.size() > 1) {
				for (int j = 0; j < nl.size(); j++) {
					System.out.println(j + nl.elementAt(j).toHtml());
				}
			}
			LinkTag lt = (LinkTag) inputNode;
			Span it = new Span();
			//it = (Span) inputNode;
			String href = lt.getAttribute("href");
			if (href.matches(".*pdf")) {
				System.out.println("----------------------------" + lt.getAttribute("href"));
			}
			//	System.out.println(i + inputNode.toHtml());
			//System.out.println(it.getAttribute("name") + "=" + it.getAttribute("value"));
		}
	}
}
