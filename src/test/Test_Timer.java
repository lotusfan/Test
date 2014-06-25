package test;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Test_Timer {
	public static void main(String[] args) {

		Timer time = new Timer();
		myTask my = new myTask();

		time.schedule(my, new Date(), 3000);

	}

}

class myTask extends TimerTask {

	@ Override
	public void run() {

		// TODO Auto-generated method stub
		System.out.println("ÄãºÃ");
		System.out.println(System.currentTimeMillis());

	}

}
