package my;

import java.text.SimpleDateFormat;

import org.json.JSONObject;

public class ApiUtility
{

	
	public static String reply(int errorCode, String reason)
	{
		JSONObject jsReply = new JSONObject();
		jsReply.put("errorCode", errorCode);
		jsReply.put("reason", reason);
		return jsReply.toString();		
	}
	
	public static Integer reqInt(JSONObject req, String name, Integer defValue)
	{
		try{
			return req.getInt(name);			
		}catch(Exception e)
		{
			return defValue;
		}
	}
	
	public static Long reqLong(JSONObject req, String name, Long defValue)
	{
		try{
			return req.getLong(name);			
		}catch(Exception e)
		{
			return defValue;
		}
	}
	
	public static String reqString(JSONObject req, String name, String defValue)
	{
		try{
			return req.getString(name);			
		}catch(Exception e)
		{
			return defValue;
		}
	}	
}
