package karthikps.constants;

import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

public class ResourceProperties {

	public static Properties properties;
	public static String chromeDriverPath = getPropertyValue("chromeDriver");
	public static String browserUrl = getPropertyValue("browserUrl");
	
	public static void loadProperties(){
		properties = new Properties();
		try {
			properties.load(ResourceProperties.class.getClassLoader().getResourceAsStream("config.properties"));
			/*Set<Object> keySet = properties.keySet();
			Iterator iterator = keySet.iterator();
			while(iterator.hasNext()) {
				String key = (String) iterator.next();
				String value = properties.getProperty(key);
			}*/
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getPropertyValue(String key) {
		if(properties == null) {
			loadProperties();
		}
		return properties.getProperty(key);
	}
	
}
