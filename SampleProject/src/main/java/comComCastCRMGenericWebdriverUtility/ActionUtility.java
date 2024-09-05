package comComCastCRMGenericWebdriverUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ActionUtility {
	
	public void mouseHoversOnElement(WebDriver driver,WebElement element) {
		Actions action=new Actions(driver);
		action.moveToElement(element).perform();;
	}
	
	public void mouseHoverBasedonCoordinates(WebDriver driver,int x,int y) {
		Actions action=new Actions(driver);
		action.moveByOffset(x, y).perform();;
		
	}
	
	public void rightClickOnElement(WebDriver driver,WebElement element) {
		Actions action=new Actions(driver);
		action.contextClick(element).perform();
	}
	
	public void scrollTheWebPage(WebDriver driver,int x,int y) {
		Actions action=new Actions(driver);
		action.scrollByAmount(x, y).perform();
	}
	
	public void scrollThePageToElement(WebDriver driver,WebElement element) {
		Actions action=new Actions(driver);
		action.scrollToElement(element).perform();
	}
	
	
	public void doubleClick(WebDriver driver,WebElement element) {
		Actions action=new Actions(driver);
		action.doubleClick(element).perform();
	}
	
	public void toClickAndHoldElement(WebDriver driver,WebElement element) {
		Actions action=new Actions(driver);
		action.clickAndHold().perform();
	}
	
	public void dragAndDrop(WebDriver driver,WebElement source,WebElement target) {
		Actions action=new Actions(driver);
		action.dragAndDrop(source,target).perform();
	}
	

}

