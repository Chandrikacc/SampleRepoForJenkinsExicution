package comComCastCRMObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInformationPage {
	WebDriver driver;
	public OrganizationInformationPage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement headerInfo;
	
	@FindBy(id ="dtlview_Organization Name")
	private WebElement actualOrganizationName;
	
	@FindBy(id = "dtlview_Industry")
	private WebElement actIndustry;
	
	@FindBy(id ="dtlview_Type")
	private WebElement actType;
	
	@FindBy(id ="dtlview_Phone")
	private WebElement actPhoneNum;
	
	@FindBy(partialLinkText = "Organizations")
	private WebElement organizationLink;
	
	@FindBy(xpath ="//input[@name='submit']")
	private WebElement searchButton;

	public WebElement getSearchButton() {
		return searchButton;
	}

	public WebElement getOrganizationLink() {
		return organizationLink;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getActPhoneNum() {
		return actPhoneNum;
	}

	public WebElement getHeaderInfo() {
		return headerInfo;
	}

	public WebElement getActualOrganizationName() {
		return actualOrganizationName;
	}

	public WebElement getActIndustry() {
		return actIndustry;
	}

	public WebElement getActType() {
		return actType;
	}

}

