package test;

public class Test_finally {
	public static void main(String[] args) {

		if (true) return;
		try {
			System.out.println("111111111111");
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			System.out.println("222222222222222");
		}
	}
}
