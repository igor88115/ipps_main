package app.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "region")
@ToString(of = {"id", "text"})
@EqualsAndHashCode(of = {"id"})
@Data
@Filter
public class Region extends EntityModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @OneToMany(mappedBy = "regionId", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<District> districtList;
    private Long countryId;

}
