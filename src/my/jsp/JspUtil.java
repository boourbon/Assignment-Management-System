package my.jsp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import my.db.Assignment;
import my.db.Exercise;
import my.dbutil.DBUtil;

import org.json.JSONObject;

public class JspUtil
{
	public static JSONObject getAssignment ( HttpServletRequest httpReq) throws Exception
	{
		int assignment = Jsp.getInt(httpReq, "assignment", 0);
		String sql = "FROM Assignment WHERE id=" + assignment;
		Assignment row = (Assignment) DBUtil.get(sql, false);
		return new JSONObject(row);
	}
	
	public static JSONObject getExercise ( HttpServletRequest httpReq) throws Exception
	{
		int assignment = Jsp.getInt(httpReq, "exercise", 0);
		String sql = "FROM Exercise WHERE id=" + assignment;
		Exercise row = (Exercise) DBUtil.get(sql, false);
		return new JSONObject(row);
	}
	
	public static JSONObject currentUser ( HttpServletRequest httpReq) throws Exception
	{
		HttpSession httpSession = httpReq.getSession();
		Object user = httpSession.getAttribute("user");
		if( user == null) 
			return new JSONObject();
		else
			return new JSONObject ( user);

	}
}
