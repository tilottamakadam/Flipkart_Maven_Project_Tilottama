package TestClasses;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import BaseClasses.Base1;
import PomClasses.HomePage;
import PomClasses.ProfilePage;

public class VeryfyUserCanAddNewAddress {
	
	static WebDriver driver;
	
	HomePage hp;
	ProfilePage pp;
	
	ExtentHtmlReporter ExtentReporter;
	
	ExtentReports report;
	ExtentTest test;

	@BeforeClass
	public void BeforClass() throws IOException
	{
		ExtentReporter=Base1.getHtmlReporter();
		report=Base1.getReports();
		test=Base1.getExtentTest("VeryfyUserCanAddNewAddress");;
		driver=Base1.getDriver("chrome");
	}
	
	@BeforeMethod
	public void BeforMethod()
	{
		hp=new HomePage(driver);
		
		
		
	}
	
	@Test
	public void verifyUserCanGoToProfilePage()
	{
		hp.moveToProfileName();
		hp.clickOnProfileText();
		hp.moveMouse(driver);
		
		pp = new ProfilePage(driver);
		Assert.assertTrue(pp.getFullProfileName());;
		
	}
	@Test(priority=1)
	public void veryUserCanAddAddress() throws InterruptedException
	{
		pp.clickManageAddressText();
		
		int previousAddressCount = pp.savedAddressCount();
		System.out.println(previousAddressCount);
		
		pp.clickAddAddressBtn();
	    pp.fillAddressDetails();
		pp.fillMainAddress();
		pp.clickOnSaveBtn();
		
		Thread.sleep(1000);
		int currentAddressCount = pp.savedAddressCount();
		
		Assert.assertEquals(currentAddressCount, previousAddressCount + 1);
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException
	{
		if(result.getStatus() == ITestResult.SUCCESS)
		{
			test.log(Status.PASS, result.getName() + " test passed");
		}
		else {
			
			String path = pp.getScreenshot(driver, result.getName());
			test.log(Status.FAIL, result.getName() + " test failed", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		}
		
	}
	
	
	@AfterClass
	public void afterClass()
	{
		report.flush();
	}


}
