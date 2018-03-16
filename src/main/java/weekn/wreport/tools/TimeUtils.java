package weekn.wreport.tools;

import java.util.Calendar;
import java.util.Date;

public class TimeUtils {
	public static void main(String[] args) {
		Long a=1521016083000L;
		getWeeknStartEnd(a);
	}
   static public Long getCurrentTimeStamp() {
	   return new Date().getTime();
   }
   static public void getWeeknStartEnd(Long timestamp) {
	   Calendar cal = Calendar.getInstance();
	   cal.setTime(new Date(timestamp));
	   int w =cal.get(Calendar.DAY_OF_WEEK);
	   System.out.println(w);
   }
}
