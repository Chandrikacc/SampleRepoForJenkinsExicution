package comComCastCRMObjectRepository;

import org.hamcrest.Factory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatingNewContactPage {
	WebDriver driver;
	public CreatingNewContactPage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(name = "lastname")
	private WebElement lastnameTextField;
	
	@FindBy(xpath = "//input[@name='account_name']/following-sibling::img")
	private WebElement orgNameplusIcon;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveButton;
	
	@FindBy(id = "jscal_field_support_start_date")
	private WebElement startDateTextField;
	
	@FindBy(id="jscal_field_support_end_date")
	private WebElement endDateTextField;
	
	@FindBy(xpath="//span[@class='dvHeaderText']")
	private WebElement actuallastName;
	
	
	public WebElement getActuallastName() {
		return actuallastName;
	}

	public WebElement getStartDateTextField() {
		return startDateTextField;
	}

	public WebElement getEndDateTextField() {
		return endDateTextField;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getLastnameTextField() {
		return lastnameTextField;
	}

	public WebElement getOrgNameplusIcon() {
		return orgNameplusIcon;
	}

	public WebElement getSaveButton() {
		return saveButton;
	}
	
	

}
