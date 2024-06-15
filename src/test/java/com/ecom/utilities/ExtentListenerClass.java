package com.ecom.utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentListenerClass implements ITestListener{
	
	ExtentReports reports;
	ExtentSparkReporter htmlReporter;
	ExtentTest test;
	
	public void configureReport() {
		String timestamp = new SimpleDateFormat("yyyy.MM.dd hh.mm.ss").format(new Date());
		String reportName = "EComStoreTestReport_" + timestamp + ".html";
		htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"//reports//"+ reportName);
		reports = new ExtentReports();
		reports.attachReporter(htmlReporter);
		
		// add system information/environment
		ReadConfig readConfig = new ReadConfig();
		reports.setSystemInfo("Machine", "testPC");
		reports.setSystemInfo("OS", System.getProperty("os.name"));
		reports.setSystemInfo("OS version", System.getProperty("os.version"));
		reports.setSystemInfo("Java", System.getProperty("java.version"));
		reports.setSystemInfo("browser", readConfig.getBrowser());
		reports.setSystemInfo("Tester", "Tester1");
		
		
		
		//configure htmlReport
		htmlReporter.config().setDocumentTitle("ExtentReport");
		htmlReporter.config().setReportName("TestReport");
		htmlReporter.config().setTheme(Theme.DARK);
	}
	
	@Override
	public void onStart(ITestContext context) {
		
		configureReport();
		System.out.println("On Start method invoked....");
	}

	@Override
	public void onFinish(ITestContext context) {
		
		System.out.println("On Finished method invoked....");
		reports.flush();
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("Making entry to report for Test method:" + result.getName());
		test = reports.createTest(result.getName());
		test.log(Status.FAIL, MarkupHelper.createLabel("Test Case failed: "+result.getName(), ExtentColor.RED));
		
		
		//Add screenshot
		String screenshotpath = System.getProperty("user.dir") + "\\screenshots\\" + result.getName() + ".png";
		File screenshotfile = new File(screenshotpath);
		
		if (screenshotfile.exists()){
			//test.fail("Captured screenshot is below" + test.addScreenCaptureFromPath(screenshotpath));
			test.log(Status.FAIL, MediaEntityBuilder.createScreenCaptureFromPath(screenshotpath, "Error").build());
		};
		
	}
	
	
	@Override
	public void onTestSkipped(ITestResult result) {
		
		System.out.println("Making entry to report for Test method:" + result.getName());
		test = reports.createTest(result.getName());
		test.log(Status.SKIP, MarkupHelper.createLabel("Test case Skipped: "+result.getName(), ExtentColor.YELLOW));
	}



	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("On Test Start of "+result.getName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("Making entry to report for Test method:"+result.getName());
		test = reports.createTest(result.getName());
		test.log(Status.PASS, MarkupHelper.createLabel("Test case Passed: "+result.getName(), ExtentColor.GREEN));
	}


	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {

	}

	

}
