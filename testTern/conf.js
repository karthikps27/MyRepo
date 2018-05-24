/**
 * 
 */

var Jasmine2HtmlReporter = require('protractor-jasmine2-html-reporter');


exports.config = {
	seleniumAddress: 'http://localhost:4444/wd/hub',
	specs: [
		//'./tests/**/*.js'
		'./tests/managerScenarios.js'
	],
	capabilities: {
		'browserName': 'chrome'
	},
	baseUrl: 'http://www.way2automation.com/angularjs-protractor/banking/',
	jasmineNodeOpts: {
	    onComplete: null,
	    isVerbose: false,
	    showColors: true,
	    includeStackTrace: false
	},
	onPrepare: function() {
      jasmine.getEnv().addReporter(
        new Jasmine2HtmlReporter({
          savePath: 'target/screenshots'
        })
      );
   }
}