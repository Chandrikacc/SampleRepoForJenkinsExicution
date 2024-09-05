package comComcastContactTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import comComCastCRMGenericFileUtility.ExcelUtility;
import comComCastCRMGenericFileUtility.FileUtility;
import comComCastCRMGenericWebdriverUtility.JavaUtility;
import comComCastCRMGenericWebdriverUtility.WebDriverUtility;
import comComCastCRMObjectRepository.ContactPage;
import comComCastCRMObjectRepository.CreatingNewContactPage;
import comComCastCRMObjectRepository.CreatingNewOrganizationPage;
import comComCastCRMObjectRepository.HomePage;
import comComCastCRMObjectRepository.LoginPage;
import comComCastCRMObjectRepository.OrganizationInformationPage;
import comComCastCRMObjectRepository.OrganizationNameListPage;
import comComCastCRMObjectRepository.OrganizationPage;

public class CreateContactWithOrganization_Test {

	public static void main(String[] args) throws Throwable {
		//Create object for utility
		FileUtility flib=new FileUtility();
		ExcelUtility elib= new ExcelUtility();
		JavaUtility jlib=new JavaUtility();
		WebDriverUtility wlib=new WebDriverUtility();

		String Browser=flib.getDataFromPropertyFile("browser");	
		String URL=flib.getDataFromPropertyFile("url");
		String Username=flib.getDataFromPropertyFile("username");
		String Password=flib.getDataFromPropertyFile("password");

		String orgName=elib.getDataFromExcelFile("Contact", 5, 2)+jlib.getRandomNumber();
		String lastName=elib.getDataFromExcelFile("Contact", 5, 3);


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
		OrganizationInformationPage oip=new OrganizationInformationPage(driver);
		ContactPage cp=new ContactPage(driver);
		CreatingNewContactPage cncp = new CreatingNewContactPage(driver);
		OrganizationNameListPage onp=new OrganizationNameListPage(driver);

		//Step1:Login to Application
		driver.get(URL);
		wlib.waitForPageToLoad(driver);
		lp.loginToApplication(URL, Username, Password);

		//Step2:Navigate to organization,create New Organization with mandatory details
		hp.getOrganizationsLink().click();
		op.getCreateOrganizationButton().click();
		cnop.getOrgNameTextField().sendKeys(orgName);
		cnop.getSaveButton().click();

		//Step3:Verify the header info
		String headerInfo=oip.getActualOrganizationName().getText();
		if(headerInfo.contains(orgName)) {
			System.out.println(orgName+" is created==Pass");
		}
		else {
			System.out.println(orgName+" is not created==Fail");
		}

		//Navigate to contact module
		hp.getContactLink().click();
		cp.getCreateContactIcon().click();
		cncp.getLastnameTextField().sendKeys(lastName);
		cncp.getOrgNameplusIcon().click();

		String parentID= driver.getWindowHandle();
		wlib.switchToTabOnUrl(driver, "module=Accounts&action");

		onp.getSearchTextField().sendKeys(orgName);
		onp.getSearchNowButton().click();
		Thread.sleep(3000);

		//dynamic element hence used driver.findElemnt()
		driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click(); 
		wlib.switchToParentTab(driver,parentID);
		cncp.getSaveButton().click();

		//verify the contact in header with expected details
		String headInfo =driver.findElement(By.id("mouseArea_Organization Name")).getText();
		if(headInfo.contains(orgName)) {
			System.out.println(orgName+"  information is created==Pass");
		}
		else {
			System.out.println(orgName+" information is not created==Fail");
		}

		Thread.sleep(2000);

		//Step5:Logout	
		hp.logoutFromApplication();
		Thread.sleep(2000);
		driver.quit();




	}

}



