package app.repository;

import app.models.Country;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Qualifier("country")
public interface CountryRepository extends JpaRepository<Country, Long>, MainRepository<Country>{
    @Override
    List<Country> findByStatus(String status);

    @Override
    List<Country> findAll();
}
