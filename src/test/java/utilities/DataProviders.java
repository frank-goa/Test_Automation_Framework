package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

/**
 * Utility class providing data for test methods using TestNG's @DataProvider annotation.
 * Contains multiple data providers for different test scenarios.
 */
public class DataProviders {

    /**
     * DataProvider method to supply login data for tests.
     * Reads data from an Excel file and returns it as a two-dimensional array.
     *
     * @return A two-dimensional array containing login data (username and password).
     * @throws IOException If an error occurs while reading the Excel file.
     */
    @DataProvider(name = "LoginData")
    public String[][] getData() throws IOException {

        // Path to the Excel file containing login data
        String path = "./testData/Opencart_LoginData.xlsx";

        // Creating an object for ExcelUtility to interact with the Excel file
        ExcelUtility xlutil = new ExcelUtility(path);

        // Getting the total number of rows and columns in the specified sheet
        int totalrows = xlutil.getRowCount("Sheet1");
        int totalcols = xlutil.getCellCount("Sheet1", 1);

        // Creating a two-dimensional array to store the login data
        String logindata[][] = new String[totalrows][totalcols];

        // Reading data from the Excel file and storing it in the array
        for (int i = 1; i <= totalrows; i++) {  // Loop through rows
            for (int j = 0; j < totalcols; j++) {  // Loop through columns
                logindata[i - 1][j] = xlutil.getCellData("Sheet1", i, j);  // Retrieve cell data
            }
        }

        // Returning the two-dimensional array containing login data
        return logindata;
    }

    // DataProvider 2
    @DataProvider(name = "LoginData2")
    public String[][] getData_2() throws IOException {

        // Path to the Excel file containing login data
        String path = "./testData/Opencart_LoginData-Invalid.xlsx";

        // Creating an object for ExcelUtility to interact with the Excel file
        ExcelUtility xlutil = new ExcelUtility(path);

        // Getting the total number of rows and columns in the specified sheet
        int totalrows = xlutil.getRowCount("Sheet1");
        int totalcols = xlutil.getCellCount("Sheet1", 1);

        // Creating a two-dimensional array to store the login data
        String logindata[][] = new String[totalrows][totalcols];

        // Reading data from the Excel file and storing it in the array
        for (int i = 1; i <= totalrows; i++) {  // Loop through rows
            for (int j = 0; j < totalcols; j++) {  // Loop through columns
                logindata[i - 1][j] = xlutil.getCellData("Sheet1", i, j);  // Retrieve cell data
            }
        }

        // Returning the two-dimensional array containing login data
        return logindata;
    }

    // DataProvider 3

    // DataProvider 4
}