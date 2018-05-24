/**
 * 
 */

var basePage = require('./basePage.js');
var customerLoginPage = require('./customerLoginPage.js');

var customerAccountsPage = function() {
	var accountSelectbox = element(by.id("accountSelect"));
	var depositMenuButton = element(by.buttonText("Deposit"));
	var withdrawlMenuButton = element(by.buttonText("Withdrawl"));
	var amountField = element(by.model("amount"));
	var actionButton = element(by.css("button.btn.btn-default"));
	var depositSuccessMessage = element(by.css('[ng-show="message"]'));
	var transactionTableLocator = by.css('.table.table-bordered.table-striped');
	var transactionButton = element(by.buttonText("Transactions"));
	
	this.addDepositToAccount = function(accountNo, amount) {
		basePage.selectElementByText(accountSelectbox, accountNo);
		basePage.waitForElementToBeClickable(depositMenuButton).click();
		basePage.waitForElementToBeClickable(amountField).sendKeys(amount);
		basePage.waitForElementToBeClickable(actionButton).click();
	};
	
	this.withdrawAmountFromAccount = function(accountNo, amount) {
		basePage.selectElementByText(accountSelectbox, accountNo);
		basePage.waitForElementToBeClickable(withdrawlMenuButton).click();
		basePage.waitForElementToBeClickable(amountField).sendKeys(amount);
		basePage.waitForElementToBeClickable(actionButton).click();
	};
	
	this.getCustomerOperationMessage = function() {
		return(basePage.isElementPresent(depositSuccessMessage).getText());
	};
	
	// write a function to wait for success message upon addition of deposit occurs,
	this.getCustomerOperationResultMessageElement = function() {
		var deferred = protractor.promise.defer();
		basePage.isElementPresent(depositSuccessMessage).getText().then(function(value) {
			if(value.indexOf("Deposit") != -1)
				deferred.fulfill("Success");
		});
		return deferred.promise;
	}
	
	this.getTransactionDetails = function(columnName, rowNo) {
		//basePage.getColumnPosition(transactionTable, columnName);
		var deferred = protractor.promise.defer();
		basePage.waitForElementToBeClickable(transactionButton).click().then(function() {
			basePage.getTableColumnDetails(basePage.isElementPresent(element(transactionTableLocator)), columnName, rowNo).
			then(function(promiseValue) {
				deferred.fulfill(promiseValue);	
			});
		});
		return deferred.promise;
	};
};

module.exports = new customerAccountsPage();