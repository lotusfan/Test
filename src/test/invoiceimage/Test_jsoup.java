package test.invoiceimage;

import java.io.File;
import java.io.IOException;

import net.sf.json.JSONObject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Test_jsoup {
	public JSONObject parseHTML() throws IOException {

		File file = new File("D:\\dd.xml");
		Document doc = Jsoup.parse(file, "UTF-8");
		Elements nameel = doc.select("div#passengerName_list");
		Elements vizael = doc.select("td#change_fee");
		Elements fromel = doc.select("div#from");
		Elements serialnumel = doc.select(":containsOwn(SERIAL NUMBER)");
		Elements carrierel = doc.select("div#carrier");
		Elements flightel = doc.select("div#flight");
		Elements flight_noel = doc.select("div#flight_no");
		Elements classel = doc.select("div#class");
		Elements dateel = doc.select("div#date");
		Elements timeel = doc.select("div#time");
		Elements fare_bastsel = doc.select("div#fare_basts");
		Elements fareel = doc.select("td#fare");
		Elements caac_development_fundel = doc.select("td#caac_development_fund");
		Elements fuel_surchargeel = doc.select("td#fuel_surcharge");
		Elements other_taxesel = doc.select("td#other_taxes");
		Elements totalel = doc.select("td#total");
		Elements agent_codeel = doc.select("td#agent_code");
		Elements allowel = doc.select("div#allow");
		Elements eticket_noel = doc.select("div#eticket_no");
		Elements insuranceel = doc.select("td#insurance");
		Element informationel = insuranceel.get(0).previousElementSibling();
		Elements fill_department = doc.select("div#agent_code + div");
		Elements fill_date = doc.select("div#agent_code + div + div");
		Elements yzm = doc.select("td#insurance");
		Element yzmn = yzm.first();
		/*
		 * System.out.println(yzmn.siblingElements().get(1).html());
		 * System.out.println(yzmn.siblingElements().get(1).html().toString().replaceAll("<[^>]*>", "")); Elements[] arrayels = new Elements[]{vizael,
		 * totalel, timeel, serialnumel, other_taxesel, nameel, fuel_surchargeel, fromel, flightel, flight_noel, fareel, fare_bastsel, dateel,
		 * classel, carrierel, caac_development_fundel, agent_codeel, allowel, insuranceel, fill_department, fill_date, yzmn .siblingElements()};
		 * 
		 * for (Elements ele : arrayels) { System.out.println(ele.html().replaceAll("<[^>]*>", "").replaceAll("&nbsp;", "  ").replaceAll("\n*", ""));
		 * }
		 */
		int i = 0;
		JSONObject tempjson = null;
		InvoiceImageData iid = new InvoiceImageData();

		iid.initData();
		JSONObject datajson = iid.getDatajson();
		//System.out.println(vizael.html() + totalel + timeel + serialnumel + other_taxesel + nameel + fuel_surchargeel + fromel + flightel
		//		+ filght_noel + fareel + fare_bastsel + dateel + classel + carrierel + caac_development_fundel + agent_codeel);
		String totalstr = totalel.html().replaceAll("<[^>]*>", "").replaceAll("&nbsp;", "  ").replaceAll("\n*", "");
		datajson.getJSONObject("totaljson").accumulate("content", totalstr);

		String vizastr = vizael.html().replaceAll("<[^>]*>", "").replaceAll("&nbsp;", "  ").replaceAll("\n*", "");
		datajson.getJSONObject("vizajson").accumulate("content", vizastr);

		String timestr = timeel.html().replaceAll("<[^>]*>", "").replaceAll("&nbsp;", "  ").replaceAll("\n*", "");
		datajson.getJSONObject("timejson").accumulate("content", timestr);

		String serialnumstr = serialnumel.html().replaceAll("<[^>]*>", "").replaceAll("&nbsp;", "  ").replaceAll("\n*", "");
		String serialnumstrch = serialnumstr.substring(1, serialnumstr.indexOf("SERIAL"));
		datajson.getJSONObject("serialnumjson").accumulate("content", serialnumstrch);
		String serivalnumstreng = serialnumstr.substring(serialnumstr.indexOf("SERIAL"));
		datajson.getJSONObject("serialnumengjson").accumulate("content", serivalnumstreng);
		//System.out.println(serialnumstrch + "------" + serivalnumstreng);

		String other_taxesstr = other_taxesel.html().replaceAll("<[^>]*>", "").replaceAll("&nbsp;", "  ").replaceAll("\n*", "");
		datajson.getJSONObject("other_taxesjson").accumulate("content", other_taxesstr);

		String namestr = nameel.html().replaceAll("<[^>]*>", "").replaceAll("&nbsp;", "  ").replaceAll("\n*", "");
		datajson.getJSONObject("namejson").accumulate("content", namestr);

		String fuel_surchargestr = fuel_surchargeel.html().replaceAll("<[^>]*>", "").replaceAll("&nbsp;", "  ").replaceAll("\n*", "");
		datajson.getJSONObject("fuel_surchargejson").accumulate("content", fuel_surchargestr);

		String fromstr = fromel.html().replaceAll("<[^>]*>", "").replaceAll("&nbsp;", "  ").replaceAll("\n*", "");
		String[] fromstr_array = fromstr.split(" ");
		JSONObject fromjson = datajson.getJSONObject("fromjson");
		fromjson.accumulate("content", fromstr_array[0]);
		for (i = 1; i < fromstr_array.length; i++) {
			tempjson = new JSONObject();
			tempjson.accumulate("content", fromstr_array[i]);
			tempjson.accumulate("width", fromjson.getInt("width"));
			tempjson.accumulate("high", fromjson.getInt("high") + i * 26);
			datajson.put("fromjson" + i, tempjson);
		}
		//System.out.println(fromstr_array[2]);

		String flightstr = flightel.html().replaceAll("<[^>]*>", "").replaceAll("&nbsp;", "  ").replaceAll("\n*", "");
		String[] flightstr_array = flightstr.split(" ");
		JSONObject flightjson = datajson.getJSONObject("flightjson");
		flightjson.accumulate("content", flightstr_array[0]);
		for (i = 1; i < flightstr_array.length; i++) {
			tempjson = new JSONObject();
			tempjson.accumulate("content", flightstr_array[i]);
			tempjson.accumulate("width", flightjson.getInt("width"));
			tempjson.accumulate("high", flightjson.getInt("high") + i * 26);
			datajson.put("flightjson" + i, tempjson);
		}
		//System.out.println(flightstr_array[1]);

		String flight_nostr = flight_noel.html().replaceAll("<[^>]*>", "").replaceAll("&nbsp;", "  ").replaceAll("\n*", "");
		datajson.getJSONObject("flight_nojson").accumulate("content", flight_nostr);

		String farestr = fareel.html().replaceAll("<[^>]*>", "").replaceAll("&nbsp;", "  ").replaceAll("\n*", "");
		datajson.getJSONObject("farejson").accumulate("content", farestr);

		String fare_bastsestr = fare_bastsel.html().replaceAll("<[^>]*>", "").replaceAll("&nbsp;", "  ").replaceAll("\n*", "");
		datajson.getJSONObject("fare_bastsejson").accumulate("content", fare_bastsestr);

		String datestr = dateel.html().replaceAll("<[^>]*>", "").replaceAll("&nbsp;", "  ").replaceAll("\n*", "");
		datajson.getJSONObject("datejson").accumulate("content", datestr);

		String seatlevelstr = classel.html().replaceAll("<[^>]*>", "").replaceAll("&nbsp;", "  ").replaceAll("\n*", "");
		datajson.getJSONObject("seatleveljson").accumulate("content", seatlevelstr);

		String fromeng = carrierel.html().replaceAll("<[^>]*>", "").replaceAll("&nbsp;", "  ").replaceAll("\n*", "");
		String[] fromeng_array = fromeng.split(" ");
		JSONObject fromengjson = datajson.getJSONObject("fromengjson");
		fromengjson.accumulate("content", fromeng_array[0]);
		for (i = 1; i < flightstr_array.length; i++) {
			tempjson = new JSONObject();
			tempjson.accumulate("content", fromeng_array[i]);
			tempjson.accumulate("width", fromengjson.getInt("width"));
			tempjson.accumulate("high", fromengjson.getInt("high") + i * 26);
			datajson.put("fromengjson" + i, tempjson);
		}
		//System.out.println(fromeng_array[1]);

		String caac_development_fundstr = caac_development_fundel.html().replaceAll("<[^>]*>", "").replaceAll("&nbsp;", "  ").replaceAll("\n*", "");
		datajson.getJSONObject("caac_development_fundjson").accumulate("content", caac_development_fundstr);

		String allowbagstr = allowel.html().replaceAll("<[^>]*>", "").replaceAll("&nbsp;", "  ").replaceAll("\n*", "");
		datajson.getJSONObject("allowbagjson").accumulate("content", allowbagstr);

		String insurancestr = insuranceel.html().replaceAll("<[^>]*>", "").replaceAll("&nbsp;", "  ").replaceAll("\n*", "");
		datajson.getJSONObject("insurancejson").accumulate("content", insurancestr);

		String informationstr = informationel.html().replaceAll("<[^>]*>", "").replaceAll("&nbsp;", "  ").replaceAll("\n*", "");
		datajson.getJSONObject("informationjson").accumulate("content", informationstr);

		String fill_departmentstr = fill_department.html().replaceAll("<[^>]*>", "").replaceAll("&nbsp;", "  ").replaceAll("\n*", "");
		datajson.getJSONObject("fill_departmentjson").accumulate("content", fill_departmentstr);

		String fill_datestr = fill_date.html().replaceAll("<[^>]*>", "").replaceAll("&nbsp;", "  ").replaceAll("\n*", "");
		datajson.getJSONObject("fill_datejson").accumulate("content", fill_datestr);

		String eticket_nostr = eticket_noel.html().replaceAll("<[^>]*>", "").replaceAll("&nbsp;", "  ").replaceAll("\n*", "");
		datajson.getJSONObject("eticket_nojson").accumulate("content", eticket_nostr);

		String yzmstr = yzmn.siblingElements().get(1).html().replaceAll("<[^>]*>", "").replaceAll("&nbsp;", "  ").replaceAll("\n*", "");
		if (yzmstr.length() > 0) {
			yzmstr = yzmstr.substring(1);
			datajson.getJSONObject("yzmjson").accumulate("content", yzmstr);
		}

		return datajson;
	}
}
