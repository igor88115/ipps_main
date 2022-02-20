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
        name = "queryFilter",
        condition = "name like :query"
)
@Where(clause = "status !='deleted'")
public class Locality extends EntityModel {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "districtId")
    private District districtId;
    @Column(length = 200)
    protected String name;
}