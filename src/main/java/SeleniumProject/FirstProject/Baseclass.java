package SeleniumProject.FirstProject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;



public class Baseclass {
	
	WebDriver driver;
	public  Properties prop;
	public  WebDriver initializeDriver() throws IOException
	{
		prop= new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\data.properties");
		prop.load(fis);
		
		String browserName=prop.getProperty("browser");
		
		//mvn test -Dbrowser=chrome
		//String browserName = System.getProperty("browser");   //---> we can provide browser name through jenkins if we do like this
		
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		
		if(browserName.contains("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
			
				ChromeOptions opt = new ChromeOptions();
				if(browserName.contains("headless")) {
					opt.addArguments("headless");
				}
				driver=new ChromeDriver(opt);
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "./driver/geckodriver.exe");
			driver=new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("ie"))
		{
			driver=new InternetExplorerDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	
	public String getScreenShotPath(WebDriver driver,String testCaseName) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+"\\reports\\"+testCaseName+".png";
		File dest = new File(path);
		FileUtils.copyFile(src, dest);
		return path;
	}
	
	static  ExtentReports extent;
	public static ExtentReports getExtentReportObj()
	{
		String path= System.getProperty("user.dir")+"\\reports\\index.html";  //---> this is where report link is present
		ExtentSparkReporter reporter= new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Report");
		reporter.config().setDocumentTitle("Project Results");
		
		 extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Pavan");
		return extent;
	}
}
