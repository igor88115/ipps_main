package app.repository;

import app.models.District;
import org.springframework.stereotype.Repository;


@Repository
public interface DistrictRepository extends MainRepository<District>{

//    @Query("FROM Locality WHERE districtId =:id AND status = 'good'")
//    List<Locality> getLocalities(@Param("id") long id);

}
