package pages_oop;

//import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseDriver {
	
	public static WebDriver initializeDriver()
    {
		System.setProperty("webdriver.chrome.driver","D:\\[DevTest]\\WebDrivers_Selenium\\chromedriver.exe");
		//System.setProperty("webdriver.chrome.driver","/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        return driver;
    }
}
