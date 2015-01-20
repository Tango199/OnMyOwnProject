import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;

import com.sun.rowset.internal.Row;


public class test {

	public static void main(String[] args) throws IOException {
		  /* Create Workbook and Worksheet */
        HSSFWorkbook my_workbook = new HSSFWorkbook();
        HSSFSheet my_sheet = my_workbook.createSheet("Cell Font");
        /* Get access to HSSFCellStyle */
        HSSFCellStyle my_style = my_workbook.createCellStyle();
        /* We will now specify a background cell color */
        my_style.setFillPattern(HSSFCellStyle.FINE_DOTS );
        my_style.setFillForegroundColor(new HSSFColor.BLUE().getIndex());
        my_style.setFillBackgroundColor(new HSSFColor.RED().getIndex());
        
        /* Create a row in the sheet */
        HSSFRow row = my_sheet.createRow(0);
        /* Create a cell */
        Cell cell = row.createCell(0);
        cell.setCellValue("This text will be in bold red color");
        /* Attach the style to the cell */
        cell.setCellStyle(my_style);
        /* Write changes to the workbook */
        FileOutputStream out = new FileOutputStream(new File("I:\\cell_fill_color.xls"));
        my_workbook.write(out);
        out.close();
        
        
}

	}


