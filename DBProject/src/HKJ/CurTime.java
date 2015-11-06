package HKJ;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CurTime {
	public static String getTime(){
		long time = System.currentTimeMillis(); 
		SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String str = dayTime.format(new Date(time));
		return str;
	}
}
