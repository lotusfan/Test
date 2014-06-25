package test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class Test_DOM4J_Pase_Node {

	public static void main(String[] args) {

		//updateBackupInfoXML("", "stay_info", "yyy");
		try {
			paseBJGSInformation();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public static void updateBackupInfoXML(String user_id, String type, String pathname) {

		File fileloc = null;
		try {
			SAXReader saxr = new SAXReader();

			fileloc = new File("src/xcfh.xml");
			System.out.println(fileloc.exists());
			// if ((fileloc.list()).length == 0) return;
			Document doc = saxr.read(fileloc);
			List<Node> node = null;
			if ("stay_info".equals(type)) {
				String str = "//table[@name='" + type + "']/record_stay_id[stay_invoice_local_path='" + pathname + "']";
				System.out.println(str);

				node = doc.selectNodes(str);
				System.out.println(node.size());
				if (node.size() == 1) {
					String str1 = null;
					node.get(0).selectSingleNode("stay_invoice_server_path").setText("你好");
					System.out.println(str1);
				}

			} else if ("other_info".equals(type)) {
				node = doc.selectNodes("//table[@name='" + type + "']/record_other_id[other_invoice_local_path='" + pathname + "']");
				if (node.size() == 1) {
					node.get(0).selectSingleNode("other_invoice_server_path").setText("");
				}
			} else if ("traffic_info".equals(type)) {
				node = doc.selectNodes("//table[@name='" + type + "']/record_traffice_id[traffic_invoice_local_path='" + pathname + "']");
				if (node.size() == 1) {
					node.get(0).selectSingleNode("traffic_invoice_server_path").setText("");
				}
			}
			// OutputFormat format = new OutputFormat("  ", true, "UTF-8");
			XMLWriter xmlw = new XMLWriter(new FileOutputStream(fileloc));
			xmlw.write(doc);
			xmlw.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public static void paseBJGSInformation() throws Exception {

		SAXReader saxr = new SAXReader();
		String str = "<?xml  version='1.0'  encoding='GB2312'?><NS><N D='YZM'>1</N><N D='FPDM'>111001374001</N><N D='FPHM'>30689939</N><N D='FPMM'>33714803</N>"
				+ "<N D='DWMC'>北京霄云桥加油加气站有限公司</N><N D='kpri'></N><N D='nsr1'></N>" + "<N D='CXJG'>北京市国家税务局通用机打发票,代码、号码、密码及税控后台校验比对结果:tz真。该查询为第6次正确查询。</N><N D='zj'>1</N></NS>";
		str = new String(str.getBytes(), "GB2312");
		InputStream is = new ByteArrayInputStream(str.getBytes());
		Document doc = saxr.read(is);
		List<Node> node = null;
		node = doc.selectNodes("//N");
		for (Node nn : node) {
			System.out.println(nn.getText());
		}
	}
}
