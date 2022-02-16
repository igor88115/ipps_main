package app.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "region")
@ToString(of = {"id", "text"})
@EqualsAndHashCode(of = {"id"})
@Data
@Where(clause = "status !='deleted'")
public class Region extends EntityModel {
    @OneToMany(mappedBy = "regionId", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<District> districtList;
    private Long countryId;
}
