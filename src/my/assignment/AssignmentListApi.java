package my.assignment;

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

public class AssignmentListApi extends AfRestfulApi
{
	static Logger logger = Logger.getLogger(AssignmentListApi.class);

	@Override
	public String execute(String reqText) throws Exception
	{
		logger.debug("....... AssignmentListApi .........");

		int errorCode = 0;
		String reason = "OK";
		
		Teacher user = (Teacher) httpSession.getAttribute("user");
		if(user == null)
			ApiUtility.reply(-1, "Please log in as a teacher!");
		
		JSONArray result = new JSONArray();
		try
		{
			JSONObject jsReq = new JSONObject(reqText);
			int course = jsReq.getInt("course");
			
			SqlWhere where = new SqlWhere();
			where.addExact("course", course);
			String sql = "FROM Assignment " + where;
			logger.debug("SQL: " + sql);
			
			List rows = DBUtil.list(sql, false);	
			result = new JSONArray ( rows );
			
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
