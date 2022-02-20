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
        name = "queryFilter",
        condition = "name like :query")
@Where(clause = "status !='deleted'")
public class Country extends EntityModel {
    @OneToMany(mappedBy = "countryId", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Region> regionList;
    @JsonView({Views.MainView.class, Views.NameView.class})
    @Column(length = 20)
    protected String name;
}