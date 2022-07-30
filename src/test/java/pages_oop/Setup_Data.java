package pages_oop;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Setup_Data {
	WebDriver driver;

	public Setup_Data(WebDriver driver) {
		this.driver = driver;
	}

	// Locator for filling fields

	//By savePasscodeButton = By.cssSelector("app-btn.btn-blue"); //Asking to save passcode and after input save button(same className)
	By savePasscodeButton = By.cssSelector("#mat-dialog-0 > app-save-ans-modal > div.btns-row > div > button");
	By passcodeField = By.id("password");
	By finalSavePasscodeButton = By.cssSelector("#mat-dialog-1 > app-user-code-modal > form > div.btns-row.flex-wrap > div > button");
	By disabledSaveButton = By.cssSelector("app-btn btn-blue disabled"); //If incomplete input will be disable save button	
	By noSavePasscode = By.className("app-btn btn-stroke");
	//Q1
	By male = By.cssSelector("#mat-radio-11 > label");
	By female = By.cssSelector("#mat-radio-12 > label");
	//Q2
	By yesResident = By.cssSelector("#mat-radio-14 > label");
	By noResident = By.cssSelector("#mat-radio-15 > label");
	//Q Salary
	//By salaryField = By.cssSelector("#mat-input-2");
	By salaryField = By.cssSelector("#mat-input-0");

	//List rights (Broto) button
	By rightSalary = By.cssSelector(".btn.btn-outline-primary.learn-more.ng-star-inserted");
	//By anchorSalary = By.id("right_main_btn2");
	By anchorSalary = By.cssSelector("#right_main_btn2");
	By yearBirth = By.id("date_of_birth-input-year");
	By monthBirth = By.id("date_of_birth-input-month");
	By dayBirth = By.id("date_of_birth-input-day");


	//Save pass-code for initial questioner (you can use for later for up to a week)
	public void savePasscodeForWeek(int passcode) throws InterruptedException {
		//Thread.sleep(900);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(savePasscodeButton).click();
		Thread.sleep(900);
		driver.findElement(passcodeField).sendKeys(String.valueOf(passcode));
		Thread.sleep(900);
		WebElement saveElementBtn = driver.findElement(finalSavePasscodeButton);
		//System.out.println("Is button enabled? " + saveElementBtn.isEnabled());
		if (saveElementBtn.isEnabled()) {
			saveElementBtn.click();
		}
	}
	public void cancelSavingPasscode() {
		driver.findElement(noSavePasscode).click();
	}
	
	//Question 1: Gender
	public void SelectGender(String gender) throws InterruptedException {
		Thread.sleep(900);
		if (gender.equalsIgnoreCase("זכר")) { //Male
			driver.findElement(male).click();
		}
		else { //Female
			driver.findElement(female).click();
		}
		Thread.sleep(900); //Wait a little bit to review gender
	}
	
	//Question 2: Resident of this country
	public void AreYouResident(boolean isResident) throws InterruptedException {
		if (isResident) {
			driver.findElement(yesResident).click();
		}
		else {
			driver.findElement(noResident).click();
		}
		Thread.sleep(900);
	}
	//Question 3: Date of Birth
	public void DateOfBirth(String day, String month, String year) throws InterruptedException {
		driver.findElement(dayBirth).sendKeys(day);
		driver.findElement(monthBirth).sendKeys(month);
		driver.findElement(yearBirth).sendKeys(year);
		Thread.sleep(900);
	}

	//Salary Rights
	public void InputSalary(double salary) throws InterruptedException {
		driver.navigate().to("https://mybenefits.gov.il/product-page/1922");
		//Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(anchorSalary).click();
		driver.findElement(salaryField).sendKeys(String.valueOf(salary));
		Thread.sleep(900);
	}

//	public String GetWelcomeName() {
//		return driver.findElement(welcomeAccName).getText();
//	}
//	
//	public By GetElementSignedInName() {
//		return welcomeAccName;
//	}
}

