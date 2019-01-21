package my.util;

import java.io.InputStream;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XmlUtil
{
	private void example() throws Exception
	{
		InputStream stream = this.getClass().getResourceAsStream(
				"/baiduyun.xml");
		SAXReader reader = new SAXReader();

		Document xmldoc = reader.read(stream);
		Element root = xmldoc.getRootElement();
		stream.close();

		Element xml_baiduyun = root.element("baiduyun");
		String baiduKey = child(xml_baiduyun, "key", "");
		
	}
	
	public static String child(Element p, String name, String defValue)
	{
		try{
			String value = p.elementText(name).trim();
			return value;
		}catch(Exception e)
		{
			return defValue;
		}
	}
	
	public static Integer childAsInt(Element p, String name, Integer defValue)
	{
		try{
			int value = Integer.valueOf(child(p, name, defValue.toString()));
			return value;
		}
		catch(Exception e)
		{
			return defValue;
		}
	}
}
