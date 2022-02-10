package app.models;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.sql.Date;


@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class EntityModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.MainView.class)
    private String name;
    @JsonView(Views.MainView.class)
    private String description;
    protected Long id;
    @Column(updatable = false)
    protected Date date_create;
    protected Date date_modificate;
    protected Date date_remove;
    protected String status;

    public EntityModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate_create() {
        return date_create;
    }

    public void setDate_create(Date date_create) {
        this.date_create = date_create;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate_modificate() {
        return date_modificate;
    }

    public void setDate_modificate(Date date_modificate) {
        this.date_modificate = date_modificate;
    }

    public Date getDate_remove() {
        return date_remove;
    }

    public void setDate_remove(Date date_remove) {
        this.date_remove = date_remove;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
