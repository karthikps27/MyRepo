package karthikps.strategies.search;

import java.util.List;

import org.openqa.selenium.WebElement;

import karthikps.page.BasePage;

public interface ISearchStrategy {

	public void searchInAmenities(String...parameters);
	public List<WebElement> getSearchResults(String searchPattern);
	
}
