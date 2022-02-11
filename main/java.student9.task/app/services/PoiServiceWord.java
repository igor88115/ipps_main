package app.services;

import app.models.EntityModel;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class PoiServiceWord{
    protected List<? extends EntityModel> entityList;

    public PoiServiceWord(List<? extends EntityModel> ModelList) {
        this.entityList = ModelList;
    }
    private void createCell(XWPFTableRow row, int pos, Object value){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        if (value == null){value="no Value";
        } else if (value instanceof Date) {
            value = dateFormat.format(value);
        } else if (value instanceof Long) {
            value = ((Long) value).toString();}
        row.getCell(pos).setText((String) value);
    }

    private void writeDataLines(XWPFDocument document) {
        XWPFTable table = document.createTable(entityList.size()+1, 5);
        int rownumber = 0;
        table.getRow(rownumber).getCell(0).setText("Name");
        table.getRow(rownumber).getCell(1).setText("Description");
        table.getRow(rownumber).getCell(2).setText("creation_date");
        table.getRow(rownumber).getCell(3).setText("modeification_date");
        table.getRow(rownumber).getCell(4).setText("ID");

        for (EntityModel model : entityList) {
            rownumber ++;
            XWPFTableRow row = table.getRow(rownumber);
            createCell(row, 0, model.getName());
            createCell(row, 1, model.getDescription());
            createCell(row, 2, model.getDate_create());
            createCell(row, 3, model.getDateModificate());
            createCell(row, 4, model.getId());
        }
    };

    public void export(HttpServletResponse response) throws IOException {
        XWPFDocument document = new XWPFDocument();
        writeDataLines(document);
        ServletOutputStream outputStream = response.getOutputStream();
        document.write(outputStream);
        document.close();
        outputStream.close();

    }
}