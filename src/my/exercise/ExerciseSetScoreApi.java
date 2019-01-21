package my.exercise;

import java.util.List;

import my.ApiUtility;
import my.course.CourseListApi;
import my.db.Exercise;
import my.db.Teacher;
import my.dbutil.DBCol;
import my.dbutil.DBUtil;
import my.dbutil.SqlWhere;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import af.restful.AfRestfulApi;

public class ExerciseSetScoreApi  extends AfRestfulApi
{

	static Logger logger = Logger.getLogger(ExerciseSetScoreApi.class);

	@Override
	public String execute(String reqText) throws Exception
	{
		logger.debug("....... ExerciseSetScoreApi .........");

		int errorCode = 0;
		String reason = "OK";
				
		String role = (String)httpSession.getAttribute("role");
		if( ! "teacher".equals(role))
			return ApiUtility.reply(-1, "Please log in as a teacher!");
		
		Teacher user = (Teacher) httpSession.getAttribute("user");
				
		JSONArray result = new JSONArray();
		try
		{
			JSONObject jsReq = new JSONObject(reqText);
			int exercise = jsReq.getInt("exercise"); 
			int score = jsReq.getInt("score");
			
			String sql = "FROM Exercise WHERE id=" + exercise;
			logger.debug("SQL: " + sql);
			Exercise row = (Exercise) DBUtil.get(sql, false);
			
			if(row == null)
				throw new Exception("The homework doesn't exist: id= " + exercise);
			
			if(row.getTeachear() != user.getId())
				throw new Exception("This homework may not be your responsibility!");
			
			row.setScore( (short) score);
			DBUtil.update( row );
			
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
		String sql = "SELECT a.id, a.title, a.student, a.score, a.status, b.displayName,a.assignment, a.timeCreated "
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
			result.put(json );
		}
		
		return result;
	}

}
