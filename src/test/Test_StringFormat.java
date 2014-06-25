package test;

import java.rmi.server.UID;
import java.util.Date;

public class Test_StringFormat {
	public static void main(String[] args) {

		String str = "ÄãºÃÑ½{0}ºÇºÇ{1}";
		System.out.println(new UID());
		String str1 = "";
		System.out.println(str1 == null);
		System.out.println(new Date(1389606671));

		String str3 = "zhangfan2010aa@163.com";
		System.out.println(str3.matches("^\\w+@\\w+(\\.(com|cn|net))+$"));

		String str4 = "BWDSESSION=pWp7TsLDn5ySyQMSHdp1ksSgYYr4Kxcz2xQl2cQ6lTySqRvMdgQL!-1473313801; path=/, Sg3KP1YvCB=MDAwM2IyYTkwMTAwMDAwMDAwMjUwD20hfS4xMzk5NjMxMTk3;path=/";
		str4.replaceAll("; path=/", "");
		System.out.println(str4.replaceAll("; path=/", ""));
	}
}
