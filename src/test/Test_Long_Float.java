package test;

public class Test_Long_Float {
	public static void main(String[] args) throws InterruptedException {

		Long time = System.currentTimeMillis();
		System.out.println(time.floatValue());
		float a = 10F / 3F;
		Long time2 = 3685L;
		a = Math.round(a * 1000);
		float b = (float) time2;
		System.out.println(b / 1000);
		ComputeTime ct = new ComputeTime();
		ct.computeStart();
		Thread.sleep(33654);
		System.out.println(ct.computeEnd());
	}
}

class ComputeTime {
	private long starttime;

	public void computeStart() {

		this.starttime = System.currentTimeMillis();
	}

	public float computeEnd() {

		return ((float) (System.currentTimeMillis() - starttime)) / 1000;
	}
}
