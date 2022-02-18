package app.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTOModel {
    private long id;
    private String name;
    private String description;
    private Date dateCreate;
    private Date dateModificate;
}
