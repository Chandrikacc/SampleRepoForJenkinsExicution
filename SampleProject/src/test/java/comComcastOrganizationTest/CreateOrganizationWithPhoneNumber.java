package comComcastOrganizationTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import comComCastCRMGenericFileUtility.ExcelUtility;
import comComCastCRMGenericFileUtility.FileUtility;
import comComCastCRMGenericWebdriverUtility.JavaUtility;
import comComCastCRMGenericWebdriverUtility.WebDriverUtility;
import comComCastCRMObjectRepository.CreatingNewOrganizationPage;
import comComCastCRMObjectRepository.HomePage;
import comComCastCRMObjectRepository.LoginPage;
import comComCastCRMObjectRepository.OrganizationInformationPage;
import comComCastCRMObjectRepository.OrganizationPage;

public class CreateOrganizationWithPhoneNumber {

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

		String orgName=elib.getDataFromExcelFile("Org", 7, 2)+jlib.getRandomNumber();
		String phoneNumber=elib.getDataFromExcelFile("Org", 7, 3);

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
		OrganizationPage op=new OrganizationPage(driver);
		CreatingNewOrganizationPage cnop=new CreatingNewOrganizationPage(driver);
		OrganizationInformationPage oip= new OrganizationInformationPage(driver);

		driver.get(URL);
		wlib.waitForPageToLoad(driver);
		lp.loginToApplication(URL, Username, Password);


		//Step2:Navigate to organization module
		hp.getOrganizationsLink().click();

		//Step3:click on "create organization" button
		op.getCreateOrganizationButton().click();


		//Enter mandatory fields
		cnop.getOrgNameTextField().sendKeys(orgName);
		cnop.getPhoneTextfield().sendKeys(phoneNumber);
		cnop.getSaveButton().click();
		Thread.sleep(2000);

		//Verify the Phone Number
		String actPhNumber = oip.getActPhoneNum().getText();	
		if(actPhNumber.equalsIgnoreCase(phoneNumber)) {			
			System.out.println(actPhNumber+" information is verified == PASS");		
		}
		else {	System.out.println(actPhNumber+" information is not verified == FAIL");		
		}

		//Step5:Logout
		hp.logoutFromApplication();
		Thread.sleep(1000);

		//close the browser
		driver.quit();
	}

}
