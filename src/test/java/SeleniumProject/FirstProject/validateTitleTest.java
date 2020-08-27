package SeleniumProject.FirstProject;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Pages.HomePage;

public class validateTitleTest extends Baseclass{

	public static Logger log = LogManager.getLogger(validateTitleTest.class.getName());
	WebDriver driver;
	@BeforeClass
	public void openBrowser() throws Exception
	{
		log.info("Initializing Driver");
		driver=initializeDriver();
		driver.get(prop.getProperty("url"));	
		log.info("Landed to Home Page");
	}
	
	HomePage home;
	@Test
	public void ValidateTitle() throws IOException {			
		home = new HomePage(driver);
		String pageTitle=home.getPageTitle().getText();
		SoftAssert assirtion = new SoftAssert();
		assirtion.assertEquals(pageTitle, "FEATURED COURSES","oh god");
		log.info("Verified PageTitle");
		System.out.println("Done and Dusted");
		assirtion.assertAll();	
	}
	
	@Test
	public void validateSubTitle()
	{
		System.out.println(home.getSubTitle().getText());
	}
	
	@AfterClass
	public void closeDriver()
	{
		driver.close();
		log.info("Closing TestCase2");
	}
}
