package karthikps.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import karthikps.constants.ResourceProperties;

public class DriverFactory {
	public static WebDriver driver = null;
	
	public static WebDriver getInstance(String browserType) {
		
		switch (browserType) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver",ResourceProperties.getPropertyValue("chromeDriver"));
			driver = new ChromeDriver();
			break;
		case "firefox":
			System.setProperty("webdriver.gecko.driver",ResourceProperties.getPropertyValue("firefoxDriver"));
			driver = new FirefoxDriver();
			break;
		default:
			break;
		}
		return driver;
	}
}
