package comComCastCRMGenericBaseTest;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import comComCastCRMGenericFileUtility.ExcelUtility;
import comComCastCRMGenericFileUtility.FileUtility;
import comComCastCRMGenericWebdriverUtility.JavaUtility;
import comComCastCRMGenericWebdriverUtility.SelectUtility;
import comComCastCRMGenericWebdriverUtility.UtilityClassObject;
import comComCastCRMGenericWebdriverUtility.WebDriverUtility;

import comComCastCRMObjectRepository.HomePage;
import comComCastCRMObjectRepository.LoginPage;
@Listeners(comComCastCRMGenericListenerUtility.ListenerImpClass.class)
public class BaseClass {
	//Create object for Utility
	public FileUtility flib=new FileUtility();
	public	ExcelUtility elib= new ExcelUtility();
	public JavaUtility jlib=new JavaUtility();
	public WebDriverUtility wlib=new WebDriverUtility();
	public SelectUtility slib=new SelectUtility();
	public WebDriver driver=null;
	public static WebDriver sdriver=null;
	//public ExtentTest test;


	@BeforeSuite(groups = {"smokeTest","regressionTest"})
	public void configBS() {
		//connecting to DB
	}

	//   @Parameters("Browser")
	@BeforeClass(groups = {"smokeTest","regressionTest"})
	//launching the browser
	//	public void configBC(String browser) throws Throwable {	
	public void configBC() throws Throwable {

		//String Browser=flib.getDataFromPropertyFile("browser");
		String Browser=System.getProperty("browser",flib.getDataFromPropertyFile("browser"));
		//	 String Browser=browser;
		if(Browser.equals("chrome")) {
			driver=new ChromeDriver();	
		}
		else if(Browser.equals("edge")) {
			driver=new EdgeDriver();		
		}
		else if(Browser.equals("firefox")) {
			driver=new FirefoxDriver();	
		}
		sdriver=driver;
		UtilityClassObject.setDriver(driver);
	}
	@BeforeMethod(groups = {"smokeTest","regressionTest"})
	public void configBM() throws Throwable {
		//Step1:Login to Application
		LoginPage lp=new LoginPage(driver);
//		String URL=flib.getDataFromPropertyFile("url");
//		String Username=flib.getDataFromPropertyFile("username");
//		String Password=flib.getDataFromPropertyFile("password");
		String URL=System.getProperty("url",flib.getDataFromPropertyFile("url"));
		String Username=System.getProperty("username",flib.getDataFromPropertyFile("username"));
		String Password=System.getProperty("password",flib.getDataFromPropertyFile("password"));
		lp.loginToApplication(URL, Username, Password);
	}
	@AfterMethod(groups = {"smokeTest","regressionTest"})
	public void configAM() {
		//Step5:Logout	
		HomePage hp=new HomePage(driver);
		hp.logoutFromApplication();
	}

	@AfterClass(groups = {"smokeTest","regressionTest"})
	public void configAc() {
		//close the browser
		driver.quit();
	}

	@AfterSuite(groups = {"smokeTest","regressionTest"})
	public void configAS(){
		//report backup
	}
}
