package app.models;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;


//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@MappedSuperclass
@Data
public class EntityModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.MainView.class)
    protected Long id;
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