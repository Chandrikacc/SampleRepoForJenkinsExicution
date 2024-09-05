package comComCastCRMObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatingNewOrganizationPage {
	WebDriver driver;
	public CreatingNewOrganizationPage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(name = "accountname")
	private WebElement orgNameTextField;
	
	@FindBy(name = "industry")
	private WebElement industryDropdOwn;
	
	@FindBy(name ="accounttype")
	private WebElement typeDropdown;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveButton;
	
	@FindBy(id="phone")
	private WebElement phoneTextfield;

	public WebElement getOrgNameTextField() {
		return orgNameTextField;
	}

	public WebElement getIndustryDropdOwn() {
		return industryDropdOwn;
	}

	public WebElement getTypeDropdown() {
		return typeDropdown;
	}

	public WebElement getSaveButton() {
		return saveButton;
	}

	public WebElement getPhoneTextfield() {
		return phoneTextfield;
	}
	
	
}

