package app.services;

import app.models.EntityModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class PoiServiceExcel {

    private void writeHeaderLine(XSSFWorkbook workbook, XSSFSheet sheet) {
        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);


        createCell(row, 0, "name", style, sheet);
        createCell(row, 1, "description", style, sheet);
        createCell(row, 2, "creation_date", style, sheet);
        createCell(row, 3, "modification_date", style, sheet);
        createCell(row, 4, "ID", style, sheet);


    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style, XSSFSheet sheet) {
        Cell cell = row.createCell(columnCount);
        if (value == null){value="no Value";}
            cell.setCellValue((String) value);
        cell.setCellStyle(style);
        sheet.autoSizeColumn(columnCount);
    }


    private void writeDataLines(XSSFWorkbook workbook, XSSFSheet sheet, List<? extends EntityModel> modelList) {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        int columnCount = 0;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        for (EntityModel model : modelList) {
            Row row = sheet.createRow(rowCount++);
            createCell(row, columnCount++, model.getName(), style, sheet);
            createCell(row, columnCount++, model.getDescription(), style, sheet);
            createCell(row, columnCount++, dateFormat.format(model.getDateCreate()), style, sheet);
            createCell(row, columnCount++, dateFormat.format(model.getDateModificate()), style, sheet);
            createCell(row, columnCount++, String.valueOf(model.getId()), style, sheet);
        }
    };
    public void export(HttpServletResponse response,List<? extends EntityModel> modelList) throws IOException {
        try(XSSFWorkbook workbook = new XSSFWorkbook();
            ServletOutputStream outputStream = response.getOutputStream();
            ){
            XSSFSheet sheet = workbook.createSheet();
            writeHeaderLine(workbook, sheet);
            writeDataLines(workbook, sheet, modelList);
            workbook.write(outputStream);
        }
    }
}