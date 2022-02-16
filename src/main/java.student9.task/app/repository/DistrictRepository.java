package app.repository;

import app.models.District;
import app.models.Locality;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DistrictRepository extends JpaRepository<District, Long>, MainRepository<District>{

    @Query("FROM Locality WHERE districtId =:id AND status = 'good'")
    List<Locality> getLocalities(@Param("id") Long id);

}
