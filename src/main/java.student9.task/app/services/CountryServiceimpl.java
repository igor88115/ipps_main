package app.services;

import app.models.Country;
import app.repository.CountryRepository;
import org.springframework.stereotype.Service;

@Service
public class CountryServiceimpl extends EntityModelServiceimpl<CountryRepository, Country> implements CountryService{

    public CountryServiceimpl(CountryRepository countryRepository) {
        super(countryRepository);
    }

}