package comComCastCRMObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationPage {
	WebDriver driver;
	public OrganizationPage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath = "//img[@title='Create Organization...']")
	private WebElement createOrganizationButton;
	public WebDriver getDriver() {
		return driver;
	}
	
	@FindBy(name ="search_text")
	private WebElement searchForTextField;
	
	@FindBy(id ="bas_searchfield")
	private WebElement organizationDropdown;

	public WebElement getSearchForTextField() {
		return searchForTextField;
	}

	public WebElement getOrganizationDropdown() {
		return organizationDropdown;
	}

	public WebElement getCreateOrganizationButton() {
		return createOrganizationButton;
	}

}
