package app.services;

import app.models.District;
import app.models.Locality;
import app.repository.DistrictRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictServiceimpl extends EntityModelServiceimpl<DistrictRepository, District> implements DistrictService {

    public DistrictServiceimpl(DistrictRepository districtRepository) {
        super(districtRepository);
    }
    @Override
    public List<Locality> getLocalities(long id) {
        return this.mainRepository.getLocalities(id);

    }
}