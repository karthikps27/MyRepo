package karthikps.testcase;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import karthikps.page.BookHotelsPage;
import karthikps.page.LoginPage;
import karthikps.page.UserAccountPage;
import karthikps.particulars.Bookings;

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
			UserAccountPage accountPage = loginPage.login("user@phptravels.com", "demouser");
			List<Bookings> fetchAllInvoices = accountPage.fetchAllInvoices();
			Assert.assertTrue(fetchAllInvoices.size() > 1);
			Assert.assertTrue(Integer.parseInt(fetchAllInvoices.get(0).getBookingId()) > 0);
			System.out.println("Fetched");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(suiteName="basic", groups = {"basic"}, testName = "testSearchingHotels", dataProvider="locationsProvider")
	public void testSearchingHotels(String location, String fromDate, String toDate, String adultCount, String childCount) {
		try {
			LoginPage loginPage = new LoginPage(driver);
			UserAccountPage accountPage = loginPage.login("user@phptravels.com", "demouser");
			BookHotelsPage gotoHotelsMenu = accountPage.gotoHotelsMenu();
			gotoHotelsMenu.searchStrategy.searchInAmenities(location, fromDate, toDate, adultCount, childCount);
			List<WebElement> searchResults = gotoHotelsMenu.searchStrategy.getSearchResults(location);
			System.out.println("Printing Details: " + searchResults.get(0).findElement(gotoHotelsMenu.getLocator("css=div.col-md-6.col-xs-4.g0-left")).getText());
		}
		catch(AssertionError e) {
			e.printStackTrace();
			Assert.fail("Exceptions thrown hence failing testcase");
		}
	}
	
	@DataProvider
	public Object[][] locationsProvider() {
		return new Object[][] {
			new Object[] {"Singapore, Singapore", "04/08/2018", "05/08/2018","1","0"},
			new Object[] {"Dubai, United Arab Emirates", "04/08/2018", "05/08/2018","1","0"},
		};
	}
}
