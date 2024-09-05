package comComCastCRMObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInformationPage {
	WebDriver driver;
	public ContactInformationPage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}

	@FindBy(id = "dtlview_Support Start Date")
	private WebElement actualStartDate;

	@FindBy(id = "dtlview_Support End Date")
	private WebElement actualEndDate;

	public WebElement getActualStartDate() {
		return actualStartDate;
	}

	public WebElement getActualEndDate() {
		return actualEndDate;
	}

}