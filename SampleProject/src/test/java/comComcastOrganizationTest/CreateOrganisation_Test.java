package comComcastOrganizationTest;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import comComCastCRMGenericBaseTest.BaseClass;
import comComCastCRMGenericWebdriverUtility.UtilityClassObject;
import comComCastCRMObjectRepository.CreatingNewOrganizationPage;
import comComCastCRMObjectRepository.HomePage;
import comComCastCRMObjectRepository.OrganizationInformationPage;
import comComCastCRMObjectRepository.OrganizationPage;

public class CreateOrganisation_Test extends BaseClass{


	@Test(groups = "smokeTest")
	public void createOrganisation_Test() throws Throwable {
		HomePage hp=new HomePage(driver);
		OrganizationPage op=new OrganizationPage(driver);
		CreatingNewOrganizationPage cnop=new CreatingNewOrganizationPage(driver);
		OrganizationInformationPage oip= new OrganizationInformationPage(driver);
		
		String orgName=elib.getDataFromExcelFile("Org", 1, 2)+jlib.getRandomNumber();
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to organization and create organization");
		//Navigate to organization and create organization
		hp.getOrganizationsLink().click();
		op.getCreateOrganizationButton().click();
		cnop.getOrgNameTextField().sendKeys(orgName);
		cnop.getSaveButton().click();

		//Verify the header and organization Name
		UtilityClassObject.getTest().log(Status.INFO, "Verify the header and organization Name");
		String headerInfo=oip.getHeaderInfo().getText();
		if(headerInfo.contains(orgName)) {			
			System.out.println(orgName+" is created==Pass");
			UtilityClassObject.getTest().log(Status.PASS, orgName+" is created");
		}
		else {
			System.out.println(orgName+" is not created==Fail");
			UtilityClassObject.getTest().log(Status.FAIL, orgName+" is not created");
		}
		String actualOrganizationName=oip.getActualOrganizationName().getText();
		if(actualOrganizationName.equals(orgName)) {
			System.out.println(orgName+" is created==Pass");
			UtilityClassObject.getTest().log(Status.PASS, orgName+" is created");
		}
		else {
			System.out.println(orgName+" is not created==Fail");
			UtilityClassObject.getTest().log(Status.FAIL, orgName+" is not created");
		}
	}

	@Test(groups = "regressionTest")

	private void createOrganizationWithIndustryType() throws Throwable {
		HomePage hp=new HomePage(driver);
		OrganizationPage op=new OrganizationPage(driver);
		CreatingNewOrganizationPage cnop=new CreatingNewOrganizationPage(driver);
		OrganizationInformationPage oip= new OrganizationInformationPage(driver);

		hp.getOrganizationsLink().click();

		//Step3:click on "create organization" button
		UtilityClassObject.getTest().log(Status.INFO,"Create organizations");
		op.getCreateOrganizationButton().click();

		//Enter mandatory fields	
		UtilityClassObject.getTest().log(Status.INFO,"Enter mandatory fields by reading data from excel");
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
		UtilityClassObject.getTest().log(Status.INFO,"Verify the industry and type");
		String actIndustry = oip.getActIndustry().getText();
		if(actIndustry.contains(industry)) {
			System.out.println(actIndustry+" information is verified == PASS");
			UtilityClassObject.getTest().log(Status.PASS,actIndustry+" information is verified");
		}else {
			System.out.println(actIndustry+" information is not verified == FAIL");
			UtilityClassObject.getTest().log(Status.FAIL,actIndustry+" information is not verified");
		}
		String actType=oip.getActType().getText();
		if(actType.equalsIgnoreCase(type)) {
			System.out.println(actType+" information is verified == PASS");
			UtilityClassObject.getTest().log(Status.PASS,actType+" information is verified");
		}else {
			System.out.println(actType+" information is not verified == FAIL");
			UtilityClassObject.getTest().log(Status.FAIL,actType+" information is not verified");

		}
	}

	@Test(groups = "regressionTest")
	public void createOrgWithPhoneNum() throws Throwable {
		HomePage hp=new HomePage(driver);
		OrganizationPage op=new OrganizationPage(driver);
		CreatingNewOrganizationPage cnop=new CreatingNewOrganizationPage(driver);
		OrganizationInformationPage oip= new OrganizationInformationPage(driver);

		String orgName=elib.getDataFromExcelFile("Org", 7, 2)+jlib.getRandomNumber();
		String phoneNumber=elib.getDataFromExcelFile("Org", 7, 3);
		
		UtilityClassObject.getTest().log(Status.INFO," Create organization with Phone Number");
		hp.getOrganizationsLink().click();
		op.getCreateOrganizationButton().click();
		cnop.getOrgNameTextField().sendKeys(orgName);
		cnop.getPhoneTextfield().sendKeys(phoneNumber);
		cnop.getSaveButton().click();
		Thread.sleep(2000);

		//Verify the Phone Number
		UtilityClassObject.getTest().log(Status.INFO," Verify the Phone Number");
		String actPhNumber = oip.getActPhoneNum().getText();	
		if(actPhNumber.equalsIgnoreCase(phoneNumber)) {			
			System.out.println(actPhNumber+" information is verified == PASS");	
			UtilityClassObject.getTest().log(Status.PASS,actPhNumber+" information is verified");
		}
		else {	System.out.println(actPhNumber+" information is not verified == FAIL");		
		UtilityClassObject.getTest().log(Status.FAIL,actPhNumber+" information is not verified");
		}

	}

}



