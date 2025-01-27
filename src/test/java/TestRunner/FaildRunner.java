package TestRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

public class FaildRunner {
	
	@CucumberOptions(features= "@target/faildrerun.txt",
			glue = "StepDefination", 
			dryRun=false,
			monochrome = true, 
			plugin = {"pretty", "html:target/cucumber-reports.html", "junit:target/cucumber.xml",
					"rerun:target/faildrerun.txt", "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"} )
			public class CucumberTest extends AbstractTestNGCucumberTests {
			}

}
