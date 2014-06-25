package test;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import test.invoiceimage.InvoiceImage;

public class Test_json_datam {
	public static void main(String[] args) {

		String str = "{'zs':{'date':'201403121215','ac_name':'空调开启指数','ac_hint':'较少开启','ac_des':'您将感到很舒适，一般不需要开启空调。','ag_name':'息斯敏过敏指数','ag_hint':'易发','ag_des':'天气条件易诱发过敏，易过敏人群应减少外出，外出宜穿长衣长裤并佩戴好眼镜和口罩，外出归来时及时清洁手和口鼻。','be_name':'海滨浴场','be_hint':'较不宜','be_des':'较不宜下海，今天有雨，但温度很低，有风，不推荐下海游玩。','cl_name':'晨练指数','cl_hint':'不宜','cl_des':'有降水，且早晨天气寒冷，风力稍大，请尽量避免户外晨练，若坚持晨练请带上雨具并注意保暖防冻。','co_name':'舒适度指数','co_hint':'较舒适','co_des':'白天会有降雨，这种天气条件下，人们会感到有些凉意，但大部分人完全可以接受。','ct_name':'穿衣指数','ct_hint':'冷','ct_des':'天气冷，建议着棉服、羽绒服、皮夹克加羊毛衫等冬季服装。年老体弱者宜着厚棉衣、冬大衣或厚羽绒服。','dy_name':'钓鱼指数','dy_hint':'不宜','dy_des':'天气不好，有风，不适合垂钓。','fs_name':'防晒指数','fs_hint':'弱','fs_des':'属弱紫外辐射天气，长期在户外，建议涂擦SPF在8-12之间的防晒护肤品。','gj_name':'逛街指数','gj_hint':'较适宜','gj_des':'虽有降水，风力稍大，但还是较适宜逛街的，不过出门前要记得带上雨具。','gl_name':'太阳镜指数','gl_hint':'不需要','gl_des':'降水天气，视线较差，不需要佩戴太阳镜','gm_name':'感冒指数','gm_hint':'易发','gm_des':'相对于今天将会出现大幅度降温，易发生感冒，注意增加衣服，加强自我防护避免感冒。','gz_name':'干燥指数','gz_hint':'适宜','gz_des':'天气条件较好，保持正常对皮肤的护理即可，可使用冬季滋润保湿型护肤品。','hc_name':'划船指数','hc_hint':'不适宜','hc_des':'白天天气不好，不适宜划船，建议选择别的娱乐方式。','jt_name':'交通指数','jt_hint':'较好','jt_des':'有降水，能见度不太好且路面潮湿，不适宜高速行驶，司机应更加集中注意力，小心驾驶。','lk_name':'路况指数','lk_hint':'潮湿','lk_des':'有降水，路面潮湿，车辆易打滑，请小心驾驶。','ls_name':'晾晒指数','ls_hint':'不宜','ls_des':'有降水，不适宜晾晒。若需要晾晒，请在室内准备出充足的空间。','mf_name':'美发指数','mf_hint':'适宜','mf_des':'较强的风力不仅易弄脏头发，还会带走头发的水分，以致头发发质变干，失去光泽，请注意保持头发的清洁，使用滋润型洗发露和护发素。','nl_name':'夜生活指数','nl_hint':'较适宜','nl_des':'虽然是阴天，有点风，但仍比较适宜夜生活，只要您稍作准备就可以放心外出。','pj_name':'啤酒指数','pj_hint':'较不适宜','pj_des':'您将会感到有些凉意，建议饮用常温啤酒，并少量饮用为好。','pk_name':'放风筝指数','pk_hint':'不宜','pk_des':'天气不好，不适宜放风筝。','pl_name':'空气污染扩散条件指数','pl_hint':'良','pl_des':'气象条件有利于空气污染物稀释、扩散和清除，可在室外正常活动。','pp_name':'化妆指数','pp_hint':'保湿','pp_des':'风力不大，建议用中性保湿型霜类化妆品，无需选用防晒化妆品。','sg_name':'一句话提示指数','sg_hint':'暂缺','sg_des':'现在是中度污染，出门记得戴口罩，小朋友老人以及有心脏或肺部疾病的人要减少户外运动。','tr_name':'旅游指数','tr_hint':'适宜','tr_des':'有降水，虽然风稍大，但温度适宜，适宜旅游，可不要错过机会呦！','uv_name':'紫外线强度指数','uv_hint':'最弱','uv_des':'属弱紫外线辐射天气，无需特别防护。若长期在户外，建议涂擦SPF在8-12之间的防晒护肤品。','xc_name':'洗车指数','xc_hint':'不宜','xc_des':'不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。','xq_name':'心情指数','xq_hint':'较差','xq_des':'有降水，雨水可能会使心绪无端地挂上轻愁，与其因下雨而无精打采，不如放松心情，好好欣赏一下雨景。你会发现雨中的世界是那般洁净温和、清新葱郁。','yd_name':'运动指数','yd_hint':'较不宜','yd_des':'有降水，且风力较强，推荐您在室内进行低强度运动；若坚持户外运动，请注意保暖并携带雨具。','yh_name':'约会指数','yh_hint':'较不适宜','yh_des':'室外有风，而且有降水，会给室外约会带来一些不便，如果外出约会，请一定做好准备。','ys_name':'雨伞指数','ys_hint':'带伞','ys_des':'有降水，请带上雨伞，如果你喜欢雨中漫步，享受大自然给予的温馨和快乐，在短时间外出可收起雨伞。','zs_name':'中暑指数','zs_hint':'无','zs_des':'温度不高，其他各项气象条件适宜，中暑机率极低。'}}";
		JSONObject jso = JSONObject.fromObject(str);
		JSONObject jsosub = (JSONObject) jso.get("zs");
		Set ss = jsosub.entrySet();
		Iterator it = ss.iterator();
		while (it.hasNext()) {
			Entry ee = (Entry) it.next();
			System.out.println(ee.getKey());
		}

		JSONObject jso1 = new JSONObject();
		JSONArray jsoa = new JSONArray();
		jsoa.add("11111111111");
		jsoa.add("22222222222");
		jso1.put("a", jsoa);
		JSONArray jsoa1 = jso1.getJSONArray("a");
		System.out.println(jsoa1.toString());
		System.out.println("-----------------------------------------------");

		InvoiceImage in = new InvoiceImage();
		in.setKeyname("asdf");
		in.setPy(3);
		JSONObject jj = (JSONObject) JSONSerializer.toJSON(in);
		jj.put("aaaaaaaaaa", "");
		System.out.println(jj.toString());
		System.out.println(jj.keys().next());
		JSONArray array = new JSONArray();
		array.add(jj);
		array.add(jj);
		array.add("123456789");
		array.add("dddd");
		array.remove("dddd");
		System.out.println(array);

	}
}

class Info {
	private String name;
	private String age;

	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public String getAge() {

		return age;
	}

	public void setAge(String age) {

		this.age = age;
	}

}
