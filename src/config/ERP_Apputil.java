package config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.page.AdminLogin;
import com.page.AdminLogout;

public class ERP_Apputil {
	
	public static WebDriver driver;
	public static Properties conpro;
	@BeforeTest
	public void setUp() throws Throwable{
		conpro = new Properties();
		//load the file
		conpro.load(new FileInputStream("./PropertyFiles/Environment.properties"));
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(conpro.getProperty("url"));
		AdminLogin login = PageFactory.initElements(driver, AdminLogin.class);
		login.verify_Login("admin", "master");
	}
	@AfterTest
	public void tearDown() {
		AdminLogout logout = PageFactory.initElements(driver, AdminLogout.class);
		logout.verify_Logout();
		driver.close();
	}

}
