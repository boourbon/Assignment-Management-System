package my;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;

public class BeanFactory implements Filter
{
	static Logger logger = Logger.getLogger(BeanFactory.class);
	
	@Override
	public void init(FilterConfig params) throws ServletException
	{		
		String appPath = params.getServletContext().getRealPath("/");
		File appDir = new File(appPath);
		Config.appPath = appDir;
		logger.info("** App Path:  " + appDir);
		
		try
		{
			Config.i().load();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
	

	@Override
	public void destroy()
	{	
		
		//AutoCleanTask.i().stopService();
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException
	{
		chain.doFilter(req,  resp);
	}


}
