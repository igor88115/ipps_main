package app.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "district")
@ToString(of = {"id", "text"})
@EqualsAndHashCode(of = {"id"})
@Data
@Where(clause = "status !='deleted'")
public class District extends EntityModel {

    @OneToMany(mappedBy = "districtId", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Locality> localityList;
    private Long regionId;

}