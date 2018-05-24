/**
 * 
 */

var basePage = require('./basePage.js');

var customerLoginPage = function() {
	var userSelectBox = element(by.model("custId"));
	var loginButton = element(by.buttonText("Login"));
	
	this.loginWithUser = function(username) {
		basePage.selectElementByText(userSelectBox, username);
		basePage.waitForElementToBeClickable(loginButton).click();
	};
};

customerLoginPage.prototype = basePage;
module.exports = new customerLoginPage();