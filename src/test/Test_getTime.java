package test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Test_getTime {
	public static void main(String[] args) {

		Calendar cal = Calendar.getInstance();
		System.out.println(cal.getTimeInMillis());
		Date date = new Date();
		System.out.println(date);
		DateFormat datef = DateFormat.getDateInstance();
		System.out.println(datef.format(date));
		DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM, Locale.CHINA);
		System.out.println(df.format(date));
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd-HH.mm.ss");
		String datestr = formatter.format(new Date());
		System.out.println(datestr);
		System.out.println(new Date(1399602456245L));
	}
}
