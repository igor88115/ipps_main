package app.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "district")
@ToString(of = {"id", "text"})
@EqualsAndHashCode(of = {"id"})
@Data
@Filter(
        name = "nameFilter",
        condition = "name like :name"
)
public class District extends EntityModel {

    @OneToMany(mappedBy = "districtId", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Where(clause = "status !='deleted'")
    private List<Locality> localityList;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "regionId")
    @Where(clause = "status !='deleted'")
    private Region regionId;
    @Column(length = 200)
    protected String name;

}