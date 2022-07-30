package pages_oop;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;

public class WebSource {

	WebDriver driver;

	public WebSource(WebDriver driver) {
		this.driver = driver;
	}

	public void InitiateWebSource(String url, int width, int height) {
		driver.get(url);
		driver.manage().window().setSize(new Dimension(width, height));
	}

	public void ForwardPage() {
		driver.navigate().forward();
	}

	public void BackwardPage() {
		driver.navigate().back();
	}

	public void RefreshPage() {
		driver.navigate().refresh();
	}

	public void NavigateURL(String url) {
		driver.navigate().to(url);
	}

}
