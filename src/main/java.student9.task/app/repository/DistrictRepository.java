package app.repository;

import app.models.District;
import app.models.Locality;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Qualifier("district")
public interface DistrictRepository extends JpaRepository<District, Long>, MainRepository<District>{
    @Override
    List<District> findByStatus(String status);

    @Override
    <S extends District> S save(S entity);

    @Query("FROM Locality WHERE districtId =:id")
    List<Locality> getLocalities(@Param("id") Long id);

}
