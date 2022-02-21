package app.models;

import app.util.Views;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import javax.persistence.*;
import java.util.Date;

import static app.util.Constants.QUERY;
import static app.util.Constants.QUERYFILTER;


@MappedSuperclass
@Data
@FilterDef(
        name = QUERYFILTER,
        parameters = @ParamDef(name = QUERY, type = "string")
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