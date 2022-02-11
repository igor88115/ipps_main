package app.models;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "region")
@ToString(of = {"id", "text"})
@EqualsAndHashCode(of = {"id"})
public class Region extends EntityModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @OneToMany(mappedBy = "region_id", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<District> districtList;
    private Long country_id;

    public Region() {
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public List<District> getDistrictList() {
        return districtList;
    }

    public void setDistrictList(List<District> districtList) {
        this.districtList = districtList;
    }

    public Long getCountry_id() {
        return country_id;
    }

    public void setCountry_id(Long country_id) {
        this.country_id = country_id;
    }
}

