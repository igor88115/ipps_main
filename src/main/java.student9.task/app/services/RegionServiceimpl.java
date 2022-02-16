package app.services;

import app.models.District;
import app.models.Region;
import app.repository.RegionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegionServiceimpl extends EntityModelServiceimpl<RegionRepository, Region> implements RegionService{
    private RegionRepository regionRepository;

    public RegionServiceimpl(RegionRepository regionRepository) {
        super(regionRepository);
        this.regionRepository = regionRepository;
    }
    @Override
    public ResponseEntity<List<District>> getDistricts(Long id) {
        Optional<Region> region = regionRepository.findByIdAndStatus(id, "good");
        if (region.isPresent()) return ResponseEntity.status(HttpStatus.OK).body(region.get().getDistrictList());
        else return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}