/**
 * 
 */

var basePage = function() {
	
	var EC = protractor.ExpectedConditions;
	var timeOutInMilli = 10000;
	
	this.openPage = function(destination) {
		browser.get(destination,timeOutInMilli);
	};
	
	this.waitForElementToBeClickable = function(webElement) {
		browser.wait(EC.elementToBeClickable(webElement), timeOutInMilli);
		return webElement;
	};
	
	this.isElementPresent = function(webElement) {
		browser.wait(EC.visibilityOf(webElement), timeOutInMilli);
		return webElement;
	};
	
	this.selectElementByText = function(webElement, optionText) {
		//this.waitForElementToBeClickable(webElement,timeOutInMilli).$('[value="2"]').click();
		var desiredOption;
		//console.log("OptionText:" + optionText)
		webElement.all(by.tagName("option")).then(function(options) {
			options.forEach(function(option, i, array) {
				option.getText().then(function(optionTextActual) {
					//console.log(optionTextActual);
					if(optionTextActual == optionText) {
						//console.log("Match Found");
						desiredOption = option;
					}
				});
			});
		}).then(function() {
			desiredOption.click();
		});
		
	};
	
	this.getColumnPosition = function(webElement, columnName) {
		var deferred = protractor.promise.defer();
		webElement.all(by.css('thead tr td')).then(function(allHeaders) {
			allHeaders.forEach(function(columnHead, i, array) {
				columnHead.getText().then(function(columnText) {
					if(columnText == columnName) {
						deferred.fulfill(i+1);
					}
				});
			});
		});
		return deferred.promise;
	};
	
	this.getTableColumnDetails = function(webElement, columnName, rowNo) {
		var result = [];
		var deferred = protractor.promise.defer();
		var tableHeader;
		this.isElementPresent(webElement);
		this.getColumnPosition(webElement, columnName).then(function(columnIndex) {
			//webElement.element(by.css('tr#anchor0 td:nth-child(1)')).then(function(rows) {
			webElement.element(by.css('tr#anchor'+rowNo+' td:nth-child('+columnIndex+')')).getText().then(function(value) {
				deferred.fulfill(value);
			});
		});
		return deferred.promise;
	};
	
	this.getRowPosition = function(webElement, firstColumnData) {
		var deferred = protractor.promise.defer();
		webElement.all(by.css('tbody td:nth-child(1)')).then(function(allData) {
			allData.forEach(function(eachData, i, array) {
				eachData.getText().then(function(arg0) {
					//console.log(arg0);
					if(arg0 == firstColumnData) {
						deferred.fulfill(i+1);
					}
				});
			});
		});
		return deferred.promise;
	};
	
	this.getTableRowDetails = function(webElement, firstColData, columnName) {
		var result = [];
		var deferred = protractor.promise.defer();
		var colIndex, rowIndex;
		this.isElementPresent(webElement);
		this.getRowPosition(webElement, firstColData).then(function(rowPosition) {
			rowIndex = rowPosition;
		});
		
		this.getColumnPosition(webElement, columnName).then(function(colPosition) {
			colIndex = colPosition;
			webElement.element(by.css('tr:nth-child('+rowIndex+') td:nth-child('+colIndex+')')).getText().then(function(value) {
				//console.log(value);
				deferred.fulfill(value);
			})
		});
		
		return deferred.promise;
	};
	
};

module.exports = new basePage();