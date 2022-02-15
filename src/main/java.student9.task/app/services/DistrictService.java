package app.services;


import app.models.District;
import app.models.Locality;
import app.repository.DistrictRepository;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DistrictService extends EntityModelService<DistrictRepository, District>{
    ResponseEntity<List<Locality>> getLocalities(Long id);
}
