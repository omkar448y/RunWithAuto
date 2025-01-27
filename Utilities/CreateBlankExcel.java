package Utilities;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
public class CreateBlankExcel {
	public static void main(String[] args) {
		  // Specify the exact path where you want to save the Excel file
        String filePath = "C:/Users/oyadavx/git/Intel_ISVC_R4C/Intel_ISVC_R4C/src/test/java/NPRCredit_ULT_BuilkUploadportal.xlsx";

        // Create a new blank workbook
        Workbook workbook = new XSSFWorkbook();  // For .xlsx format, use HSSFWorkbook for .xls format

        // Create a blank sheet
        workbook.createSheet("Sheet1");  // Adds a blank sheet named "Sheet1"

        // Write the workbook to the specified file path
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
            System.out.println("Blank Excel file created at: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Close the workbook
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
