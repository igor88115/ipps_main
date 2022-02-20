package app.services;

import app.models.Country;
import app.models.Region;
import app.repository.CountryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceimpl extends EntityModelServiceimpl<CountryRepository, Country> implements CountryService{

    public CountryServiceimpl(CountryRepository countryRepository) {
        super(countryRepository);
    }

}