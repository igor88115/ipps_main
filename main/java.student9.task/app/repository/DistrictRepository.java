package app.repository;

import app.models.District;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Qualifier("district")
public interface DistrictRepository extends JpaRepository<District, Long>, MainRepository<District>{
    @Override
    List<District> findByStatus(String status);

    @Override
    <S extends District> S save(S entity);
}
