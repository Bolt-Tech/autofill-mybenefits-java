package test_case;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import pages_oop.BaseDriver;
import pages_oop.WebSource;
import pages_oop.NavigateButton;
import pages_oop.Setup_Data;
import pages_oop.Excel_File;

public class Setup_Data_Demo {

	private WebDriver driver;
	private WebSource webSource;
	private NavigateButton navOptions;
	private Setup_Data dataOptions;
	private Excel_File excelOptions;
	private Map<String, Object> vars;
	JavascriptExecutor js;

	public Setup_Data_Demo() throws IOException {//Constructor
		super();
		excelOptions = new Excel_File("D:\\Automation-ElimelechClient\\RelevantDemo\\AutoFill_MyBenefits\\mybenefits_data.xls");
		//excelOptions = new Excel_File("/mybenefits_data.xls");
	}
	
	@Before
	public void setUp() throws IOException {
		driver = BaseDriver.initializeDriver();
		js = (JavascriptExecutor) driver;
		vars = new HashMap<String, Object>();		
	}

	@After
	public void tearDown() {
		//driver.quit();
	}

	// ------[Fill in some questions after typing password then gender, date etc]------//
	@Test
	public void GovBenefits() throws NumberFormatException, Exception {
		Logger logger = LogManager.getLogger(Setup_Data_Demo.class);
		logger.info("Exploring mybenefits site");
		logger.debug("Starting a website...");
		webSource = new WebSource(driver);
		webSource.InitiateWebSource("https://mybenefits.gov.il/", 1350, 1060);

		// -----------[WORK START HERE]---------//

		navOptions = new NavigateButton(driver);
		dataOptions = new Setup_Data(driver);
		//Thread.sleep(1000); //Wait for page to load before initial
		navOptions.ClickInitialButton();
		int passcode = ((int)(Math.random()*9000)+1000); //Generating random 4-digits pass-code
		dataOptions.savePasscodeForWeek(passcode);
		logger.debug("Passcode will expire next week, so 4-digits passcode is: " + passcode);
		Thread.sleep(2000); //Wait for pop-up saying welcome to finish in a few seconds

		//Questioner starts here
		try {
			String gender = excelOptions.GetGeneralCellTypes("D3");
			System.out.println("Excel, gender is: " + gender); //(Debug) Check if it got excel data
			dataOptions.SelectGender(gender);
			navOptions.ClickNextButton();
			
			boolean boolResident = Boolean.valueOf(excelOptions.GetGeneralCellTypes("D4"));
			dataOptions.AreYouResident(boolResident);//Use Excel_File with GetBoolCell
			System.out.println("Excel, is resident? " + boolResident); //(Debug) Check if it got excel data
			navOptions.ClickFlagButton(); //Works as a last resort
			
			String dateValue = excelOptions.GetGeneralCellTypes("D5");
			String [] dateParts = dateValue.split("-");
			String day = dateParts[0], month = dateParts[1], year = dateParts[2];
			
			dataOptions.DateOfBirth(day, month, year); //Use Excel_File with GetIntCell or Date
			System.out.println("Excel, date is: " + dateValue); //(Debug) Check if it got excel data
			System.out.println("Excel, day: " + day + ", Month: " + month + ", Year: " + year); //Debug
			navOptions.ClickNextButtonAge();
		} catch (Exception e) {
			System.out.println("Error occured from excel");
		}
		Thread.sleep(1000);
		double salary = Double.valueOf(excelOptions.GetGeneralCellTypes("D25"));
		dataOptions.InputSalary(salary);
		System.out.println("Excel, salary: " + salary); //Debug
		Thread.sleep(10000); //Keeps window open for a while before driver quits
	}
}
