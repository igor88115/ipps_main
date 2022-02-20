package app.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTOEntityModel{
    private long id;
    private String name;
    private  Date dateCreate;
    private Date dateModificate;
    private  Date dateRemove;
    private String status;
}
