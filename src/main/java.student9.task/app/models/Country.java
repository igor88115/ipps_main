package app.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;


@Entity
@ToString(of = {"id", "text"})
@EqualsAndHashCode(of = {"id"})
@Data
@FilterDef(
        name = "nameFilter",
        parameters = @ParamDef(name = "name", type = "string")
)
@Filter(
        name = "nameFilter",
        condition = "name like :name"
)
public class Country extends EntityModel {
    @OneToMany(mappedBy = "countryId", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Where(clause = "status !='deleted'")
    private List<Region> regionList;
}