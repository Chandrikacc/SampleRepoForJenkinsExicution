package comComCastCRMObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import comComCastCRMGenericWebdriverUtility.WebDriverUtility;

public class LoginPage {
	
	WebDriver driver;
	public LoginPage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver,this);		
	}
	WebDriverUtility wlib=new WebDriverUtility();

	@FindBy(name="user_name")
	private WebElement usernameTextField;
	
	@FindBy(name="user_password")
	private WebElement passwordTextField;

	
	@FindBy(id="submitButton")
	private WebElement loginButton;


	public WebElement getUsernameTextField() {
		return usernameTextField;
	}


	public WebElement getPasswordTextField() {
		return passwordTextField;
	}


	public WebElement getLoginButton() { 
		return loginButton;
	}

	
	//Provide Action	
	public void loginToApplication(String url,String username,String password) {
		driver.get(url);
		wlib.waitForPageToLoad(driver);
		driver.manage().window().maximize();
		usernameTextField.sendKeys(username);
		passwordTextField.sendKeys(password);
		loginButton.click();
		
	}
	
	
}

