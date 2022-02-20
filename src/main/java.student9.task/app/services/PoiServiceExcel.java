package app.services;

import app.models.DTOEntityModel;
import app.models.EntityModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
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
        createCell(row, 1, "creation_date", style, sheet);
        createCell(row, 2, "modification_date", style, sheet);
        createCell(row, 3, "ID", style, sheet);
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style, XSSFSheet sheet) {
        Cell cell = row.createCell(columnCount);
        if (value == null){value="no Value";}
        cell.setCellValue((String) value);
        cell.setCellStyle(style);
        sheet.autoSizeColumn(columnCount);
    }

    private void writeDataLines(XSSFWorkbook workbook, XSSFSheet sheet, List<DTOEntityModel> modelList) {
        int rowCount = 1;
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        int columnCount = 0;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (DTOEntityModel model : modelList) {
            Row row = sheet.createRow(rowCount++);
            createCell(row,0, model.getName(), style, sheet);
            createCell(row, 1, dateFormat.format(model.getDateCreate()), style, sheet);
            createCell(row, 2, dateFormat.format(model.getDateModificate()), style, sheet);
            createCell(row, 3, String.valueOf(model.getId()), style, sheet);
        }
    };

    public void export(HttpServletResponse response,List<? extends EntityModel> modelList) throws IOException {
        try(XSSFWorkbook workbook = new XSSFWorkbook();
            ServletOutputStream outputStream = response.getOutputStream();
        ){
            List<DTOEntityModel> dtoModelList
                    =  new ModelMapper().map(modelList, new TypeToken<List<DTOEntityModel>>() {}.getType());
            XSSFSheet sheet = workbook.createSheet();
            writeHeaderLine(workbook, sheet);
            writeDataLines(workbook, sheet, dtoModelList);
            workbook.write(outputStream);
        }
    }
}