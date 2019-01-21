package my.util;

import org.json.JSONObject;




public class JsonUtil
{
	public static Integer getInt(JSONObject req, String name, Integer defValue)
	{
		try{
			return req.getInt(name);			
		}catch(Exception e)
		{
			return defValue;
		}
	}
	
	public static Boolean getBoolean(JSONObject req, String name, Boolean defValue)
	{
		try{
			return req.getBoolean(name);			
		}catch(Exception e)
		{
			return defValue;
		}
	}
	
	public static Long getLong(JSONObject req, String name, Long defValue)
	{
		try{
			return req.getLong(name);			
		}catch(Exception e)
		{
			return defValue;
		}
	}
	
	public static String getString(JSONObject req, String name, String defValue)
	{
		try{
			return req.getString(name);			
		}catch(Exception e)
		{
			return defValue;
		}
	}
	
}
