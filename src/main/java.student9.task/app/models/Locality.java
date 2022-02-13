package app.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "locality")
@ToString(of = {"id", "text"})
@EqualsAndHashCode(of = {"id"})
@Data
public class Locality extends EntityModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    private Long districtId;
}