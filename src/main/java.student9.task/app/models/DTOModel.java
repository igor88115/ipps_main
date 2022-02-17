package app.models;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;


@Data
public class DTOModel {
    private long id;
    private String name;
    private String description;
    private Date dateCreate;
    private Date dateModificate;

    public DTOModel(long id, String name, String description, Date dateCreate, Date dateModificate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dateCreate = dateCreate;
        this.dateModificate = dateModificate;
    }
}
