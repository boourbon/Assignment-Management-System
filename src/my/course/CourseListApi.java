package my.course;


import java.util.List;

import javax.servlet.http.HttpSession;

import my.ApiUtility;
import my.dbutil.DBUtil;
import my.dbutil.SqlWhere;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;


import af.restful.AfRestfulApi;


public class CourseListApi extends AfRestfulApi
{
	static Logger logger = Logger.getLogger(CourseListApi.class);

	@Override
	public String execute(String reqText) throws Exception
	{
		logger.debug("....... CourseListApi .........");

		String orgId = "NA";

		int errorCode = 0;
		String reason = "OK";
		
		JSONObject jsReq = new JSONObject(reqText);
		
		SqlWhere where = new SqlWhere();
		if(jsReq.has("teacher"))
		{
			where.addExact("teacher", jsReq.getInt("teacher"));
		}
		
		JSONArray result = new JSONArray();
		try
		{
			String sql = "FROM Course" + where;
			logger.debug("SQL: " + sql);
			List rows = DBUtil.list(sql, false);
			
			result = new JSONArray( rows );
			
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


}
