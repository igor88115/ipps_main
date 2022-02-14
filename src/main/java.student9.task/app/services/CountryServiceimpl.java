package app.services;

import app.models.Region;
import app.repository.CountryRepository;
import app.repository.MainRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceimpl extends EntityModelService implements CountryService{
    private CountryRepository countryRepository;
    public CountryServiceimpl(CountryRepository countryRepository) {
        super(countryRepository);
        this.countryRepository = countryRepository;
    }

    public List<Region> getRegions(Long id) {
        return this.countryRepository.getRegions(id);
    }
}