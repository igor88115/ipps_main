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
@Filter(
        name = "nameFilter",
        condition = "name like :name"
)
public class Locality extends EntityModel {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "districtId")
    @Where(clause = "status !='deleted'")
    private District districtId;
    @Column(length = 200)
    protected String name;
}