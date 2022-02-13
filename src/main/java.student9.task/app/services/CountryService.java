package app.services;

import app.repository.CountryRepository;
import org.springframework.stereotype.Service;

@Service
public class CountryService extends EntityModelService{
    private CountryRepository countryRepository;
    public CountryService(CountryRepository countryRepository) {
        super(countryRepository);
    }
}