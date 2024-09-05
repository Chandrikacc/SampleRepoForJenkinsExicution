package comComcastContactTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import comComCastCRMGenericFileUtility.ExcelUtility;
import comComCastCRMGenericFileUtility.FileUtility;
import comComCastCRMGenericWebdriverUtility.JavaUtility;
import comComCastCRMGenericWebdriverUtility.WebDriverUtility;
import comComCastCRMObjectRepository.ContactInformationPage;
import comComCastCRMObjectRepository.ContactPage;
import comComCastCRMObjectRepository.CreatingNewContactPage;
import comComCastCRMObjectRepository.HomePage;
import comComCastCRMObjectRepository.LoginPage;

public class CreateContactWithSupportDate_Test {

	public static void main(String[] args) throws Throwable {
		//Create object for Utility
		FileUtility flib=new FileUtility();
		ExcelUtility elib= new ExcelUtility();
		JavaUtility jlib=new JavaUtility();
		WebDriverUtility wlib=new WebDriverUtility();
		
		String Browser=flib.getDataFromPropertyFile("browser");	
		String URL=flib.getDataFromPropertyFile("url");
		String Username=flib.getDataFromPropertyFile("username");
		String Password=flib.getDataFromPropertyFile("password");
	
		String lastName=elib.getDataFromExcelFile("Contact", 1, 2)+jlib.getRandomNumber();


		WebDriver driver=null;
		if(Browser.equals("chrome")) {
			driver=new ChromeDriver();	
		}
		else if(Browser.equals("edge")) {
			driver=new EdgeDriver();		
		}
		else if(Browser.equals("firefox")) {
			driver=new FirefoxDriver();			
		}
		
		LoginPage lp=new LoginPage(driver);
		HomePage hp=new HomePage(driver);
		ContactPage cp=new ContactPage(driver);
		CreatingNewContactPage cncp=new CreatingNewContactPage(driver);
		ContactInformationPage cip=new ContactInformationPage(driver);

		//Login to Application
		driver.get(URL);
		wlib.waitForPageToLoad(driver);
		lp.loginToApplication(URL, Username, Password);

		//navigate to contacts and enter mandatory fields
		hp.getContactLink().click();
		cp.getCreateContactIcon().click();
		cncp.getLastnameTextField().sendKeys(lastName);
		
		String startDate=jlib.getSystemDateYYYYMMDD();
		String endDate=jlib.getRequiredDateYYYYMMDD(30);
		
		cncp.getStartDateTextField().clear();
		cncp.getStartDateTextField().sendKeys(startDate);
		cncp.getEndDateTextField().clear();
		cncp.getEndDateTextField().sendKeys(endDate);
		cncp.getSaveButton().click();

		//verify the contact in header with expected details
		String actualStartDate= cip.getActualStartDate().getText();
		if(actualStartDate.contains(startDate)) {
			System.out.println(startDate+"  information is created==Pass");
		}
		else {
			System.out.println(startDate+" information is not created==Fail");
		}

		String actualEndDate=cip.getActualEndDate().getText();
		if(actualEndDate.contains(endDate)) {
			System.out.println(endDate+"  information is created==Pass");
		}
		else {
			System.out.println(endDate+" information is not created==Fail");
		}

		//Step5:Logout	
				hp.logoutFromApplication();
				Thread.sleep(2000);
				driver.quit();
	}

}
