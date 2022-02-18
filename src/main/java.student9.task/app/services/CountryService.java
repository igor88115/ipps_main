package app.services;


import app.models.Country;
import app.models.Region;
import app.repository.CountryRepository;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CountryService extends EntityModelService<CountryRepository, Country>{

}
