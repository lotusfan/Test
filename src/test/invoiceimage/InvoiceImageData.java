package test.invoiceimage;

import net.sf.json.JSONObject;

public class InvoiceImageData {
	private JSONObject datajson;

	public JSONObject getDatajson() {

		return datajson;
	}

	public void setDatajson(JSONObject datajson) {

		this.datajson = datajson;
	}

	public void initData() {

		datajson = new JSONObject();
		JSONObject totaljson = new JSONObject();
		totaljson.accumulate("width", 810);
		totaljson.accumulate("high", 305);
		datajson.put("totaljson", totaljson);

		JSONObject vizastrjson = new JSONObject();
		vizastrjson.accumulate("width", 545);
		vizastrjson.accumulate("high", 115);
		datajson.put("vizajson", vizastrjson);

		JSONObject timejson = new JSONObject();
		timejson.accumulate("width", 441);
		timejson.accumulate("high", 178);
		datajson.put("timejson", timejson);

		JSONObject serialnumjson = new JSONObject();
		serialnumjson.accumulate("width", 717);
		serialnumjson.accumulate("high", 31);
		datajson.put("serialnumjson", serialnumjson);

		JSONObject other_taxesjson = new JSONObject();
		other_taxesjson.accumulate("width", 618);
		other_taxesjson.accumulate("high", 306);
		datajson.put("other_taxesjson", other_taxesjson);

		JSONObject namejson = new JSONObject();
		namejson.accumulate("width", 30);
		namejson.accumulate("high", 115);
		datajson.put("namejson", namejson);

		JSONObject fuel_surchargejson = new JSONObject();
		fuel_surchargejson.accumulate("width", 420);
		fuel_surchargejson.accumulate("high", 306);
		datajson.put("fuel_surchargejson", fuel_surchargejson);

		JSONObject fromjson = new JSONObject();
		fromjson.accumulate("width", 76);
		fromjson.accumulate("high", 176);
		datajson.put("fromjson", fromjson);

		JSONObject flightjson = new JSONObject();
		flightjson.accumulate("width", 179);
		flightjson.accumulate("high", 176);
		datajson.put("flightjson", flightjson);

		JSONObject flight_nojson = new JSONObject();
		flight_nojson.accumulate("width", 221);
		flight_nojson.accumulate("high", 178);
		datajson.put("flight_nojson", flight_nojson);

		JSONObject farejson = new JSONObject();
		farejson.accumulate("width", 184);
		farejson.accumulate("high", 306);
		datajson.put("farejson", farejson);

		JSONObject fare_bastsejson = new JSONObject();
		fare_bastsejson.accumulate("width", 616);
		fare_bastsejson.accumulate("high", 178);
		datajson.put("fare_bastsejson", fare_bastsejson);

		JSONObject datejson = new JSONObject();
		datejson.accumulate("width", 327);
		datejson.accumulate("high", 178);
		datajson.put("datejson", datejson);

		JSONObject seatleveljson = new JSONObject();
		seatleveljson.accumulate("width", 295);
		seatleveljson.accumulate("high", 178);
		datajson.put("seatleveljson", seatleveljson);

		JSONObject fromengjson = new JSONObject();
		fromengjson.accumulate("width", 140);
		fromengjson.accumulate("high", 178);
		datajson.put("fromengjson", fromengjson);

		JSONObject caac_development_fundjson = new JSONObject();
		caac_development_fundjson.accumulate("width", 293);
		caac_development_fundjson.accumulate("high", 306);
		datajson.put("caac_development_fundjson", caac_development_fundjson);

		JSONObject allowbagjson = new JSONObject();
		allowbagjson.accumulate("width", 942);
		allowbagjson.accumulate("high", 178);
		datajson.put("allowbagjson", allowbagjson);

		JSONObject insurancejson = new JSONObject();
		insurancejson.accumulate("width", 940);
		insurancejson.accumulate("high", 339);
		datajson.put("insurancejson", insurancejson);

		JSONObject fill_departmentjson = new JSONObject();
		fill_departmentjson.accumulate("width", 356);
		fill_departmentjson.accumulate("high", 364);
		datajson.put("fill_departmentjson", fill_departmentjson);

		JSONObject fill_datejson = new JSONObject();
		fill_datejson.accumulate("width", 876);
		fill_datejson.accumulate("high", 366);
		datajson.put("fill_datejson", fill_datejson);

		JSONObject eticket_nojson = new JSONObject();
		eticket_nojson.accumulate("width", 90);
		eticket_nojson.accumulate("high", 339);
		datajson.put("eticket_nojson", eticket_nojson);

		JSONObject yzmjson = new JSONObject();
		yzmjson.accumulate("width", 270);
		yzmjson.accumulate("high", 339);
		datajson.put("yzmjson", yzmjson);

		JSONObject serivalnumengjson = new JSONObject();
		serivalnumengjson.accumulate("width", 717);
		serivalnumengjson.accumulate("high", 51);
		datajson.put("serialnumengjson", serivalnumengjson);

		JSONObject informationjson = new JSONObject();
		informationjson.accumulate("width", 612);
		informationjson.accumulate("high", 339);
		datajson.put("informationjson", informationjson);
	}
}
