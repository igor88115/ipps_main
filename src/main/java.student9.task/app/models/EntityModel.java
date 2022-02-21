package app.models;

import app.util.Status;
import app.util.Views;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;



@MappedSuperclass
@Data
@FilterDef(
        name = "queryFilter",
        parameters = @ParamDef(name = "query", type = "string")
)
public class EntityModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.MainView.class)
    protected long id;
    @Column(updatable = false)
    @Temporal(TemporalType.DATE)
    protected Date dateCreate;
    @Temporal(TemporalType.DATE)
    protected Date dateModificate;
    @Temporal(TemporalType.DATE)
    protected Date dateRemove;
    protected Status status;

    public String getName() {
        return null;
    }

    public void setName(String name) {
    }
}