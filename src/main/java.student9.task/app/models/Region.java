package app.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "region")
@ToString(of = {"id", "text"})
@EqualsAndHashCode(of = {"id"})
@Data
@Filter(
        name = "nameFilter",
        condition = "name like :name"
)
public class Region extends EntityModel {
    @Where(clause = "status !='deleted'")
    @OneToMany(mappedBy = "regionId", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<District> districtList;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "countryId")
    @Where(clause = "status !='deleted'")
    private Country countryId;
    @Column(length = 200)
    protected String name;
}