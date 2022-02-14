package app.repository;

import app.models.Country;
import app.models.Locality;
import app.models.Region;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

public interface CountryRepository extends JpaRepository<Country, Long>, MainRepository<Country>{
    @Query("FROM Region WHERE countryId =:id AND status = 'good'")
    List<Region> getRegions(@Param("id") Long id);
}
