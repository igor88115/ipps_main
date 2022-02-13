package app.services;

import app.models.Region;
import app.repository.RegionRepository;
import org.springframework.stereotype.Service;

@Service
public class RegionService extends EntityModelService {
    private RegionRepository regionRepository;
    private Region region;

    public RegionService(RegionRepository regionRepository) {
        super(regionRepository);

    }
}