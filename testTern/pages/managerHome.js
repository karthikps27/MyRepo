/**
 * 
 */

var basePage = require('./basePage.js');

var managerHome = function() {
	
	var addCustomerButton = element(by.buttonText("Add Customer"));
	var openAccountButton = element(by.buttonText("Open Account"));
	var customerManageButton = element(by.buttonText("Customers"));
	var customerDropdownList = element(by.id("userSelect"));
	var currencyTypeList = element(by.id("currency"));
	var processButton = element(by.buttonText("Process"));
	var transactionTableLocator = by.css('.table.table-bordered.table-striped');
	
	var firstNameField = element(by.model("fName"));
	var lastNamefield = element(by.model("lName"));
	var postCodeField = element(by.model("postCd"));
	var actionButton = element(by.css("button.btn.btn-default"));
	
	this.addCustomers = function(firstname, lastname, postcode) {
		basePage.waitForElementToBeClickable(addCustomerButton).click();
		basePage.waitForElementToBeClickable(firstNameField).sendKeys(firstname);
		basePage.waitForElementToBeClickable(lastNamefield).sendKeys(lastname);
		basePage.waitForElementToBeClickable(postCodeField).sendKeys(postcode);
		basePage.waitForElementToBeClickable(actionButton).click();
	};
	
	this.addAccountsToCustomer = function(customerName, currency) {
		var deferred = protractor.promise.defer();
		basePage.waitForElementToBeClickable(openAccountButton).click();
		basePage.selectElementByText(basePage.waitForElementToBeClickable(customerDropdownList),
				customerName);
		basePage.selectElementByText(basePage.waitForElementToBeClickable(currencyTypeList),
				currency);
		basePage.waitForElementToBeClickable(processButton).click();
		
		var resultAlert = browser.switchTo().alert();
		resultAlert.getText().then(function(arg0) {
			var newAccountNo = arg0.substr(arg0.indexOf(":")+1, arg0.length);
			deferred.fulfill(newAccountNo);
		});
		resultAlert.accept();
		return deferred.promise;
	};
	
	this.checkAddedAccount = function(customerFirstName, accountNo) {
		basePage.waitForElementToBeClickable(customerManageButton).click();
		
		//basePage.getRowPosition(basePage.isElementPresent(element(transactionTableLocator)), customerName);
		basePage.getTableRowDetails(basePage.isElementPresent(element(transactionTableLocator)), 
		customerFirstName, "Account Number").then(function(addedAccountNo) {
				expect(addedAccountNo).toContain(accountNo);
			
		})
	};
};

managerHome.prototype = basePage;
module.exports = new managerHome();