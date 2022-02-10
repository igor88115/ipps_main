package app.models;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;


@Entity
@Table(name = "country")
@ToString(of = {"id", "text"})
@EqualsAndHashCode(of = {"id"})
public class Country extends EntityModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @JsonView(Views.MainView.class)
    protected String name;
    @JsonView(Views.MainView.class)
    protected String description;
    protected Date date_modificate;
    protected Date date_create;
    protected Date date_remove;
    @JsonView(Views.MainView.class)
    protected String status;
    @JsonView(Views.MainView.class)
    @OneToMany(mappedBy = "country_id", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Region> regionList;

    public Country() {
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Date getDate_modificate() {
        return date_modificate;
    }

    @Override
    public void setDate_modificate(Date date_modificate) {
        this.date_modificate = date_modificate;
    }

    @Override
    public Date getDate_create() {
        return date_create;
    }

    @Override
    public void setDate_create(Date date_create) {
        this.date_create = date_create;
    }

    @Override
    public Date getDate_remove() {
        return date_remove;
    }

    @Override
    public void setDate_remove(Date date_remove) {
        this.date_remove = date_remove;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
    }

    public List<Region> getRegionList() {
        return regionList;
    }

    public void setRegionList(List<Region> regionList) {
        this.regionList = regionList;
    }
}
