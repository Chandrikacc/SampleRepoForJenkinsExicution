package comComCastCRMObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	WebDriver driver;
	public HomePage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(linkText = "Organizations")
	private WebElement organizationsLink;
	
	@FindBy(linkText = "Contacts")
	private WebElement contactLink;
	
	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement logoutIcon;
	
	@FindBy(linkText = "Sign Out" ) 
	private WebElement signoutLink;

	public WebElement getOrganizationsLink() {
		return organizationsLink;
	}

	public WebElement getContactLink() {
		return contactLink;
	}

	public WebElement getLogoutIcon() {
		return logoutIcon;
	}

	public WebElement getSinoutLink() {
		return signoutLink;
	}
	
	public void logoutFromApplication() {
		logoutIcon.click();
		signoutLink.click();	
	}
	

}

