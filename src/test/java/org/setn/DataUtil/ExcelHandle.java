package org.setn.DataUtil;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.setn.MainClass.BasicClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelHandle extends BasicClass {

    public static String data = "";

    public static String getExcelData(int row, int cell) {
        File file = new File("resources/Data.xlsx");
        try (FileInputStream fis = new FileInputStream(file);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
            XSSFSheet sheet = workbook.getSheetAt(0);
            DataFormatter df = new DataFormatter();
            data = df.formatCellValue(sheet.getRow(row).getCell(cell));
        } catch (IOException e) {
            System.err.println("Could not fetch data at "+row+" row, "+cell+" cell");
            throw new RuntimeException(e);
        }
        return data;
    }
}
