package my.teacher;

import java.util.List;

import my.ApiUtility;
import my.course.CourseListApi;
import my.db.Teacher;
import my.dbutil.DBUtil;
import my.dbutil.SqlWhere;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import af.restful.AfRestfulApi;

public class TeacherLoginApi extends AfRestfulApi
{
	static Logger logger = Logger.getLogger(TeacherLoginApi.class);

	@Override
	public String execute(String reqText) throws Exception
	{
		logger.debug("....... TeacherLoginApi .........");

		int errorCode = 0;
		String reason = "OK";
		
		JSONArray result = new JSONArray();
		try
		{
			JSONObject jsReq = new JSONObject(reqText);
			String username = jsReq.getString("username").trim();
			String password = jsReq.getString("password").trim();
			
			SqlWhere where = new SqlWhere();
			where.addExact("username", username);
			String sql = "FROM Teacher " + where;
			logger.debug("SQL: " + sql);
			
			Teacher row = (Teacher) DBUtil.get(sql, false);
			if(row == null)
				throw new Exception("No such user!");
			if( ! password.equals(row.getPassword()))
				throw new Exception("Password doesn't match!");
					
			httpSession.setAttribute("role", "teacher"); 
			httpSession.setAttribute("user", row);			
			
		} catch (Exception e)
		{
			e.printStackTrace();
			return ApiUtility.reply(-1, "Database Error:" + e.getMessage());
		}

		JSONObject jsReply = new JSONObject();
		jsReply.put("errorCode", errorCode);
		jsReply.put("reason", reason);
		jsReply.put("result", result);
		return jsReply.toString();
	}
}
