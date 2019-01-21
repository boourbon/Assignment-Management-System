package my.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtility
{
	public static SimpleDateFormat sdfPathFormat = new SimpleDateFormat("yyyy/MM/dd/"); 
	public static SimpleDateFormat sdfPathFormat2 = new SimpleDateFormat("yyyyMM/dd/");
	public static SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmss"); 
	public static String fileSuffix(String fileName)
	{
		int p = fileName.lastIndexOf('.');
		if(p >= 0)
		{
			return fileName.substring(p+1).toLowerCase();
		}
		return "";
	}
	
	public static String date2Path()
	{
		return sdfPathFormat.format(new Date());
	}
	
	public static String date2Path2()
	{
		return sdfPathFormat2.format(new Date());
	}
	
	public static String date2Path(Date d)
	{
		return sdfPathFormat.format(d);
	}
	public static String time2Path()
	{
		return timeFormat.format(new Date());
	}
}
