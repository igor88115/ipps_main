package app.services;


import app.models.Country;
import app.models.EntityModel;
import app.models.Region;
import app.repository.CountryRepository;
import app.repository.MainRepository;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CountryService extends EntityModelService<CountryRepository, Country>{

    ResponseEntity<List<Region>> getRegions(Long id);
}
