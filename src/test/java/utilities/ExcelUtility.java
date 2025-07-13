package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Utility class for handling Excel file operations.
 * Provides methods to read, write, and manipulate data in Excel files using Apache POI.
 */
public class ExcelUtility {

    // File input stream for reading Excel files
    public FileInputStream fi;
    // File output stream for writing to Excel files
    public FileOutputStream fo;
    // Workbook object representing the Excel file
    public XSSFWorkbook workbook;
    // Sheet object representing a specific sheet in the workbook
    public XSSFSheet sheet;
    // Row object representing a specific row in the sheet
    public XSSFRow row;
    // Cell object representing a specific cell in the row
    public XSSFCell cell;
    // Style object for formatting cells
    public CellStyle style;
    // Path to the Excel file
    String path;

    /**
     * Constructor to initialize the ExcelUtility with the file path.
     *
     * @param path The path to the Excel file.
     */
    public ExcelUtility(String path) {
        this.path = path;
    }

    /**
     * Gets the number of rows in the specified sheet.
     *
     * @param sheetName The name of the sheet.
     * @return The number of rows in the sheet.
     * @throws IOException If an I/O error occurs.
     */
    public int getRowCount(String sheetName) throws IOException {
        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);
        int rowcount = sheet.getLastRowNum();
        workbook.close();
        fi.close();
        return rowcount;
    }

    /**
     * Gets the number of cells in the specified row of the sheet.
     *
     * @param sheetName The name of the sheet.
     * @param rownum The row number.
     * @return The number of cells in the row.
     * @throws IOException If an I/O error occurs.
     */
    public int getCellCount(String sheetName, int rownum) throws IOException {
        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rownum);
        int cellcount = row.getLastCellNum();
        workbook.close();
        fi.close();
        return cellcount;
    }

    /**
     * Gets the data from a specific cell in the sheet.
     *
     * @param sheetName The name of the sheet.
     * @param rownum The row number.
     * @param colnum The column number.
     * @return The data in the cell as a String.
     * @throws IOException If an I/O error occurs.
     */
    public String getCellData(String sheetName, int rownum, int colnum) throws IOException {
        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rownum);
        cell = row.getCell(colnum);

        DataFormatter formatter = new DataFormatter();
        String data;
        try {
            data = formatter.formatCellValue(cell); // Returns the formatted value of a cell as a String regardless of the cell type.
        } catch (Exception e) {
            data = "";
        }
        workbook.close();
        fi.close();
        return data;
    }

    /**
     * Sets the data in a specific cell of the sheet.
     *
     * @param sheetName The name of the sheet.
     * @param rownum The row number.
     * @param colnum The column number.
     * @param data The data to set in the cell.
     * @throws IOException If an I/O error occurs.
     */
    public void setCellData(String sheetName, int rownum, int colnum, String data) throws IOException {
        File xlfile = new File(path);
        if (!xlfile.exists()) { // If Excel file does not exist, create a new file
            workbook = new XSSFWorkbook();
            fo = new FileOutputStream(path);
            workbook.write(fo);
        }

        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);

        if (workbook.getSheetIndex(sheetName) == -1) // If sheet does not exist, create a new sheet
            workbook.createSheet(sheetName);
        sheet = workbook.getSheet(sheetName);

        if (sheet.getRow(rownum) == null) // If row does not exist, create a new row
            sheet.createRow(rownum);
        row = sheet.getRow(rownum);

        cell = row.createCell(colnum);
        cell.setCellValue(data);
        fo = new FileOutputStream(path);
        workbook.write(fo);
        workbook.close();
        fi.close();
        fo.close();
    }

    /**
     * Fills a specific cell with green color.
     *
     * @param sheetName The name of the sheet.
     * @param rownum The row number.
     * @param colnum The column number.
     * @throws IOException If an I/O error occurs.
     */
    public void fillGreenColor(String sheetName, int rownum, int colnum) throws IOException {
        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);

        row = sheet.getRow(rownum);
        cell = row.getCell(colnum);

        style = workbook.createCellStyle();

        style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        cell.setCellStyle(style);
        workbook.write(fo);
        workbook.close();
        fi.close();
        fo.close();
    }

    /**
     * Fills a specific cell with red color.
     *
     * @param sheetName The name of the sheet.
     * @param rownum The row number.
     * @param colnum The column number.
     * @throws IOException If an I/O error occurs.
     */
    public void fillRedColor(String sheetName, int rownum, int colnum) throws IOException {
        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rownum);
        cell = row.getCell(colnum);

        style = workbook.createCellStyle();

        style.setFillForegroundColor(IndexedColors.RED.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        cell.setCellStyle(style);
        workbook.write(fo);
        workbook.close();
        fi.close();
        fo.close();
    }
}