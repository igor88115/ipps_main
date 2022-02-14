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
    public PoiServiceExcel() {
    }

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
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value == null){value="no Value";}
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }


    private void writeDataLines(XSSFWorkbook workbook, XSSFSheet sheet, List<? extends EntityModel> modelList) {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        for (EntityModel model : modelList) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            createCell(row, columnCount++, model.getName(), style, sheet);
            createCell(row, columnCount++, model.getDescription(), style, sheet);
            createCell(row, columnCount++, dateFormat.format(model.getDateCreate()), style, sheet);
            createCell(row, columnCount++, dateFormat.format(model.getDateModificate()), style, sheet);
            createCell(row, columnCount++, model.getId().intValue(), style, sheet);
        }
    };
    public void export(HttpServletResponse response,List<? extends EntityModel> modelList) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet();
        writeHeaderLine(workbook, sheet);
        writeDataLines(workbook, sheet, modelList);
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}