package weekn.wreport.tools;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TimeUtils {
	public static void main(String[] args) {
		Long a=new Date().getTime();
		getWeeknStartEnd(a);
	}
   static public Long getCurrentTimeStamp() {
	   return new Date().getTime();
   }
   static public Map<String, Long> getWeeknStartEnd(Long timestamp) {
	   Calendar cal = Calendar.getInstance();
	   cal.setTime(new Date(timestamp));
	   int w =cal.get(Calendar.DAY_OF_WEEK)-1;//这个星期从周日开始的，所以减1
	   
	   cal.set(Calendar.HOUR_OF_DAY, 0);
	   cal.set(Calendar.MINUTE, 0);
	   cal.set(Calendar.SECOND, 0);
	   Long data_zero=cal.getTime().getTime();//时间当天的零点时间
	   Long start=data_zero-(w-1)*(1000*24*60*60);
	   Long end=data_zero+(8-w)*(1000*24*60*60);
	   Map<String, Long> res=new HashMap<String, Long>();
	   res.put("start", start);
	   res.put("end", end);
	   return res;
   }
}
