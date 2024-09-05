package comComCastCRMGenericListenerUtility;


import java.time.LocalDateTime;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import comComCastCRMGenericWebdriverUtility.UtilityClassObject;

public class ListenerImpClass implements ITestListener,ISuiteListener {
	public ExtentSparkReporter spark;
	public ExtentReports report;
	public static ExtentTest test;

	public void onStart(ISuite suite) {	
		//Spark report configuration
		String time=LocalDateTime.now().toString().replace(':','-');
		spark=new ExtentSparkReporter("./AdvancedReport/report"+time+".html");
		spark.config().setDocumentTitle("CRM Test Suite Results");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);

		//Attach report, Add environmental information and create Test
		report=new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Windows-11");
		report.setSystemInfo("Browser", "Chrome-100");
	}

	public void onFinish(ISuite suite) {
		//report backup"
		report.flush();
	}

	public void onTestStart(ITestResult result) {
		System.out.println("======<"+result.getMethod().getMethodName()+">===START=====");
		test = report.createTest(result.getMethod().getMethodName());
		UtilityClassObject.setTest(test); 
		test.log(Status.INFO, result.getMethod().getMethodName()+"==>STARTED<====");

	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("======<"+result.getMethod().getMethodName()+">===END=====");
		test.log(Status.PASS, result.getMethod().getMethodName()+"==>COMPLETED<====");
	}

	public void onTestFailure(ITestResult result) {
		String testName=result.getMethod().getMethodName();
		TakesScreenshot ts=(TakesScreenshot)UtilityClassObject.getDriver();
		String filePath = ts.getScreenshotAs(OutputType.BASE64);
		String time=LocalDateTime.now().toString().replace(':','-');
		test.addScreenCaptureFromBase64String(filePath, testName+time);
		test.log(Status.FAIL, result.getMethod().getMethodName()+"==>FAILED<====");
	}

	public void onTestSkipped(ITestResult result) {
		test.log(Status.SKIP, result.getMethod().getMethodName()+"==>SKIPPED<==");


	}
}
