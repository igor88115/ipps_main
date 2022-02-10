package app.repository;

import app.models.Locality;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Qualifier("locality")
public interface LocalityRepository extends JpaRepository<Locality, Long>, MainRepository<Locality>{

}