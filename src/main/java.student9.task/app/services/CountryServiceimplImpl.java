package app.services;

import app.models.Region;
import app.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class CountryService extends EntityModelService im{
    private CountryRepository countryRepository;
    public CountryService(CountryRepository countryRepository) {
        super(countryRepository);
        this.countryRepository = countryRepository;
    }

    public List<Region> getRegions(Long id) {
        return this.countryRepository.getRegions(id);
    }
}