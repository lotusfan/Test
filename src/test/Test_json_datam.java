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

		String str = "{'zs':{'date':'201403121215','ac_name':'�յ�����ָ��','ac_hint':'���ٿ���','ac_des':'�����е������ʣ�һ�㲻��Ҫ�����յ���','ag_name':'Ϣ˹������ָ��','ag_hint':'�׷�','ag_des':'�����������շ��������׹�����ȺӦ�������������˴����³��㲢������۾��Ϳ��֣��������ʱ��ʱ����ֺͿڱǡ�','be_name':'����ԡ��','be_hint':'�ϲ���','be_des':'�ϲ����º����������꣬���¶Ⱥܵͣ��з磬���Ƽ��º����档','cl_name':'����ָ��','cl_hint':'����','cl_des':'�н�ˮ�����糿�������䣬�����Դ��뾡�����⻧�⳿��������ֳ����������߲�ע�Ᵽů������','co_name':'���ʶ�ָ��','co_hint':'������','co_des':'������н��꣬�������������£����ǻ�е���Щ���⣬���󲿷�����ȫ���Խ��ܡ�','ct_name':'����ָ��','ct_hint':'��','ct_des':'�����䣬�������޷������޷���Ƥ�п˼���ë���ȶ�����װ���������������ź����¡������»�����޷���','dy_name':'����ָ��','dy_hint':'����','dy_des':'�������ã��з磬���ʺϴ�����','fs_name':'��ɹָ��','fs_hint':'��','fs_des':'����������������������ڻ��⣬����Ϳ��SPF��8-12֮��ķ�ɹ����Ʒ��','gj_name':'���ָ��','gj_hint':'������','gj_des':'���н�ˮ�������Դ󣬵����ǽ����˹�ֵģ���������ǰҪ�ǵô�����ߡ�','gl_name':'̫����ָ��','gl_hint':'����Ҫ','gl_des':'��ˮ���������߽ϲ����Ҫ���̫����','gm_name':'��ðָ��','gm_hint':'�׷�','gm_des':'����ڽ��콫����ִ���Ƚ��£��׷�����ð��ע�������·�����ǿ���ҷ��������ð��','gz_name':'����ָ��','gz_hint':'����','gz_des':'���������Ϻã�����������Ƥ���Ļ����ɣ���ʹ�ö�������ʪ�ͻ���Ʒ��','hc_name':'����ָ��','hc_hint':'������','hc_des':'�����������ã������˻���������ѡ�������ַ�ʽ��','jt_name':'��ָͨ��','jt_hint':'�Ϻ�','jt_des':'�н�ˮ���ܼ��Ȳ�̫����·�泱ʪ�������˸�����ʻ��˾��Ӧ���Ӽ���ע������С�ļ�ʻ��','lk_name':'·��ָ��','lk_hint':'��ʪ','lk_des':'�н�ˮ��·�泱ʪ�������״򻬣���С�ļ�ʻ��','ls_name':'��ɹָ��','ls_hint':'����','ls_des':'�н�ˮ����������ɹ������Ҫ��ɹ����������׼��������Ŀռ䡣','mf_name':'����ָ��','mf_hint':'����','mf_des':'��ǿ�ķ���������Ū��ͷ�����������ͷ����ˮ�֣�����ͷ�����ʱ�ɣ�ʧȥ������ע�Ᵽ��ͷ������࣬ʹ��������ϴ��¶�ͻ����ء�','nl_name':'ҹ����ָ��','nl_hint':'������','nl_des':'��Ȼ�����죬�е�磬���ԱȽ�����ҹ���ֻҪ������׼���Ϳ��Է��������','pj_name':'ơ��ָ��','pj_hint':'�ϲ�����','pj_des':'������е���Щ���⣬�������ó���ơ�ƣ�����������Ϊ�á�','pk_name':'�ŷ���ָ��','pk_hint':'����','pk_des':'�������ã������˷ŷ��ݡ�','pl_name':'������Ⱦ��ɢ����ָ��','pl_hint':'��','pl_des':'�������������ڿ�����Ⱦ��ϡ�͡���ɢ����������������������','pp_name':'��ױָ��','pp_hint':'��ʪ','pp_des':'�������󣬽��������Ա�ʪ��˪�໯ױƷ������ѡ�÷�ɹ��ױƷ��','sg_name':'һ�仰��ʾָ��','sg_hint':'��ȱ','sg_des':'�������ж���Ⱦ�����żǵô����֣�С���������Լ��������β���������Ҫ���ٻ����˶���','tr_name':'����ָ��','tr_hint':'����','tr_des':'�н�ˮ����Ȼ���Դ󣬵��¶����ˣ��������Σ��ɲ�Ҫ��������ϣ�','uv_name':'������ǿ��ָ��','uv_hint':'����','uv_des':'���������߷��������������ر�������������ڻ��⣬����Ϳ��SPF��8-12֮��ķ�ɹ����Ʒ��','xc_name':'ϴ��ָ��','xc_hint':'����','xc_des':'����ϴ����δ��24Сʱ�����꣬����ڴ��ڼ�ϴ������ˮ��·�ϵ���ˮ���ܻ��ٴ�Ū�����İ�����','xq_name':'����ָ��','xq_hint':'�ϲ�','xq_des':'�н�ˮ����ˮ���ܻ�ʹ�����޶˵ع�����������������޾���ɣ�����������飬�ú�����һ���꾰����ᷢ�����е��������ǰ�ྻ�º͡����´�����','yd_name':'�˶�ָ��','yd_hint':'�ϲ���','yd_des':'�н�ˮ���ҷ�����ǿ���Ƽ��������ڽ��е�ǿ���˶�������ֻ����˶�����ע�Ᵽů��Я����ߡ�','yh_name':'Լ��ָ��','yh_hint':'�ϲ�����','yh_des':'�����з磬�����н�ˮ���������Լ�����һЩ���㣬������Լ�ᣬ��һ������׼����','ys_name':'��ɡָ��','ys_hint':'��ɡ','ys_des':'�н�ˮ���������ɡ�������ϲ���������������ܴ���Ȼ�������ܰ�Ϳ��֣��ڶ�ʱ�������������ɡ��','zs_name':'����ָ��','zs_hint':'��','zs_des':'�¶Ȳ��ߣ��������������������ˣ�������ʼ��͡�'}}";
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
