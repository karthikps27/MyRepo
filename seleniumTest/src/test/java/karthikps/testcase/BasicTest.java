package karthikps.testcase;

import java.util.Arrays;

import org.testng.annotations.Test;

import karthikps.page.LoginPage;
import karthikps.page.UserHomePage;

public class BasicTest extends BaseTestClass{

	@Test(suiteName="basic", groups = {"basic"}, testName = "testLogin")
	public void testLogin() {
		try {
			LoginPage loginPage = new LoginPage(driver);
			loginPage.login("user@phptravels.com", "demouser");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(suiteName="basic", groups = {"basic"}, testName = "testViewingInvoices")
	public void testViewingInvoices() {
		try {
			LoginPage loginPage = new LoginPage(driver);
			UserHomePage homePage = loginPage.login("user@phptravels.com", "demouser");
			homePage.fetchAllInvoices();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
