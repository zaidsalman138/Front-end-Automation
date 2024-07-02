package Utilities;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelUtils {
    private static XSSFWorkbook workbook;
    private static XSSFSheet sheet;

    /**
     * This method is to set the File path and to open the Excel file.
     * Pass Excel Path and SheetName as Arguments to this method.
     *
     * @param path     the path of the excel file
     * @param sheetName the name of the sheet within the excel file
     */
    public void setExcelFile(String path, String sheetName) {
        FileInputStream excelFile;
        try {
            excelFile = new FileInputStream(path);
            workbook = new XSSFWorkbook(excelFile);
            sheet = workbook.getSheet(sheetName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is to read the test data from the Excel cell.
     * In this, we are passing parameters/arguments as Row Num and Col Num.
     *
     * @param rowNum the row number
     * @param colNum the column number
     * @return the data from the specified cell
     */
    public  String getCellData(int rowNum, int colNum) {
        DataFormatter formatter = new DataFormatter();
        return formatter.formatCellValue(sheet.getRow(rowNum).getCell(colNum));
        
    }

    /**
     * This method is to get the row count used of the excel sheet.
     *
     * @return the number of used rows in the sheet
     */
    public int getRowCount() {
        return sheet.getPhysicalNumberOfRows();
    }

    /**
     * This method is to get the column count used of the excel sheet.
     *
     * @param rowNum the row number for which to count the columns
     * @return the number of used columns in the row
     */
    public int getColumnCount(int rowNum) {
        return sheet.getRow(rowNum).getPhysicalNumberOfCells();
    }
}
