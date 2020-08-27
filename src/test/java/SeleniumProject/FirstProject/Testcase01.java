package SeleniumProject.FirstProject;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Pages.HomePage;
import Pages.LoginPage;

public class Testcase01 extends Baseclass {

	public static Logger log = LogManager.getLogger(Testcase01.class.getName());
	WebDriver driver;
	@BeforeClass
	public void openBrowser() throws IOException {
		log.info("Initializing Driver");
		driver=initializeDriver();
		log.debug("Entering Url");
		driver.get(prop.getProperty("url"));	
		HomePage home = new HomePage(driver);
		home.clickOnLogIn();
		
		log.info("Landed to Home Page");
	}
		
	//this will execute 2 times bcoz dataprovider gives 2 set of data 
	@Test(dataProvider="getData")
	public void checkLoginPage(String email,String password,String info)
	{
		LoginPage login = new LoginPage(driver);
		login.setEmail(email);
		login.setPassword(password);
		login.clickOnLogin();
		log.info(info);
	}
	
	@DataProvider
	public Object[][] getData() throws Exception
	{
		Thread.sleep(500);
		Object[][] data = new Object[2][3];
		//1st set of data
		data[0][0]="pavan@gmail.com";
		data[0][1]="1234569";
		data[0][2]="Invalid Input";
		
		//2nd set of data
		data[1][0]="pavan@gmail.com";
		data[1][1]="1234569";
		data[1][2]="Invalid Input";
		
		return data;
	}
	
	@AfterClass
	public void closingDriver()
	{
		driver.close();
		log.info("Closing TestCase1");
	}
}
