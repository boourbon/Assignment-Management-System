package my;

import java.io.File;
import java.io.InputStream;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Config
{
	public static File appPath = new File("c:/");

	static Config one;
	public static Config i()
	{
		if(one == null) one = new Config();
		return one;
	}
	
	public void load() throws Exception
	{
		InputStream stream = this.getClass().getResourceAsStream(
				"/config.xml");
		SAXReader reader = new SAXReader();
		
		Document xmldoc = reader.read(stream);
		Element root = xmldoc.getRootElement();
		stream.close();
		
	}
}
