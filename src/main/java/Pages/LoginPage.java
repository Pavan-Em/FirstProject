package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	@FindBy(id="user_email")
	private WebElement email;
	
	@FindBy(id="user_password")
	private WebElement password;
	
	@FindBy(xpath = "//*[@value='Log In']")
	private WebElement loginButton;
	
	public LoginPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public void setEmail(String name)
	{
		email.sendKeys(name);
	}
	
	public void setPassword(String Password)
	{
		password.sendKeys(Password);
	}
	
	public void clickOnLogin()
	{
		loginButton.click();
	}
}
