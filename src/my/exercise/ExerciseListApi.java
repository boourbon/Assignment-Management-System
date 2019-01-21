package my.exercise;

import java.util.List;

import my.ApiUtility;
import my.course.CourseListApi;
import my.dbutil.DBCol;
import my.dbutil.DBUtil;
import my.dbutil.SqlWhere;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import af.restful.AfRestfulApi;

public class ExerciseListApi  extends AfRestfulApi
{

	static Logger logger = Logger.getLogger(ExerciseListApi.class);

	@Override
	public String execute(String reqText) throws Exception
	{
		logger.debug("....... ExerciseListApi .........");

		int errorCode = 0;
		String reason = "OK";
		
		JSONObject jsReq = new JSONObject(reqText);
		
		SqlWhere where = new SqlWhere();
		if(jsReq.has("course"))
		{
			where.addExact("course", jsReq.getInt("course"));
		}
		if(jsReq.has("assignment"))
		{
			where.addExact("assignment", jsReq.getInt("assignment"));
		}
		if(jsReq.has("student"))
		{
			where.addExact("student", jsReq.getString("student"));
		}
		
		JSONArray result = new JSONArray();
		try
		{
			// calculate page number
//			String sql = "FROM Exercise" + where; // HQL: 大写的Course
//			logger.debug("SQL: " + sql);
//			List rows = AfDbUtil.list(sql, false);
//			
//			result = new JSONArray( rows );
			
			result= list ( where );
			
		} catch (Exception e)
		{
			//e.printStackTrace();
			return ApiUtility.reply(-1, "Error:" + e.getMessage());
		}

		JSONObject jsReply = new JSONObject();
		jsReply.put("errorCode", errorCode);
		jsReply.put("reason", reason);
		jsReply.put("result", result);
		return jsReply.toString();
	}

	private JSONArray list ( SqlWhere where) throws Exception
	{
		String sql = "SELECT a.id, a.title, a.student, a.score, a.status, b.displayName,a.assignment, a.timeCreated,a.storePath "
				+ " FROM exercise a  JOIN student b ON a.student=b.id "
				+ where;
		logger.debug( "SQL: " + sql);
		
		JSONArray result = new JSONArray();
		List rawdata = DBUtil.list(sql, true);
		for(int i=0; i<rawdata.size(); i++)
		{
			Object[] values = (Object[]) rawdata.get(i);
			int k = 0;
			
			JSONObject json = new JSONObject();			
			json.put("id", DBCol.asInt( values[k++],0));
			json.put("title", DBCol.asString( values[k++],""));
			json.put("student", DBCol.asString( values[k++],""));
			json.put("score", DBCol.asInt( values[k++],0));
			json.put("status", DBCol.asInt( values[k++],0));
			json.put("studentName", DBCol.asString( values[k++],""));
			json.put("assignment", DBCol.asInt( values[k++],0));
			json.put("timeCreated", DBCol.asString( values[k++],""));
			json.put("storePath", DBCol.asString( values[k++],""));
			result.put(json );
		}
		
		return result;
	}

}
