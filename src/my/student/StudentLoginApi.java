package my.student;

import java.util.List;

import my.ApiUtility;
import my.db.Student;
import my.dbutil.DBUtil;
import my.dbutil.SqlWhere;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import af.restful.AfRestfulApi;

public class StudentLoginApi extends AfRestfulApi
{
	static Logger logger = Logger.getLogger(StudentLoginApi.class);

	@Override
	public String execute(String reqText) throws Exception
	{
		logger.debug("....... StudentLoginApi .........");

		int errorCode = 0;
		String reason = "OK";

		/* 查询 */
		JSONArray result = new JSONArray();
		try
		{
			JSONObject jsReq = new JSONObject(reqText);
			String id = jsReq.getString("id").trim();
			String password = jsReq.getString("password").trim();
			
			SqlWhere where = new SqlWhere();
			where.addExact("id", id);
			String sql = "FROM Student " + where;
			logger.debug("SQL: " + sql);
			
			Student row = (Student) DBUtil.get(sql, false);
			if(row == null)
				throw new Exception("No such user!");
			if( ! password.equals(row.getPassword()))
				throw new Exception("Password doesn't match!");
			
			httpSession.setAttribute("role", "student");
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
