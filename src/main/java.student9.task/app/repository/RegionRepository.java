package app.repository;

import app.models.District;
import app.models.Region;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


@Qualifier("region")
public interface RegionRepository extends JpaRepository<Region, Long>, MainRepository<Region>{
    Optional<Region> findByIdAndStatus(Long id, String good);
}
