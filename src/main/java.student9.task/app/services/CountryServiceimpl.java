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

//    @Override
//    public ResponseEntity<List<Region>> getRegions(long id) {
//        List<Region> regions = this.mainRepository.getRegions(id);
//        if (regions.isEmpty() == false) return ResponseEntity.status(HttpStatus.OK).body(regions);
//        else return new ResponseEntity(HttpStatus.NOT_OK);
//    }
}