package my.dbutil;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class AfSqlSet
{
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	ArrayList<String> conditions = new ArrayList<String>();
	
	public void add(String condition)
	{
		conditions.add(condition);
	}

	public void addExact(String colName, String colValue)
	{
		conditions.add( colName + "='" + colValue + "'");
	}

	public void addExact(String colName, int colValue)
	{
		conditions.add( colName + "=" + colValue + "");
	}

	public void addExact(String colName, Long colValue)
	{
		conditions.add( colName + "=" + colValue + "");
	}
	public void addLike(String colName, String colValue)
	{
		conditions.add( colName + " LIKE '" + colValue + "'");
	}
	public void addTimestamp(String colName, Timestamp colValue)
	{
		conditions.add( colName + "='" + sdf.format(colValue) + "'");
	}
	
	public void addPlus(String colName, int colValue)
	{
		conditions.add( colName + "=" + "`" + colName + "`+" + colValue + "");
	}
	
	@Override
	public String toString()
	{
		if(conditions.size() == 0) return " ";
		
		String where = " SET ";
		for(int i=0; i<conditions.size() ;i++)
		{
			if(i != 0) where += " , ";
			where += conditions.get(i) + " ";
		}
		return where;
	}
}
