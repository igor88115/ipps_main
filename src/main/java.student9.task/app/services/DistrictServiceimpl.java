package app.services;

import app.models.District;
import app.models.Locality;
import app.repository.DistrictRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictServiceimpl extends EntityModelService{
    private DistrictRepository districtRepository;
    private District district;
    public DistrictServiceimpl(DistrictRepository districtRepository) {
        super(districtRepository);
        this.districtRepository = districtRepository;
    }

    public List<Locality> getLocalities(Long id) {
        return this.districtRepository.getLocalities(id);
    }
}