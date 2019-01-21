package af.fileupload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import my.exercise.ExerciseUploadHandler;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.log4j.Logger;
import org.json.JSONObject;

public class CommonFileUpload extends HttpServlet
{
	static Logger logger = Logger.getLogger(CommonFileUpload.class);
		
	public static File TMPDIR = new File("c:/tmp");
	

	public CommonFileUpload()
	{		
	}
	
	public UploadHandler createHandler(String type,HttpServletRequest request)
	{
		if("exercise".equals(type))
		{
			return new ExerciseUploadHandler( request);
		}
		
		return new UploadHandler(request);

	}
	
	@Override
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response)
			throws ServletException, IOException
	{
		doUpload(request, response);
	}
	
	public void doUpload(HttpServletRequest request, 
			HttpServletResponse response)
			throws ServletException, IOException
	{
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		request.setCharacterEncoding("UTF-8");
		String type = request.getParameter("type");
		
		UploadHandler handler = createHandler(type,request);
		JSONObject result = new JSONObject();
		
		ServletFileUpload upload = new ServletFileUpload();
		
		UploadFileItem fileinfo = new UploadFileItem();
		List<UploadFormItem> formitems = new ArrayList();
		
		try{
			handler.check();
		}catch(Exception e)
		{
			logger.warn("Error occurs before upload: " + e.getMessage());
			reply(request, response, -1, e.getMessage(), result);
			return;
		}
		
		try{
			FileItemIterator iter = upload.getItemIterator(request);
			while (iter.hasNext()) 
			{
			    FileItemStream item = iter.next();
			    String fieldName = item.getFieldName();
			    InputStream fieldStream = item.openStream();
			    if (item.isFormField())
			    {		    	
			    	String fieldValue = Streams.asString(fieldStream, "UTF-8");
			    	System.out.println("Form Field:" + fieldName + "=" + fieldValue);
			    	formitems.add( new UploadFormItem( fieldName, fieldValue));
			    } 
			    else 
			    {
			    	String fileName = item.getName();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss");
					String datestr = sdf.format(new Date());
					String tmpFileName = datestr + "-" + createGUID() + "." + fileSuffix(fileName);
					
			    	fileinfo.fileName = fileName;
			    	fileinfo.tmpFile = new File(TMPDIR, tmpFileName);
			    	fileinfo.size = 0;
			    	
			        logger.debug("Uploading to web:" + fileinfo.fileName + " >> " + fileinfo.tmpFile);
				        	        
			        fileinfo.tmpFile.getParentFile().mkdirs();
			        
			        FileOutputStream streamOut = new FileOutputStream(fileinfo.tmpFile);
			        byte[] buf = new byte[8192];
			        while(true)
			        {
			        	int n = fieldStream.read(buf);
			        	if(n<0) break;
			        	if(n==0) continue;			        	
			        	streamOut.write(buf, 0, n);	
			        	
			        	fileinfo.size += n;
			        }
			        fieldStream.close();
			        streamOut.close();
			        		        
			        try
					{
			        	logger.debug("File Received:" + fileinfo.tmpFile.getAbsolutePath());
			        	result = handler.handleFile(fileinfo, formitems);
						logger.debug("Whole process finished:" + fileinfo.tmpFile.getAbsolutePath());
					} 
			        catch (Exception e)
					{
						e.printStackTrace();
						reply(request, response, -1, e.getMessage(), result);
						return;
					}
			    }
			}
		}
		catch(FileUploadException e)
		{
			e.printStackTrace();
			reply(request, response, -1, e.getMessage(), result);
			return;
		}
		
		reply(request, response, 0, "OK", result);	
	}
	
	public void reply(HttpServletRequest request, 
			HttpServletResponse response, 
			int errorCode, String reason, JSONObject result)
	{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/plain");		
		JSONObject jsReply = new JSONObject();
		jsReply.put("error", errorCode);
		jsReply.put("reason", reason);
		jsReply.put("result", result);
		try{
			PrintWriter writer = response.getWriter();
			writer.write(jsReply.toString());
			writer.close();
		}catch(Exception e){}
	}

	private String createGUID ()
	{
		 String s = UUID.randomUUID().toString(); 
	     String s2 = s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24); 
	     return s2.toUpperCase();
	}
	
	public String fileSuffix(String fileName)
	{
		int p = fileName.lastIndexOf('.');
		if(p >= 0)
		{
			return fileName.substring(p+1).toLowerCase();
		}
		return "";
	}
}
	

