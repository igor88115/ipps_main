package app.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "locality")
@ToString(of = {"id", "text"})
@EqualsAndHashCode(of = {"id"})
@Data
@Where(clause = "status !='deleted'")
@Filter(
        name = "nameFilter",
        condition = "name like :name"
)
public class Locality extends EntityModel {

    private Long districtId;
}