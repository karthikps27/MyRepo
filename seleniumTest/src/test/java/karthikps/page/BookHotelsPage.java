package karthikps.page;

import org.openqa.selenium.WebDriver;

import karthikps.strategies.search.HotelSearchStrategy;

public class BookHotelsPage extends BookAmenitiesPage{
	
	public BookHotelsPage(WebDriver driver) {
		super(driver);
		searchStrategy = new HotelSearchStrategy(this);
	}
}
