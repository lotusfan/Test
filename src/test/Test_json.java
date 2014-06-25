package test;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Test_json {
	public static void main(String[] args) {

		String str = "{nihao:'ddd', invoiceNum:'eettd', yaya:'11123'}";
		JSONObject json = new JSONObject();
		JSONObject json2 = new JSONObject();
		JSONObject json3 = JSONObject.fromObject(str);
		JSONObject json4 = new JSONObject();
		JSONObject jsonlogin = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		jsonArray.element("aaa");
		jsonArray.element("bb");
		json4.put("u_nm", "4939726d39@qq.com");
		json4.put("u_pd", "123456789");
		json4.put("ap_id", "com.xcfh");
		json4.put("ap_ver", "1.0.01");
		json4.put("u_id", "Tabceefalksdjflkjlkasdjflk");
		/*
		 * json4.put("u_fnm", "adf"); json4.put("u_gd", "d"); json4.put("u_ph_num", "65416"); json4.put("u_fm_adr", "asdf"); json4.put("u_pro",
		 * "asdf"); json4.put("u_thr_flag", "1");
		 */
		json2.put("register_info", json4);
		//json2.put("ddd", jsonArray);
		//json.put("1", json2);
		//	System.out.println(json.toString());
		System.out.println(json2.toString());
		System.out.println(json3.toString());

		//µÇÂ¼
		jsonlogin.put("u_nm", "123456");
		jsonlogin.put("u_pd", "123456");
		SendRequestPost srp = new SendRequestPost();
		try {
			srp.sendRequestPost(json4);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class SendRequestPost {
	public void sendRequestPost(JSONObject js) throws Exception {

		String str1 = "http://127.0.0.1:8080/XCFH_INVOICE/invoiceproving/selectinvoice.action?regionNum=1100&addressType=result";
		String strreg = "http://192.168.3.14/XCFHCOM/usermanager/userreg.action";
		String strlogin = "http://192.168.3.50:8080/XCFHCOM/usermanager/userlogin.action";
		String appstrlogin = "http://127.0.0.1:8080/XCFHAPP/app/appforward.action?module=com&type=user&method=userlogin";
		URL url = new URL(strreg);
		HttpURLConnection httpconection = (HttpURLConnection) url.openConnection();
		httpconection.setDoInput(true);
		httpconection.setDoOutput(true);
		httpconection.setRequestMethod("POST");
		httpconection.setUseCaches(false);
		httpconection.setInstanceFollowRedirects(true);
		httpconection.setRequestProperty("Content-Type", "application/json");
		//httpconection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.2; WOW64; rv:27.0) Gecko/20100101 Firefox/27.0");
		httpconection.connect();
		String str = "regionNum=0002&addressType=result";
		OutputStream out = httpconection.getOutputStream();
		DataOutputStream dos = new DataOutputStream(out);
		System.out.println(js.toString());
		String xml = "<xcfh><error>4445</error></xcfh>";
		JSONObject obb = new JSONObject();
		obb.put("nihao", "ddddd");
		dos.writeBytes(js.toString());
		dos.flush();

		System.out.println(httpconection.getResponseCode());
		InputStream in = httpconection.getInputStream();
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		int len = -1;
		while ((len = in.read()) != -1) {
			bos.write(len);
		}
		System.out.println(bos.toString());
		if (out != null) out.close();
		if (dos != null) dos.close();

	}
}
