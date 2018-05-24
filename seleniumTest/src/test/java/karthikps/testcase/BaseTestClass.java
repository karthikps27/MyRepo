package karthikps.testcase;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import karthikps.constants.ResourceProperties;
import karthikps.utils.DriverFactory;

public abstract class BaseTestClass {

	public WebDriver driver;
	
	@BeforeMethod(alwaysRun=true)
	public void setUp() {
		driver = DriverFactory.getInstance("firefox");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		//log.info("-- Testing on: " + ResourceProperties.browserUrl);
		driver.get(ResourceProperties.browserUrl);
	}
	
	@AfterMethod
	public void tearDown() {
		driver.close();
	}
	
}
