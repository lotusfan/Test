package test;

import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Test_DOM4J_Pase {
	public static void main(String[] args) throws Exception {

		SAXReader saxr = new SAXReader();
		Document docu = saxr.read(new File("src/xcfh.xml"));
		Element rootel = docu.getRootElement();
		List<Element> listel = rootel.elements();
		for (Element tablel : listel) {
			if (tablel.attributeValue("name").equals("stay_info")) {
				System.out.println(tablel.getName());
				List<Element> subtablel = tablel.elements();
				for (Element recordel : subtablel) {
					Element local_path = recordel.element("stay_invoice_local_path");
					System.out.println(local_path.getText());
				}
			} else if (tablel.attributeValue("name").equals("traffic_info")) {
				System.out.println(tablel.getName());
				List<Element> subtablel = tablel.elements();
				for (Element recordel : subtablel) {
					Element local_path = recordel.element("traffic_invoice_local_path");
					System.out.println(local_path.getText());
				}
			} else if (tablel.attributeValue("name").equals("other_info")) {
				System.out.println(tablel.getName());
				List<Element> subtablel = tablel.elements();
				for (Element recordel : subtablel) {
					Element local_path = recordel.element("other_invoice_local_path");
					System.out.println(local_path.getText());
				}
			}
		}
	}
}
