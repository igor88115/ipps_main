package app.services;

import app.models.District;
import app.models.EntityModel;
import app.repository.DistrictRepository;
import org.springframework.stereotype.Service;

@Service
public class DistrictService extends EntityModelService{
    private DistrictRepository districtRepository;
    private District district;
    public DistrictService(DistrictRepository districtRepository) {
        super(districtRepository);

    }


}
