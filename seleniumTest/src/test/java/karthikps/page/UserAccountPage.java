package karthikps.page;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import karthikps.particulars.Bookings;

public class UserAccountPage extends BasePage{

	List<WebElement> invoiceDivs;
	public List<Bookings> allBookings;
	public static String invoiceMainDiv = "id=bookings";
	public static String bookingDivs = "css=div#bookings div.row";
	public static String hotelDetailsInBookings = "css=div.col-md-5.offset-0";
	public static String bookingDetailsInBookings = "css=div.col-md-3";
	public static String hotelNameField = "css=a.dark b";
	public static String hotelLocation = "css=span.dark.size12";
	public static String hotelCost = "css=span.opensans.green.bold.size14";
	
	//div.row:nth-child(2) > div:nth-child(2) > span:nth-child(1)
	
	public UserAccountPage(WebDriver driver) {
		super(driver);
	}
	
	public List<Bookings> fetchAllInvoices() {
		waitForElementToStabilizeInSize(bookingDivs);
		invoiceDivs = waitForElementsToAppear(bookingDivs);
		allBookings = new ArrayList<Bookings>();
		for (WebElement webElement : invoiceDivs) {
			WebElement hotelDetails = webElement.findElement(getLocator(hotelDetailsInBookings));
			WebElement bookingDetails = webElement.findElement(getLocator(bookingDetailsInBookings));
			
			Bookings booking = new Bookings();
			
			booking.setCost(hotelDetails.findElement(getLocator(hotelCost)).getText());
			booking.setHotelName(hotelDetails.findElement(getLocator(hotelNameField)).getText());
			String location = hotelDetails.findElement(getLocator(hotelLocation)).getText();
			if(location.indexOf("/") > 0)
				booking.setLocation(location.substring(0,location.indexOf("/")-1));
			else
				booking.setLocation(location);
			String bookingDetail = bookingDetails.findElement(getLocator("css=span.grey")).getText();
			booking.setBookingId(bookingDetail.substring(bookingDetail.indexOf("ID")+3,bookingDetail.indexOf("\n")));
			booking.setBookingCode(bookingDetail.substring(bookingDetail.indexOf("Code")+5,bookingDetail.indexOf("\nDue")));
			booking.setDueDate(bookingDetail.substring(bookingDetail.indexOf("Date")+5,bookingDetail.length()));
			
			allBookings.add(booking);
		}
		return allBookings;
	}
	
	public BookHotelsPage gotoHotelsMenu() {
		waitForElementToAppear("linktext=Hotels").click();
		return new BookHotelsPage(driver);
	}

}
