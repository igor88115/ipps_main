package app.services;

import app.models.DTOEntityModel;
import app.models.DTOModel;
import app.models.EntityModel;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
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

    private void createCell(XWPFTableRow row, int pos, Object value){
        if (value == null){value="no Value";}
        row.getCell(pos).setText(String.valueOf(value));
    }

    private void writeDataLines(XWPFDocument document, List<DTOEntityModel> entityList) {
        XWPFTable table = document.createTable(entityList.size()+1, 5);
        int rownumber = 0;
        table.getRow(rownumber).getCell(0).setText("Name");
        table.getRow(rownumber).getCell(1).setText("creation_date");
        table.getRow(rownumber).getCell(2).setText("modification_date");
        table.getRow(rownumber).getCell(3).setText("ID");

        for (DTOEntityModel model : entityList) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            rownumber ++;
            XWPFTableRow row = table.getRow(rownumber);
            createCell(row, 0, model.getName());
            createCell(row, 1, dateFormat.format(model.getDateCreate()));
            createCell(row, 2, dateFormat.format(model.getDateModificate()));
            createCell(row, 3, String.valueOf(model.getId()));
        }
    };

    public void export(HttpServletResponse response, List<? extends EntityModel> entityList)throws IOException{
        try(XWPFDocument document = new XWPFDocument();
            ServletOutputStream outputStream = response.getOutputStream();){
            List<DTOEntityModel> dtoModelList
                    =  new ModelMapper().map(entityList, new TypeToken<List<DTOEntityModel>>() {}.getType());
            writeDataLines(document, dtoModelList);
            document.write(outputStream);
        }
    }
}