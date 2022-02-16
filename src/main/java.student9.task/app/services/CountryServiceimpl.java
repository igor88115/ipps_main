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
    private CountryRepository countryRepository;


    public CountryServiceimpl(CountryRepository countryRepository) {
        super(countryRepository);
        this.countryRepository = countryRepository;
    }

    @Override
    public ResponseEntity<List<Region>> getRegions(Long id) {
        List<Region> regions = this.countryRepository.getRegions(id);
        if (regions.isEmpty() == false) return ResponseEntity.status(HttpStatus.OK).body(regions);
        else return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}