package comComcastOrganizationTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import comComCastCRMGenericFileUtility.ExcelUtility;
import comComCastCRMGenericFileUtility.FileUtility;
import comComCastCRMGenericWebdriverUtility.JavaUtility;
import comComCastCRMGenericWebdriverUtility.SelectUtility;

import comComCastCRMObjectRepository.CreatingNewOrganizationPage;
import comComCastCRMObjectRepository.HomePage;
import comComCastCRMObjectRepository.LoginPage;
import comComCastCRMObjectRepository.OrganizationInformationPage;
import comComCastCRMObjectRepository.OrganizationPage;
/**
 * This class contains information about deleting an organisation
 * @author whosc
 *
 */
public class DeleteTheOrganization_Test {

	public static void main(String[] args) throws Throwable {
	
		//Create object for Utility
		FileUtility flib=new FileUtility();
		ExcelUtility elib= new ExcelUtility();
		JavaUtility jlib=new JavaUtility();
		SelectUtility slib=new SelectUtility();

		String Browser=flib.getDataFromPropertyFile("browser");	
		String URL=flib.getDataFromPropertyFile("url");
		String Username=flib.getDataFromPropertyFile("username");
		String Password=flib.getDataFromPropertyFile("password");

		String orgName=elib.getDataFromExcelFile("org", 9, 2)+jlib.getRandomNumber();

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

		//Step1:Login to Application and create new Organization
		lp.loginToApplication(URL, Username, Password);
		
		hp.getOrganizationsLink().click();
		op.getCreateOrganizationButton().click();
		cnop.getOrgNameTextField().sendKeys(orgName);
		cnop.getSaveButton().click();
//		wlib.waitForElementPresent(driver, hp.getOrganizationsLink());
		Thread.sleep(3000);
		hp.getOrganizationsLink().click();
	

	
		op.getSearchForTextField().sendKeys(orgName);
		WebElement orgDropDown = op.getOrganizationDropdown();
		slib.selectByIndex(orgDropDown, 1);
		oip.getSearchButton().click();
		
		//dynamic element hence used driver.findElemnt()
		driver.findElement(By.xpath("//a[text()='"+orgName+"']/../../td[8]/a[text()='del']")).click();
		driver.switchTo().alert().accept();
		
		//Step5:Logout	
		hp.logoutFromApplication();
		Thread.sleep(2000);
		driver.quit();


	}

}

