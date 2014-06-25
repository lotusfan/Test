package test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Test_Map {
	public static void main(String[] args) {

		Map mm = new HashMap<String, String>();
		mm.put("nihao", "");
		mm.put("invoiceNo", "23100137025240011998");
		mm.put("randon", "-196483046");
		StructureAttributePost sap = new StructureAttributePost();
		System.out.println(sap.structureAttributePost(mm));
	}
}

class StructureAttributePost {
	public String structureAttributePost(Map attributeMap) {

		Set attributeSet = attributeMap.entrySet();
		Iterator it = attributeSet.iterator();
		StringBuffer attributeString = new StringBuffer();
		while (it.hasNext()) {
			Entry<String, String> entryMap = (Entry<String, String>) it.next();
			attributeString.append(entryMap.getKey() + "=" + entryMap.getValue() + "&");
		}

		return attributeString.substring(0, attributeString.length() - 1);
	}
}