package test;

import java.lang.reflect.Method;

public class Test_reflect {
	public static void main(String[] args) throws Exception {

		//OutName on = new OutName();
		Class outname = OutName.class;
		Object oo = outname.newInstance();
		OutName out = (OutName) oo;
		Method me = outname.getMethod("setPassword", String.class);
		System.out.println(outname.getMethod("kk", String.class));
		me.invoke(oo, "ÄãºÃÑ½");
		System.out.println(oo.getClass().getSimpleName());
		System.out.println(out.getPassword());
		/*
		 * for (int i = 0; i < on.getClass().getDeclaredMethods().length; i++) { System.out.println(on.getClass().getDeclaredMethods()[i].getName());
		 * }com.xcfh.voca.chailv
		 */
	}
}

class OutName {

	private String name;
	public String password;

	public void getName(String name) {

		System.out.println(name);
	}

	public String getPassword() {

		return this.password;
	}

	public void getObject(Object o) {

		System.out.println(o.getClass().getSimpleName());
	}

	public void setPassword(String pa) {

		this.password = pa;
	}
}
