package app.services;

import app.models.District;
import app.models.Region;
import app.repository.RegionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegionServiceimpl extends EntityModelService implements RegionService{
    private RegionRepository regionRepository;
    private Region region;

    public RegionServiceimpl(RegionRepository regionRepository) {
        super(regionRepository);
        this.regionRepository = regionRepository;
    }

    public List<District> getDistricts(Long id) {
        Optional<Region> region = regionRepository.findByIdAndStatus(id, "good");
        return region.get().getDistrictList();
    }
}