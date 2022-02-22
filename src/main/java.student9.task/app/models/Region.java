package app.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Filter;

import javax.persistence.*;
import java.util.List;

import static app.util.Constants.CONDITION;
import static app.util.Constants.QUERYFILTER;


@Entity
@Table(name = "region")
@EqualsAndHashCode(of = {"id"})
@ToString(of = {"id", "text"})
@Data
@Filter(
        name = QUERYFILTER,
        condition = CONDITION)
public class Region extends EntityModel {
    @OneToMany(mappedBy = "regionId", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<District> districtList;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "countryId")
    private Country countryId;
    @Column(length = 200)
    protected String name;
}