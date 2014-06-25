package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Test_File_Cut_String {
	public static void main(String[] args) throws Exception {

		File ff = new File("src/shanghai.html");

		System.out.println(ff.exists());
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(ff)));
		StringBuffer sb = new StringBuffer();

		String str_temp = "";
		while ((str_temp = br.readLine()) != null) {
			sb.append(str_temp);
			sb.append("\n");
		}
		String str_out = new String(sb.toString().getBytes(), "UTF-8");
		System.out.println(str_out);
		System.out.println(str_out.indexOf("<strong>"));
		System.out.println(str_out.indexOf("/strong>"));
		System.out.println(str_out.substring(6355, 6440));

	}
}
