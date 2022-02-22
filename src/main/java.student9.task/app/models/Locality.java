package app.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Filter;

import javax.persistence.*;

import static app.util.Constants.CONDITION;
import static app.util.Constants.QUERYFILTER;

@Entity
@Table(name = "locality")
@ToString(of = {"id", "text"})
@EqualsAndHashCode(of = {"id"})
@Data
@Filter(
        name = QUERYFILTER,
        condition = CONDITION)
public class Locality extends EntityModel {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "districtId")
    private District districtId;
    @Column(length = 200)
    protected String name;
}