package karthikps.page;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import karthikps.strategies.search.HotelSearchStrategy;
import karthikps.strategies.search.ISearchStrategy;

public abstract class BasePage {

	public String myAccountLink = "xpath=(//a[contains(text(),'My Account')])[2]";
	
	public WebDriver driver;
	public JavascriptExecutor je;
	
	public BasePage(WebDriver driver) {
		this.driver = driver;
		je = (JavascriptExecutor)driver;
	}
	
	public By getLocator(String locator) {
		String type = locator.substring(0,locator.indexOf("="));
		By by = null;
		switch (type) {
		case "css":
			by = By.cssSelector(locator.substring(locator.indexOf("=")+1));
			break;
		case "id":
			by = By.id(locator.substring(locator.indexOf("=")+1));
			break;
		case "name":
			by = By.name(locator.substring(locator.indexOf("=")+1));
			break;
		case "linktext":
			by = By.linkText(locator.substring(locator.indexOf("=")+1));
			break;
		case "xpath":
			by = By.xpath(locator.substring(locator.indexOf("=")+1));
			break;
		default:
			break;
		}
		return by;
	}
	
	public boolean isElementPresent(WebElement element) {
		Dimension size = element.getSize();
		if(size.height != 0 && size.width != 0)
			return true;
		return false;
	}
	
	public WebElement waitForElementToBeClickable(String locator) {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(getLocator(locator)));
		if(isElementPresent(element))
			return element;
		else {
			throw (new RuntimeException());
		}
	}
	
	public WebElement waitForElementToAppear(String locator) {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(getLocator(locator)));
		if(isElementPresent(element))
			return element;
		else {
			throw (new RuntimeException());
		}
	}
	
	public List<WebElement> waitForElementsToAppear(String locator) {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver arg0) {
				try {
					driver.findElements(getLocator(locator));
					return true;
				}
				catch (Exception e) {
					return false;
				}
			}
		});
		return(driver.findElements(getLocator(locator)));
	}
	
	public void waitForElementToStabilizeInSize(String locator) {
		Long currentTime = System.currentTimeMillis();
		Long afterTime = System.currentTimeMillis();
		Dimension initial = waitForElementToAppear(locator).getSize();
		int count = 0;
		while(afterTime-currentTime < 10000) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Dimension current = waitForElementToAppear(locator).getSize();
			if(current.width != initial.width || current.height != initial.height) {
				initial = current;
				afterTime = System.currentTimeMillis();
			}
			else
				count++;
			if(count > 3)
				break;				
		}
	}
	
	public void enterValuesToInputField(String locator, String text) {
		waitForElementToAppear(locator).click();
		waitForElementToAppear(locator).clear();
		waitForElementToAppear(locator).sendKeys(text);
	}
	
	public void scrollForLazyLoading() {
		je.executeScript("window.scrollby(0,250)", "");
	}
}
