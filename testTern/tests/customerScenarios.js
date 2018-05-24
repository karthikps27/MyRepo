/**
 * 
 */

var homePage = require('../pages/homePage.js');
var customerLoginPage = require('../pages/customerLoginPage.js');
var customerAccountsPage = require('../pages/customerAccountsPage.js');

describe('testFunction for accessing home page',function() {
	
	beforeEach(function() {
		homePage.openPage('#/login');
	})
	
	it('test function for accessing customer login page', function() {
		homePage.goToCustomLogin();
		browser.sleep(4000);
	});
	
	it('test logging in as customer', function() {
		homePage.goToCustomLogin();
		customerLoginPage.loginWithUser("Harry Potter")
	});
	
	it('test adding deposit to customer', function() {
		homePage.goToCustomLogin();
		customerLoginPage.loginWithUser("Harry Potter");
		customerAccountsPage.addDepositToAccount("1005", "1500");
		//browser.sleep(2000);
		expect(customerAccountsPage.getCustomerOperationMessage()).toEqual("Deposit Successful");
		customerAccountsPage.getTransactionDetails("Amount",0);
	});
	
	it('test verifying added deposit to customer', function() {
		var amount = "1500";
		homePage.goToCustomLogin();
		customerLoginPage.loginWithUser("Harry Potter");
		customerAccountsPage.addDepositToAccount("1005", amount);
		browser.sleep(1000);
		customerAccountsPage.getCustomerOperationResultMessageElement().then(function(arg0) {
			console.log(arg0);
			customerAccountsPage.getTransactionDetails("Amount",0).then(function(amountValue) {
				expect(amountValue).toEqual(amount);
			});
		})
	});
	
	it('test withdrawing from account', function() {
		var depositAmount = "1500", withdrawAmount = "500";
		homePage.goToCustomLogin();
		customerLoginPage.loginWithUser("Harry Potter");
		customerAccountsPage.addDepositToAccount("1005", depositAmount);
		browser.sleep(1000);
		customerAccountsPage.getCustomerOperationMessage().then(function(operationResult) {
			//console.log(arg0);
			if(operationResult == "Deposit Successful") {
				customerAccountsPage.withdrawAmountFromAccount("1005", withdrawAmount);
				expect(customerAccountsPage.getCustomerOperationMessage()).toBe("Transaction successful");
			}
		});
		customerAccountsPage.getTransactionDetails("Amount",1).then(function(arg0) {
			browser.sleep(5000);
			expect(arg0).toEqual(withdrawAmount);
		})
	})
});