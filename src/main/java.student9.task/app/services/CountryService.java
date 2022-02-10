package app.services;

import app.models.District;
import app.repository.CountryRepository;
import app.repository.DistrictRepository;
import app.repository.MainRepository;
import org.springframework.stereotype.Service;

@Service
public class CountryService extends EntityModelService{
    private CountryRepository countryRepository;
    public CountryService(CountryRepository countryRepository) {
        super(countryRepository);

    }
}