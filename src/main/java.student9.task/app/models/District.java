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
@ToString(of = {"id", "text"})
@EqualsAndHashCode(of = {"id"})
@Data
@Filter(
        name = QUERYFILTER,
        condition = CONDITION)
public class District extends EntityModel {

    @OneToMany(mappedBy = "districtId", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Locality> localityList;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "regionId")
    private Region regionId;
    @Column(length = 200)
    protected String name;

}