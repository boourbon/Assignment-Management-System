package my.jsp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

public class JspTeacher
{
	public static JSONObject currentUser ( HttpServletRequest httpReq) throws Exception
	{
		HttpSession httpSession = httpReq.getSession();
		my.db.Teacher t = (my.db.Teacher) httpSession.getAttribute("user");
		if( t == null) 
			return new JSONObject();
		else
			return new JSONObject ( t);

	}
}
