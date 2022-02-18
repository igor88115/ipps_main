package app.models;

import app.util.Views;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;


@Entity
@ToString(of = {"id", "text"})
@EqualsAndHashCode(of = {"id"})
@Data
@Filter(
        name = "nameFilter",
        condition = "name like :name")
public class Country extends EntityModel {
    @OneToMany(mappedBy = "countryId", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Where(clause = "status !='deleted'")
    private List<Region> regionList;
    @JsonView({Views.MainView.class, Views.NameView.class})
    @Column(length = 20)
    protected String name;
}