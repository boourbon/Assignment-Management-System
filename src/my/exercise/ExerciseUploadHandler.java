package my.exercise;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import my.Config;
import my.db.Exercise;
import my.dbutil.DBUtil;
import my.util.CommonUtility;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import af.fileupload.UploadFileItem;
import af.fileupload.UploadFormItem;
import af.fileupload.UploadHandler;

public class ExerciseUploadHandler extends UploadHandler
{
	static Logger logger = Logger.getLogger(ExerciseUploadHandler.class);
	
	int exercise = 0;
	
	public ExerciseUploadHandler(HttpServletRequest httpReq)
	{
		super(httpReq);				
	}

	@Override
	public void check() throws Exception
	{
		exercise = Integer.valueOf( this.httpReq.getParameter("id"));
	}

	@Override
	public JSONObject handleFile(UploadFileItem fileinfo,
			List<UploadFormItem> formitems) throws Exception
	{
		String sql = "FROM Exercise WHERE id=" + exercise;
		Exercise row = (Exercise) DBUtil.get(sql, false);
		if(row == null)
			throw new Exception("Invalid Homework ID, " + exercise);
		
		try{
			File f = new File(Config.appPath, row.getStorePath());
			FileUtils.deleteQuietly( f );
		}catch(Exception e)
		{			
		}
		
		File tmpFile = fileinfo.tmpFile;
		String storePath = "files/" + CommonUtility.date2Path2() + tmpFile.getName();
		
		File dstFile = new File (Config.appPath, storePath);
		dstFile.getParentFile().mkdirs();
		
		FileUtils.moveFile(tmpFile, dstFile);
		
		row.setStorePath( storePath );
		row.setStatus( (short) 1);
		DBUtil.update( row );
		logger.debug("File transfered to: " + dstFile);
		
		JSONObject result = new JSONObject();
		result.put("storePath", storePath);
		return result;
	}

}
