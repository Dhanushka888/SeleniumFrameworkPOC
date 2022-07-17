package Academy;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.Base;
import resources.ExtentReportNG;

public class Listeners extends Base implements ITestListener {
	ExtentReports extent = ExtentReportNG.getReportObject();// import the extent report object
	ExtentTest test;
	//Below implementation is for thread safety
	ThreadLocal<ExtentTest> extentTest=new ThreadLocal<ExtentTest>();

	@Override
	public void onTestStart(ITestResult result) {
		// Create test for ExtentReport
		String testMethodName = result.getMethod().getMethodName();
		test = extent.createTest(testMethodName);
		extentTest.set(test);
		System.out.println(testMethodName);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		//Create sucsus extent report entry
		extentTest.get().log(Status.PASS, "Test Passed");
		//test.log(Status.FAIL, "Test Failed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		extentTest.get().fail(result.getThrowable());//on test fail throwable goes to extent report
		WebDriver driver = null;
		String testMethodName = result.getMethod().getMethodName();
		
		try {
			// Below statement will catch the current driver instance regarding the current
			// method being executed
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver")
					.get(result.getInstance());
		} catch (Exception e1) {

			e1.printStackTrace();
		}

		// Screenshot in test failure
		try {
			getScreenShotPath(testMethodName, driver);
		} catch (IOException e) {

			e.printStackTrace();
		}
		try {
			//This will attach the screenshot to testNG report
			String attachmentPath= getScreenShotPath(testMethodName,driver);
			System.out.println(attachmentPath);
			//extentTest.get().addScreenCaptureFromPath(attachmentPath, testMethodName);
			test.addScreenCaptureFromPath(attachmentPath, testMethodName);
			
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		// flush the extent report
		extent.flush();
	}

}
