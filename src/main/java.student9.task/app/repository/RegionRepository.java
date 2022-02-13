package app.repository;

import app.models.Region;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Qualifier("region")
public interface RegionRepository extends JpaRepository<Region, Long>, MainRepository<Region>{

}
