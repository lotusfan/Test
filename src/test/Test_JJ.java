package test;

public class Test_JJ {
	public static void main(String[] args) {

		String addressType = "yzm";
		String regionNum = "00123";
		if (addressType == null || regionNum == null || addressType.length() == 0 || regionNum.length() != 4) {
			System.out.println("true");
		}
		if (addressType == null && addressType.length() == 0) {
			System.out.println("addressType true");
		}
		if (regionNum == null || regionNum.length() != 4) {
			System.out.println("regionNum  true");
		}
	}
}
