package my.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class HttpSessionUtil
{
	public static Long getLong(HttpSession ss, String paramName, Long defValue)
	{		
		try{
			return Long.valueOf(ss.getAttribute(paramName).toString()); 
		}catch(Exception e)
		{
			return defValue;
		}		
	}
	
	public static Integer getInt(HttpSession ss, String paramName, Integer defValue)
	{		
		try{
			return Integer.valueOf(ss.getAttribute(paramName).toString()); 
		}catch(Exception e)
		{
			return defValue;
		}		
	}

	public static String getString(HttpSession ss, String paramName, String defValue)
	{		
		String val = null;
		try{
			val = ss.getAttribute(paramName).toString();
		}catch(Exception e){}
		
		if(val == null || val.length() == 0)
		{
			val = defValue;
		}
		return val;
	}
	
	public static boolean getBoolean(HttpSession ss, String paramName, boolean defValue)
	{		
		Boolean val = null;
		try{			
			val = (Boolean) ss.getAttribute(paramName);
		}catch(Exception e)
		{			
		}	
		if(val == null) return defValue;
		return val;
	}
	
	public static Object getObject(HttpSession ss, String paramName, Object defValue)
	{		
		Object val = null;
		try{			
			val = (Object) ss.getAttribute(paramName);
		}catch(Exception e)
		{			
		}	
		if(val == null) return defValue;
		return val;
	}
	
}
