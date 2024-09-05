package comComcastOrganizationTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import comComCastCRMGenericFileUtility.ExcelUtility;
import comComCastCRMGenericFileUtility.FileUtility;
import comComCastCRMGenericWebdriverUtility.JavaUtility;
import comComCastCRMGenericWebdriverUtility.SelectUtility;
import comComCastCRMGenericWebdriverUtility.WebDriverUtility;
import comComCastCRMObjectRepository.CreatingNewOrganizationPage;
import comComCastCRMObjectRepository.HomePage;
import comComCastCRMObjectRepository.LoginPage;
import comComCastCRMObjectRepository.OrganizationInformationPage;
import comComCastCRMObjectRepository.OrganizationPage;

public class CreateOrganizationWithIndustryType {


		public static void main(String[] args) throws Throwable {

			//Create object for Utility
			FileUtility flib=new FileUtility();
			ExcelUtility elib= new ExcelUtility();
			JavaUtility jlib=new JavaUtility();
			WebDriverUtility wlib=new WebDriverUtility();
			SelectUtility slib=new SelectUtility();
			
			String Browser=flib.getDataFromPropertyFile("browser");	
			String URL=flib.getDataFromPropertyFile("url");
			String Username=flib.getDataFromPropertyFile("username");
			String Password=flib.getDataFromPropertyFile("password");
			

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
			
			
			//Step1:Login to Application
			driver.get(URL);
			wlib.waitForPageToLoad(driver);
			lp.loginToApplication(URL, Username, Password);

			
			//Step2:Navigate to organization module
			hp.getOrganizationsLink().click();
			
			//Step3:click on "create organization" button
			op.getCreateOrganizationButton().click();

			//Enter mandatory fields
			String orgName=elib.getDataFromExcelFile("Org", 4, 2)+jlib.getRandomNumber();
			String industry=elib.getDataFromExcelFile("Org", 4, 3);
			String type=elib.getDataFromExcelFile("Org", 4, 4);
			cnop.getOrgNameTextField().sendKeys(orgName);
			WebElement industryDropdown=cnop.getIndustryDropdOwn();
			slib.selectByValue(industryDropdown, industry);
			WebElement typeDropDown=cnop.getTypeDropdown();
			slib.selectByValue(typeDropDown, type);
			cnop.getSaveButton().click();
			

			//Verify the industry and type
			String actIndustry = oip.getActIndustry().getText();
			if(actIndustry.contains(industry)) {
				System.out.println(actIndustry+" information is verified == PASS");
			}else {
				System.out.println(actIndustry+" information is not verified == FAIL");
			}
			String actType=oip.getActType().getText();
			if(actType.equalsIgnoreCase(type)) {
				System.out.println(actType+" information is verified == PASS");
			}else {
				System.out.println(actType+" information is not verified == FAIL");
			}
			//Step5:Logout
			hp.logoutFromApplication();
			Thread.sleep(2000);
			
			//close the browser
			driver.quit();

	
	}

}
