package TestComponent;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;

import UtilityPackage.Utilis;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Flags.Flag;
import javax.mail.search.FlagTerm;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;

public class NewTest extends Utilis{
	
	
	@BeforeSuite
	public void beforeSuite() throws  IOException {

		
		FileInputStream inputStream = new FileInputStream("./Conf.properties");
		prop = new Properties();
		prop.load(inputStream);
		
		// Launch the browser with webdriver Manager 
		

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		report = new ExtentReports(System.getProperty("user.dir")+"\\ExtentReportResults.html");
	
	}

	

	@Test
	public void Validationforsignup() {
		
		try {
			
			test = report.startTest("Validate Signup page");
			
			// Launch the URL
			driver.get(prop.get("URL").toString());
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.manage().window().maximize();

			
			//Click the Dropdown
			Assert.assertEquals(true, click(prop.get("DROPDOWN").toString()));
			
			//Validate that the dropdown has "English" and "Dutch"
			Assert.assertEquals(true, Elementwait(prop.get("DROPDOWNENGLISHLABLE").toString()));
			Assert.assertEquals(true,  Elementwait(prop.get("DROPDOWNDUTCHLABLE").toString()));
			
			//click English Languague
			Assert.assertEquals(true, click(prop.get("DROPDOWNENGLISHLABLE").toString()));
			
			//Fill in your name.
			Assert.assertEquals(true, typetext(prop.get("NAMEFIELD").toString(), prop.get("INPUTNAME").toString()));
			
			//For organization, use your name as well.
			Assert.assertEquals(true, typetext(prop.get("COMPANYFIELD").toString(),  prop.get("INPUTCOMPANY").toString()));

			//Input your email address
			Assert.assertEquals(true, typetext(prop.get("MAILFIELD").toString(),  prop.get("MAILUSERNAME").toString()));

			
			
			//Click on "I agree to the Terms And Conditions"
			Assert.assertEquals(true, click(prop.get("AGREEMENTFIELD").toString()));
			
			Assert.assertEquals(true, click(prop.get("SUBMITBUTTON").toString()));
			
			Assert.assertEquals(true, mailvalidation(prop.get("MAILUSERNAME").toString(),prop.get("MAILPASSWORD").toString(),prop.get("MAILSUBJECT").toString()));
			
		} catch (Exception | AssertionError e) {
			Assert.fail();
		}
	}


	@AfterSuite
	public void afterSuite() {
		driver.quit();
		report.flush();
	}

	

}
