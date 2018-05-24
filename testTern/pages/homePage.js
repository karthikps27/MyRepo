/**
 * 
 */

var basePage = require('./basePage.js');

var homePage = function() {
	var customerLoginButton = element(by.css("div.borderM.box.padT20 div:nth-child(1) button"));
	var managerLoginButton = element(by.buttonText("Bank Manager Login"));
	
	this.goToCustomLogin = function() {
		basePage.waitForElementToBeClickable(customerLoginButton).click();
	};
	
	this.goToManagerLogin = function() {
		basePage.waitForElementToBeClickable(managerLoginButton).click();
	};
};

homePage.prototype = basePage;
module.exports = new homePage();