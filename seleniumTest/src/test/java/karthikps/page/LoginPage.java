package karthikps.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage{

	public String emailField = "name=username";
	public String passwordField = "name=password";
	public String signInButton = "css=button.btn.btn-action.btn-lg.btn-block.loginbtn";
	public String loginLink = "xpath=(//a[contains(text(),'Login')])[2]";
	public LoginPage(WebDriver driver) {
		super(driver);
	}

	public UserHomePage login(String username, String password) {
		
		/*waitForElementToAppear("id=username").click();
		waitForElementToAppear("id=username").sendKeys("admin");
		waitForElementToAppear("id=password").click();
		waitForElementToAppear("id=password").sendKeys("everteam@0665");
		waitForElementToAppear("css=.btn.btn-info.btn-block").click();*/
		
		waitForElementToAppear(myAccountLink).click();
		waitForElementToBeClickable(loginLink).click();
		waitForElementToBeClickable(emailField).sendKeys(username);
		waitForElementToBeClickable(passwordField).sendKeys(password);
		waitForElementToBeClickable(signInButton).click();
		
		UserHomePage userHomePage = new UserHomePage(driver);
		return userHomePage;
	}
}
