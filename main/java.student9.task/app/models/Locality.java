package app.models;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "locality")
@ToString(of = {"id", "text"})
@EqualsAndHashCode(of = {"id"})
public class Locality extends EntityModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    private Long district_id;

    public Locality() {
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Long getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(Long district_id) {
        this.district_id = district_id;
    }
}