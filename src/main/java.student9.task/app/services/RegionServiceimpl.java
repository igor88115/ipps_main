package app.services;

import app.models.Region;
import app.repository.RegionRepository;
import org.springframework.stereotype.Service;

@Service
public class RegionServiceimpl extends EntityModelServiceimpl<RegionRepository, Region> implements RegionService{

    public RegionServiceimpl(RegionRepository regionRepository) {
        super(regionRepository);
    }

}