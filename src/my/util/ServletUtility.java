package my.util;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

public class ServletUtility
{
	public static void replyJsonError(HttpServletRequest request, 
			HttpServletResponse response, 
			int errorCode, String reason)
	{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/plain");		
		JSONObject jsReply = new JSONObject();
		jsReply.put("error", errorCode);
		jsReply.put("reason", reason);
		try{
			PrintWriter writer = response.getWriter();
			writer.write(jsReply.toString());
			writer.close();
		}catch(Exception e){}
	}
	
	public static String getContextPath(HttpServletRequest request)
	{
		String url = "http://"
				+ request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath() ;
		return url;
	}
}
