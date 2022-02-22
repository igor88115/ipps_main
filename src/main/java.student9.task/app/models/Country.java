package app.models;

import app.util.Views;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Filter;

import javax.persistence.*;
import java.util.List;

import static app.util.Constants.CONDITION;
import static app.util.Constants.QUERYFILTER;


@Entity
@ToString(of = {"id", "text"})
@EqualsAndHashCode(of = {"id"})
@Data
@Filter(
        name = QUERYFILTER,
        condition = CONDITION)
public class Country extends EntityModel {
    @OneToMany(mappedBy = "countryId", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Region> regionList;
    @JsonView({Views.MainView.class, Views.NameView.class})
    @Column(length = 20)
    protected String name;
}