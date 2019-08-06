import java.util.Calendar;//Leon Grinshpun 316226679
import java.util.GregorianCalendar; 

public class CalendarAdapter implements  ICalendar
{
	private Calendar calendar = new GregorianCalendar();

	@Override
	public int getHour()
	{
		return calendar.get(calendar.HOUR_OF_DAY);
	}

	@Override
	public int getMinute() 
	{
		return calendar.get(calendar.MINUTE);
	}

	@Override
	public int getSecond()
	{

		return calendar.get(calendar.SECOND);
	}	
}
