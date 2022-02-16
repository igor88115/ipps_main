package app.services;

import app.models.District;
import app.models.Region;
import app.repository.RegionRepository;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RegionService extends EntityModelService<RegionRepository, Region>{

    ResponseEntity<List<District>> getDistricts(Long id);
}
