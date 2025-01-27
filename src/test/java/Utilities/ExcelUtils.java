package Utilities;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
public class ExcelUtils {

    public static String getCellData(String excelFilePath, int sheetIndex, int rowIndex, int cellIndex) {
        String cellData = "";
        try (FileInputStream excelFile = new FileInputStream(new File(excelFilePath));
             Workbook workbook = new XSSFWorkbook(excelFile)) {

            Sheet sheet = workbook.getSheetAt(sheetIndex);
            Row row = sheet.getRow(rowIndex);
            
            if (row != null) {
                Cell cell = row.getCell(cellIndex);
                
                if (cell != null) {
                    switch (cell.getCellType()) {
                        case STRING:
                            cellData = cell.getStringCellValue().trim();
                            break;
                        case NUMERIC:
                            cellData = String.valueOf((int) cell.getNumericCellValue());
                            break;
                        case BOOLEAN:
                            cellData = String.valueOf(cell.getBooleanCellValue());
                            break;
                        case FORMULA:
                            cellData = cell.getCellFormula();
                            break;
                        case BLANK:
                            cellData = "";
                            break;
                        default:
                            cellData = cell.toString().trim();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cellData;
    }
	
	
	
}
