package comComCastCRMGenericWebdriverUtility;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SelectUtility {
	public void selectByIndex(WebElement element,int index) {
		Select select =new Select(element);
		select.selectByIndex(index);
	}
	public void selectByVisibleText(WebElement element,String text) {
		Select select =new Select(element);
		select.selectByValue(text);
	}
	
	public void selectByValue(WebElement element,String value) {
		Select select =new Select(element);
		select.selectByValue(value);
	}
	
	public void deSelectByIndex(WebElement element,int index) {
		Select select =new Select(element);
		select.deselectByIndex(index);
	}
	
	public void deSelectByValue(WebElement element,String value) {
		Select select =new Select(element);
		select.deselectByValue(value);
	}
	
	public void deSelectByVisibleText(WebElement element,int text) {
		Select select =new Select(element);
		select.deselectByIndex(text);
	}
	
	public void deSelectAllDropDown(WebElement element) {
		Select select =new Select(element);
		select.deselectAll();
	}
	
	public List<WebElement> getOptionsOfDropDown(WebElement element) {
		Select select =new Select(element);
		List<WebElement> allOptions = select.getAllSelectedOptions();
		return allOptions;
	}
	
	
}

