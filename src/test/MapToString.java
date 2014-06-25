package test;

import java.util.HashMap;
import java.util.Map;

public class MapToString {
	public static void main(String[] args) {

		Map map = new HashMap();
		map.put("aa", "vaa");
		map.put("bb", "vbb");
		System.out.println(map.toString());
		System.out.println(UID.getUUID());
	}
}
