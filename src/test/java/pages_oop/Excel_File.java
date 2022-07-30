package pages_oop;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
//import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellReference;
//import org.openqa.selenium.WebDriver;

public class Excel_File {

	int sheetRowCount;
	HSSFSheet sheet;
	// FormulaEvaluator evaluator;

	// Initial Excel file name (A MUST)
	public Excel_File(String filename) throws IOException {
		// this.driver = driver;
		FileInputStream excelName = SetupExcelFile(filename);
		HSSFWorkbook hss = WorkHookFile(excelName);
		ReadExcelSheet(hss);
		// evaluator = hss.getCreationHelper().createFormulaEvaluator();
		sheetRowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
	}

	// Set up class & method for excel and reuse easily to other files
	public FileInputStream SetupExcelFile(String filename) throws IOException {
		// File file = new File("C:\\Users\\alaa_\\Desktop\\AutomationRequestClient\\AutomationClient\\mybenefits_data.xlsx");
		File file = new File(filename);
		FileInputStream inputStream = new FileInputStream(file);
		return inputStream;
	}

	// Open/Close workhook file
	public HSSFWorkbook WorkHookFile(FileInputStream fd) throws IOException {
		return new HSSFWorkbook(fd);
	}

	// Read sheet data
	public void ReadExcelSheet(HSSFWorkbook hss) throws IOException {
		// sheet = hss.getSheet("mybenefits_data");
		sheet = hss.getSheetAt(0);// There is only one sheet to be safe
	}

	// --------------------------[Getter Management]--------------------------//

	// General Getter with argument
	public String GetGeneralCellTypes(String cellID) throws Exception {

		CellReference cellReference = new CellReference(cellID);
		Row row = sheet.getRow(cellReference.getRow());
		Cell cell = row.getCell(cellReference.getCol());
		DateFormat formatter = new SimpleDateFormat("d-M-yyyy");

		if (cell != null) {
			switch (cell.getCellType()) { // Never use evaluator.evaluateFormulaCell(cell), for some reason, it doesn't work
			case STRING:
				return cell.getStringCellValue();
			case BOOLEAN:
				return Boolean.toString(cell.getBooleanCellValue());
			case NUMERIC: // Double number or Date e.g: 11-9-1946
				if (DateUtil.isCellDateFormatted(cell)) {
					return formatter.format(cell.getDateCellValue()).toString();
				} else {
					return Double.toString(cell.getNumericCellValue());
				}
			case BLANK:
				return "";
			case ERROR:
				throw new Exception("Error: " + cell.getErrorCellValue());

			default:
				return "ERROR, cell type is not supported";
			}
		}
		return null;
	}
}
