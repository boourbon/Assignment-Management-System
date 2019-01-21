package af.fileupload;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;


public class UploadHandler
{	
	protected HttpServletRequest httpReq;
	protected HttpSession httpSession;
	
	public UploadHandler(HttpServletRequest httpReq)
	{
		this.httpReq = httpReq;
		this.httpSession = httpReq.getSession();
	}
	
	public void check() throws Exception
	{
		
	}
	
	public JSONObject handleFile ( UploadFileItem fileinfo, List<UploadFormItem> formitems) throws Exception
	{
		return new JSONObject();
	}
}
