package app.repository;

import app.models.Country;
import app.models.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country, Long>, MainRepository<Country>{
    @Query("FROM Region WHERE countryId =:id AND status = 'good'")
    List<Region> getRegions(@Param("id") long id);


}
