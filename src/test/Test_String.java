package test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Test_String {
	public static void main(String[] args) {

		String str = "lkajsldfjlsjdlgdfkhdgs.jpg";
		System.out.println(str.endsWith("png"));
		String str2 = " SLkDyqvdZVjnGJLPsQV2vnihao=nihao&=nihaoNmvvlm2LSspRKrXRv11hJPqGNCBZh5v!1192627013; path=/";
		System.out.println(str2);
		//System.out.println(str2.toUpperCase());
		String str3 = str2.replace("=nihao", "=11111111111111");
		System.out.println(str3);
		Map amap = new HashMap<String, String>();
		amap.put("fpdmnumber", "65465463132654");
		amap.put("rand", "654654645");
		StructureAddress sa = new StructureAddress();
		String str5 = sa.structureRequestAddress(
				"http://zwcx.tax861.gov.cn/fpcxjg.jsp?order=on&fpdmnumber=fpdmnumber&fphmnumber=07965089&fppassword=85715335&rand=rand", amap);
		System.out.println(str5);
	}
}

class StructureAddress {

	/*
	 * 将Map中的请求参数插入外网请求地址中，构造带有参数的请求地址
	 */
	public String structureRequestAddress(String address, Map attributeMap) {

		Set attributeESet = attributeMap.entrySet();
		Iterator it = attributeESet.iterator();
		while (it.hasNext()) {
			Entry<String, String> attributeEntry = (Entry<String, String>) it.next();
			address = address.replace("=" + attributeEntry.getKey(), "=" + attributeEntry.getValue());
			System.out.println(address);
		}
		return address;
	}

}
