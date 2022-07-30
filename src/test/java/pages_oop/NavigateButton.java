package pages_oop;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigateButton {
	WebDriver driver;


	public NavigateButton(WebDriver driver) {
		this.driver = driver;
	}

	// Locator for clicking button/navigation elements
	//By startButton = By.id("personal-eligibility-check");
	By startButton = By.cssSelector("#personal-eligibility-check");
	By nextBtn = By.cssSelector(".mat-stepper-next.btn.btn-primary.my-2.next-question.ng-star-inserted");
	By nextBtnAge = By.cssSelector("#cdk-step-content-0-2 > div > div.btns-wrap.col-12 > button.mat-stepper-next.btn.btn-primary.my-2.next-question.ng-star-inserted");
	By previousBtn = By.className("mx-2");
	By flagBtn = By.cssSelector(".step-2 > div:nth-child(5) > button:nth-child(1)");

	By allRightsBtnNav = By.cssSelector(".btn.dd-controller");
	By allRightsBtn = By.cssSelector(".app-btn-lg.btn-primary.float-right");

	// Last resort, each next button has next number until 12
	// By nextBtn1 = By.cssSelector(".step-1 > div:nth-child(5) > button:nth-child(1)");
	// By nextBtn2 = By.cssSelector(".step-2 > div:nth-child(5) > button:nth-child(1)");

	public void ClickInitialButton() throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(startButton).click();
	}

	public void ClickNextButton() {
		driver.findElement(nextBtn).click();
	}
	public void ClickNextButtonAge() { //last resort
		driver.findElement(nextBtnAge).click();
	}

	public void isNextButtonEnabled() {
		driver.findElement(nextBtn).isEnabled();
	}

	public void ClickPreviousButton() {
		driver.findElement(previousBtn).click();
	}
	public void ClickFlagButton() {
		driver.findElement(flagBtn).click();
	}

	public void ClickAllRightsButton() throws InterruptedException {
		driver.findElement(allRightsBtnNav).click();
		Thread.sleep(800);
		driver.findElement(allRightsBtn).click();
	}
}
