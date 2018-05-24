/**
 * 
 */

var homePage = require('../pages/homePage.js');
var managerHome = require('../pages/managerHome.js');

describe('test customer scenarios', function() {
	
	beforeEach(function() {
		homePage.openPage('#/login');
	});
	
	/*it('Add new customer', function() {
		homePage.goToManagerLogin();
		managerHome.addCustomers("Steve", "Roger", "71295");
		var resultAlert = browser.switchTo().alert();
		resultAlert.accept();
	});*/
	
	it('Add an account to new user', function() {
		homePage.goToManagerLogin();
		managerHome.addCustomers("Tony","Stark","13245");
		var resultAlert = browser.switchTo().alert();
		resultAlert.accept();
		managerHome.addAccountsToCustomer("Tony Stark", "Dollar").then(function(accountNo) {
			managerHome.checkAddedAccount("Tony", accountNo);
		});
	});
	
})