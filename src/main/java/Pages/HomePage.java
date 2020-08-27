package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	@FindBy(xpath="//*[text()='Login']")
	private WebElement login;
	
	@FindBy(xpath ="//*[@id='content']//div[@class='text-center']/h2")
	private WebElement pageTitle;
	
	@FindBy(xpath = "//*[@class='carousel-caption']//p")
	private WebElement subTitle;
	
	public HomePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnLogIn()
	{
		login.click();
	}
	
	public WebElement getPageTitle()
	{
		return pageTitle;
	}
	
	public WebElement getSubTitle()
	{
		return subTitle;
	}
}
