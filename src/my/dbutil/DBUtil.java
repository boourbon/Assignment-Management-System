package my.dbutil;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import my.db.HibernateSessionFactory;

import org.hibernate.Session;


public class DBUtil
{
	public static Timestamp now () 
	{
		return new Timestamp(System.currentTimeMillis());
	}
	public static String nowstr () 
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format( now() );
	}
	
	public static int columnInt (Object val, int defValue)
	{
		try{
			return Integer.valueOf( val.toString());
		}catch(Exception e)
		{
		}
		return defValue;
	}
	public static long columnLong (Object val, long defValue)
	{
		try{
			return Long.valueOf( val.toString());
		}catch(Exception e)
		{
		}
		return defValue;
	}	
	
	public static Object save (Object row)  throws Exception
	{
		Session dbss = HibernateSessionFactory.getSession();
		try
		{
			dbss.beginTransaction();
			dbss.save(row);
			dbss.getTransaction().commit();
		} finally
		{
			if (dbss != null)
				dbss.close();
		}
		return row;
	}
	
	public static Object update(Object row)  throws Exception
	{
		Session dbss = HibernateSessionFactory.getSession();
		try
		{
			dbss.beginTransaction();
			dbss.update(row);
			dbss.getTransaction().commit();
			return row;
		} finally
		{
			if (dbss != null)
				dbss.close();
		}
	}

	public static Object saveOrUpdate(Object row)  throws Exception
	{
		Session dbss = HibernateSessionFactory.getSession();
		try
		{
			dbss.beginTransaction();
			dbss.saveOrUpdate(row);
			dbss.getTransaction().commit();
			return row;
		} finally
		{
			if (dbss != null)
				dbss.close();
		}
	}
	
	public static Object get (String sql, boolean nativeSQL) throws Exception
	{
		Session dbss = HibernateSessionFactory.getSession();
		try
		{
			List rawdata = null;
			if(nativeSQL)
				rawdata = dbss.createSQLQuery(sql).list();
			else
				rawdata = dbss.createQuery(sql).list();

			if(rawdata.size()  > 0)
				return rawdata.get(0);
		} finally
		{
			if (dbss != null)
				dbss.close();
		}
		return null;
	}

	public static List  list(String sql, boolean nativeSQL) throws Exception
	{
		Session dbss = HibernateSessionFactory.getSession();
		try
		{
			List rawdata = null;
			if(nativeSQL)
				rawdata = dbss.createSQLQuery(sql).list();
			else
				rawdata = dbss.createQuery(sql).list();

			return rawdata;
		} finally
		{
			if (dbss != null)
				dbss.close();
		}
	}
	
	public static List  listN(String sql, boolean nativeSQL, int N) throws Exception
	{
		Session dbss = HibernateSessionFactory.getSession();
		try
		{
			List rawdata = null;
			if(nativeSQL)
				rawdata = dbss.createSQLQuery(sql).setMaxResults(N).list();
			else
				rawdata = dbss.createQuery(sql).setMaxResults(N).list();

			return rawdata;
		} finally
		{
			if (dbss != null)
				dbss.close();
		}
	}
	
	public static int execute (String sql, boolean nativeSQL)  throws Exception
	{
		Session dbss = HibernateSessionFactory.getSession();
		try
		{
			int ret = 0;
			dbss.beginTransaction();
			if(nativeSQL)
				ret = dbss.createSQLQuery(sql).executeUpdate();
			else
				ret = dbss.createQuery(sql).executeUpdate();
			dbss.getTransaction().commit();
			
			return ret;
		} finally
		{
			if (dbss != null)
				dbss.close();
		}
	}
	

}
