package test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import net.sf.json.JSONObject;

public class Test_charset {
	public static void main(String[] args) throws UnsupportedEncodingException {

		String str = "654654654";
		System.out.println(new String(str.getBytes("utf-8"), "GBK"));
		String str1 = "";
		System.out.println((str1 = toUnicode("中文怎么样")));
		char[] c = str1.toCharArray();
		System.out.println(new String(c));
		System.out.println("--------------------"
				+ parseUnicode("'. \u5c34\u5c2c\u4f60\u5bb6\u91cc\u5783\u573e\u6240hi\u5566\u5566\u5566\u5566\u5566.'"));
		System.out.println(toChinese(str1));
		String str2 = "张三";
		System.out.println(str2 = URLEncoder.encode(str2, "UTF-8"));
		System.out.println(str2 = URLEncoder.encode(str2, "UTF-8"));
		String str3 = "%2F%E5%8F%91%E5%91%86";
		System.out.println(str3 = URLDecoder.decode(str3, "UTF-8"));
		System.out.println(str3 = URLDecoder.decode(str3, "UTF-8"));

		String str4 = "354646";
		String str4_temp = toUnicode(str4);
		System.out.println(str4_temp);
		System.out.println(parseUnicode(str4_temp));

		String str5 = "%E6%8F%90%E4%BA%A4";
		System.out.println(URLDecoder.decode(str5, "UTF-8"));
		JSONObject jso = JSONObject
				.fromObject("{'ss':'\\u95ad\\u7538\\u69fc\\u9357\\u5ea2\\u5632\\u9365\\u4ecb\\u6aaf\\u6fb6ч\\u53ad\\u6434\\u6941\\u6e41\\u95c4\\u612c\\u53d5\\u9359'}");
		jso.put("sst", toUnicode("你好"));
		System.out.println(jso);
		jso.entrySet();
		System.out.println(parseUnicode("\u2103"));

	}

	public static String toUnicode(String s) {

		String s1 = "";
		for (int i = 0; i < s.length(); i++) {
			Character.UnicodeBlock ub = Character.UnicodeBlock.of(s.charAt(i));
			if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
					|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
					|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
				s1 += "\\u" + Integer.toHexString(s.charAt(i) & 0xffff);
			} else {
				s1 += s.charAt(i);
			}

		}
		return s1;
	}

	public static String toChinese(String s) {

		char[] sc = s.toCharArray();
		return new String(sc);
	}

	public static String parseUnicode(String line) {

		int len = line.length();
		char[] out = new char[len];//保存解析以后的结果
		int outLen = 0;
		for (int i = 0; i < len; i++) {
			char aChar = line.charAt(i);
			if (aChar == '\\') {
				aChar = line.charAt(++i);
				if (aChar == 'u') {
					int value = 0;
					for (int j = 0; j < 4; j++) {
						aChar = line.charAt(++i);
						switch (aChar) {
							case '0':
							case '1':
							case '2':
							case '3':
							case '4':
							case '5':
							case '6':
							case '7':
							case '8':
							case '9':
								value = (value << 4) + aChar - '0';
								break;
							case 'a':
							case 'b':
							case 'c':
							case 'd':
							case 'e':
							case 'f':
								value = (value << 4) + 10 + aChar - 'a';
								break;
							case 'A':
							case 'B':
							case 'C':
							case 'D':
							case 'E':
							case 'F':
								value = (value << 4) + 10 + aChar - 'A';
								break;
							default:
								throw new IllegalArgumentException("Malformed \\uxxxx encoding.");
						}
					}
					out[outLen++] = (char) value;
				} else {
					if (aChar == 't') aChar = '\t';
					else if (aChar == 'r') aChar = '\r';
					else if (aChar == 'n') aChar = '\n';
					else if (aChar == 'f') aChar = '\f';
					out[outLen++] = aChar;
				}
			} else {
				out[outLen++] = aChar;
			}
		}
		return new String(out, 0, outLen);
	}

}
