package my.dbutil;

import java.math.BigInteger;

public class DBCol
{
	public static Integer asInt (Object val, int defValue)
	{
		try{
			if (val instanceof Integer) return (Integer)val;
			return Integer.valueOf( val.toString());
		}catch(Exception e)
		{
		}
		return defValue;
	}
	
	public static Long asLong (Object val, long defValue)
	{
		try{
			if (val instanceof Long) return (Long)val;
			if (val instanceof BigInteger) return ((BigInteger)val).longValue();
			return Long.valueOf( val.toString());
		}catch(Exception e)
		{
		}
		return defValue;
	}	
	
	public static Short asShort (Object val, short defValue)
	{
		try{
			if (val instanceof Short) return (Short)val;
			if (val instanceof Byte) return ((Byte)val).shortValue();
			return Short.valueOf( val.toString());
		}catch(Exception e)
		{
		}
		return defValue;
	}	
	
	public static String asString (Object val, String defValue)
	{
		try{
			if (val instanceof String) return (String)val;
			return val.toString();
		}catch(Exception e)
		{
		}
		return defValue;
	}
	
	public static Boolean asBoolean (Object val, Boolean defValue)
	{
		try{
			if (val instanceof Boolean) return (Boolean)val;
			return Boolean.valueOf( val.toString());
		}catch(Exception e)
		{
		}
		return defValue;
	}
	
}
