package app.repository;

import app.models.Locality;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Qualifier("locality")
public interface LocalityRepository extends JpaRepository<Locality, Long>, MainRepository<Locality>{

}