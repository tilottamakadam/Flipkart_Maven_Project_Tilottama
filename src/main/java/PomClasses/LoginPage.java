package PomClasses;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import UtilityClasses.Util1;

public class LoginPage extends Util1{
	
	WebDriver driver;
	
	@FindBy(xpath="//input[@class='_2IX_2- VJZDxU']")
	private WebElement emailField;
	
	@FindBy(xpath="//input[@class='_2IX_2- _3mctLh VJZDxU']")
	private WebElement passwordField;
	
	@FindBy(xpath="//button[@class='_2KpZ6l _2HKlqd _3AWRsL']")
	private WebElement loginBtn;
	
	public LoginPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}
	
	public void enterEmail() throws IOException
	{
		emailField.sendKeys(getProperty("username"));
	}
	
	
	public void enterPassword() throws IOException
	{
		passwordField.sendKeys(getProperty("password"));
	}
	
	
	

	public void clickLoginBtn()
	{
		loginBtn.click();
	}
}
