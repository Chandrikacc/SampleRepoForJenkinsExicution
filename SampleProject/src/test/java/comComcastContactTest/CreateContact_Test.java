package comComcastContactTest;


import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import comComCastCRMGenericBaseTest.BaseClass;
import comComCastCRMGenericWebdriverUtility.UtilityClassObject;
import comComCastCRMObjectRepository.ContactInformationPage;
import comComCastCRMObjectRepository.ContactPage;
import comComCastCRMObjectRepository.CreatingNewContactPage;
import comComCastCRMObjectRepository.CreatingNewOrganizationPage;
import comComCastCRMObjectRepository.HomePage;
import comComCastCRMObjectRepository.OrganizationInformationPage;
import comComCastCRMObjectRepository.OrganizationNameListPage;
import comComCastCRMObjectRepository.OrganizationPage;
/**
 * This class is to create Contact
 * @author whosc
 *
 */
public class CreateContact_Test extends BaseClass {

	@Test(groups = "smokeTest")
	public void createContact_Test() throws Throwable {
		HomePage hp=new HomePage(driver);
		ContactPage cp=new ContactPage(driver);
		CreatingNewContactPage cncp= new CreatingNewContactPage(driver);
		UtilityClassObject.getTest().log(Status.INFO, "Reading data from excel");

		String lastName=elib.getDataFromExcelFile("Contact", 1, 2)+jlib.getRandomNumber();
		UtilityClassObject.getTest().log(Status.INFO, "/navigate to contacts and enter mandatory fields");

		///navigate to contacts and enter mandatory fields
		hp.getContactLink().click();
		cp.getCreateContactIcon().click();
		cncp.getLastnameTextField().sendKeys(lastName);
		cncp.getSaveButton().click();
		UtilityClassObject.getTest().log(Status.INFO, "verify the contact in header with expected details");
		//verify the contact in header with expected details
		String ActuallastName=cncp.getActuallastName().getText();
		boolean status=ActuallastName.contains(lastName);
		Assert.assertTrue(status);
		System.out.println(lastName+"  information is created==Pass");
		UtilityClassObject.getTest().log(Status.PASS, lastName+"  information is created");
	} 
	@Test(groups = "regressionTest")
	public void createContactWithOrg() throws Throwable {

		String orgName=elib.getDataFromExcelFile("Contact", 5, 2)+jlib.getRandomNumber();
		String lastName=elib.getDataFromExcelFile("Contact", 5, 3);

		HomePage hp=new HomePage(driver);
		OrganizationPage op=new OrganizationPage(driver);
		CreatingNewOrganizationPage cnop=new CreatingNewOrganizationPage(driver);
		OrganizationInformationPage oip=new OrganizationInformationPage(driver);
		ContactPage cp=new ContactPage(driver);
		CreatingNewContactPage cncp = new CreatingNewContactPage(driver);
		OrganizationNameListPage onp=new OrganizationNameListPage(driver);	
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to organization,create New Organization with mandatory details");
		//Navigate to organization,create New Organization with mandatory details
		hp.getOrganizationsLink().click();
		op.getCreateOrganizationButton().click();
		cnop.getOrgNameTextField().sendKeys(orgName);
		cnop.getSaveButton().click();
		UtilityClassObject.getTest().log(Status.INFO,"Verify the header info");
		//Step3:Verify the header info
		String headerInfo=oip.getActualOrganizationName().getText();
		if(headerInfo.contains(orgName)) {
			System.out.println(orgName+" is created==Pass");
			UtilityClassObject.getTest().log(Status.PASS, "");
		}
		else {
			System.out.println(orgName+" is not created==Fail");
		}
		UtilityClassObject.getTest().log(Status.INFO,"Navigate to contact module");
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

		UtilityClassObject.getTest().log(Status.INFO,"verify the contact in header with expected details");

		//verify the contact in header with expected details
		String headInfo =driver.findElement(By.id("mouseArea_Organization Name")).getText();
		if(headInfo.contains(orgName)) {
			System.out.println(orgName+"  information is created==Pass");
			UtilityClassObject.getTest().log(Status.PASS,orgName+"  information is created");

		}
		else {
			System.out.println(orgName+" information is not created==Fail");
			UtilityClassObject.getTest().log(Status.FAIL,orgName+" information is not created");
		}

	}
	@Test(groups = "regressionTest")
	public void createContactWithSupportDate_Test() throws Throwable {
		String lastName=elib.getDataFromExcelFile("Contact", 1, 2)+jlib.getRandomNumber();

		HomePage hp=new HomePage(driver);
		ContactPage cp=new ContactPage(driver);
		CreatingNewContactPage cncp=new CreatingNewContactPage(driver);
		ContactInformationPage cip=new ContactInformationPage(driver);

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
		UtilityClassObject.getTest().log(Status.INFO, "verify the contact in header with expected details");
		String actualStartDate= cip.getActualStartDate().getText();
		if(actualStartDate.contains(startDate)) {
			System.out.println(startDate+"  information is created==Pass");
			UtilityClassObject.getTest().log(Status.PASS,startDate+"  information is created");
		}
		else {
			System.out.println(startDate+" information is not created==Fail");
			UtilityClassObject.getTest().log(Status.FAIL,startDate+"  information is not created");
		}

		String actualEndDate=cip.getActualEndDate().getText();
		if(actualEndDate.contains(endDate)) {
			System.out.println(endDate+"  information is created==Pass");
			UtilityClassObject.getTest().log(Status.PASS,endDate+"  information is created");
		}
		else {
			System.out.println(endDate+" information is not created==Fail");
			UtilityClassObject.getTest().log(Status.FAIL,endDate+"  information is not created");
		}
	}

}
