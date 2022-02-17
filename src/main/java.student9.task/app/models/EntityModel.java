package app.models;

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
        name = "nameFilter",
        parameters = @ParamDef(name = "name", type = "string")
)
public class EntityModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.MainView.class)
    @Where(clause = "status !='deleted'")
    protected long id;
    @JsonView({Views.MainView.class, Views.NameView.class})
    protected String name;
    @JsonView(Views.MainView.class)
    protected String description;
    @Column(updatable = false)
    @Temporal(TemporalType.DATE)
    protected Date dateCreate;
    @Temporal(TemporalType.DATE)
    protected Date dateModificate;
    @Temporal(TemporalType.DATE)
    protected Date dateRemove;
    protected String status;
}