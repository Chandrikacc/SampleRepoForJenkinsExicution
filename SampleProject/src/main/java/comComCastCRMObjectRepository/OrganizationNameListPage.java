package comComCastCRMObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationNameListPage {
	WebDriver driver;
	public OrganizationNameListPage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(id = "search_txt")
	private WebElement searchTextField;
	
	
	@FindBy(name = "search")
	private WebElement searchNowButton;
	
	
	public WebElement getSearchTextField() {
		return searchTextField;
	}


	public WebElement getSearchNowButton() {
		return searchNowButton;
	}
	

	

}

