package UtilityPackage;

import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Flags.Flag;
import javax.mail.search.FlagTerm;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Utilis {
	
	
	public static WebDriver driver;
	public static 	Properties prop;
	public static ExtentTest test;
	public static ExtentReports report;

	
	
	public static boolean Elementwait(String Xpathelement)
	{
		boolean flag=false;
		try {
			WebDriverWait elementwait=new WebDriverWait(driver, 10);
			elementwait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Xpathelement)));
			test.log(LogStatus.PASS, "Element :"+Xpathelement+" is Present");
			flag=true;
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Element :"+Xpathelement+" is not Present");
		}
		
		return flag;
		
	}
	
	public static boolean click(String Xpathelement)
	{
		boolean flag=false;
		try {
			driver.findElement(By.xpath(Xpathelement)).click();
			test.log(LogStatus.PASS, "Element :"+Xpathelement+" is Clicked");
			flag=true;
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Element :"+Xpathelement+" is not Clicked");
		}
		
		return flag;
		
	}
	
	
	public static boolean typetext(String Xpathelement,String inputtext)
	{
		boolean flag=false;
		try {
			driver.findElement(By.xpath(Xpathelement)).sendKeys(inputtext);;
			System.out.println();
			test.log(LogStatus.PASS, inputtext+" is typed in"+Xpathelement);
			flag=true;
		} catch (Exception e) {
			test.log(LogStatus.FAIL, inputtext+" is not typed in"+Xpathelement);
		}
		
		return flag;
		
	}
	
	
	public static boolean mailvalidation(String Username,String Password, String Subject) throws MessagingException
	{
		boolean flag=false;
		Properties properties = new Properties();
		Session session = Session.getDefaultInstance(properties);
		Store store = session.getStore("imaps");
		store.connect("pop.gmail.com", Username,Password);
		Folder emailFolder = store.getFolder("INBOX");
		emailFolder.open(Folder.READ_ONLY);
		Message[] messages = emailFolder.search(new FlagTerm(new Flags(Flag.SEEN), false));
		int s = messages.length;
		for (int i = s; i > (s - 500); i--) {
			Message message = messages[i - 1];
			System.out.println(message.getSubject());
			if (message.getSubject().contains(Subject)) {
				test.log(LogStatus.PASS, "Mail is Identified");
				flag=true;
				break;
			}
			}
		if(!flag)
		{
			test.log(LogStatus.FAIL, "Mail is not Identified");
		}
		return flag;
	}

}
