package karthikps.strategies.search;

import java.util.List;
import java.util.regex.Pattern;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import karthikps.page.BasePage;

public class HotelSearchStrategy implements ISearchStrategy{

	public BasePage page;
	
	public final String LOCATION_FIELD = "css=input.select2-input";
	public final String BOOKING_FROM_DATE = "css=#dpd1 input.form-control.input-lg.dpd1";
	public final String BOOKING_TO_DATE = "css=#dpd2 input.form-control.input-lg.dpd2";
	public final String TRAVELLER_INPUT_FIELD = "css=input#travellersInput";
	public final String ADULTS_COUNT_FIELD = "id=adultInput";
	public final String CHILD_COUNT_FIELD = "id=childInput";
	public final String SEARCH_BUTTON = "css=button.btn.btn-lg.btn-block.btn-danger.pfb0.loader";
	
	public final String SEARCH_RESULTS_TABLE = "css=table.bgwhite.table.table-striped";
	
	public HotelSearchStrategy(BasePage page) {
		this.page = page;
	}
	
	@Override
	public void searchInAmenities(String... parameters) {
		page.waitForElementToAppear("css=a.select2-choice").click();
		page.enterValuesToInputField(LOCATION_FIELD,parameters[0].substring(0, 5));
		page.waitForElementToStabilizeInSize("css=ul.select2-results");
		List<WebElement> resultList = page.waitForElementsToAppear("css=ul.select2-results li");
		
		for(WebElement result: resultList) {
			try {
				if(result.findElement(page.getLocator("css=ul > li > div")).getText().equals(parameters[0])) {
					result.findElement(page.getLocator("css=ul > li > div > span")).click();
					page.enterValuesToInputField(BOOKING_FROM_DATE, parameters[1]);
					page.enterValuesToInputField(BOOKING_TO_DATE, parameters[2]);
					page.waitForElementToAppear(TRAVELLER_INPUT_FIELD).click();
					page.enterValuesToInputField(ADULTS_COUNT_FIELD, parameters[3]);
					page.enterValuesToInputField(CHILD_COUNT_FIELD, parameters[4]);
					page.waitForElementToAppear(SEARCH_BUTTON).click();
					break;
				}
			}
			catch(NoSuchElementException ne) {
				System.out.println(ne.getMessage());
			}
			catch(StaleElementReferenceException se) {
				se.printStackTrace();
			}
		}
	}

	@Override
	public List<WebElement> getSearchResults(String searchPattern) {
		page.waitForElementToStabilizeInSize(SEARCH_RESULTS_TABLE);
		List<WebElement> resultList = page.waitForElementsToAppear("css=tbody > tr > td.wow.fadeIn.p-10-0.animated");
		int result = 0;
		for(WebElement element: resultList) {
			if(Pattern.compile(Pattern.quote(searchPattern.substring(0, searchPattern.indexOf(","))), 
					Pattern.CASE_INSENSITIVE).matcher(element.getText()).find())
				continue;
			else
				result = 1;
		}
		if(result != 0) {
			throw new AssertionError("Search results not relevant");
		}
		else
			return resultList;
	}
}
