package karthikps.page;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import karthikps.particulars.Bookings;

public class UserHomePage extends BasePage{

	List<WebElement> invoiceDivs;
	public List<Bookings> allBookings;
	public static String invoiceMainDiv = "id=bookings";
	public static String bookingDivs = "css=div#bookings div.row";
	public static String hotelDetailsInBookings = "css=div.col-md-5.offset-0";
	public static String bookingDetailsInBookings = "css=div.col-md-3";
	public static String hotelCostField = "css=a.dark b";
	public static String hotelLocation = "css=span.dark.size12";
	public static String hotelCost = "css=span.opensans.green.bold.size14";
	
	
	public UserHomePage(WebDriver driver) {
		super(driver);
	}
	
	public void fetchAllInvoices() {
		waitForElementToStabilizeInSize(bookingDivs);
		invoiceDivs = waitForElementsToAppear(bookingDivs);
		allBookings = new ArrayList<Bookings>();
		for (WebElement webElement : invoiceDivs) {
			WebElement hotelDetails = webElement.findElement(getLocator(hotelDetailsInBookings));
			Bookings booking = new Bookings();
			
			/*booking.setBookingCode(bookingCode);
			booking.setBookingId(bookingId);
			booking.setCost(cost);
			booking.setCurrency(currency);
			booking.setDueDate(dueDate);
			booking.setHotelName(hotelName);*/
			
			System.out.println(hotelDetails.findElement(getLocator(hotelCostField)).getText());
		}
	}

}
