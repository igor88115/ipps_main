package app.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "country")
@ToString(of = {"id", "text"})
@EqualsAndHashCode(of = {"id"})
@Data
public class Country extends EntityModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @OneToMany(mappedBy = "countryId", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Region> regionList;
}