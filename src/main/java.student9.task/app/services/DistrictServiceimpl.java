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
    private DistrictRepository districtRepository;

    public DistrictServiceimpl(DistrictRepository districtRepository) {
        super(districtRepository);
        this.districtRepository = districtRepository;
    }
    @Override
    public List<Locality> getLocalities(Long id) {
        return this.districtRepository.getLocalities(id);

    }
}