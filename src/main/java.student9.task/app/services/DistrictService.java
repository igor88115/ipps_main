package app.services;

import app.models.District;
import app.models.EntityModel;
import app.repository.DistrictRepository;
import app.repository.MainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DistrictService extends EntityModelService{
    private DistrictRepository districtRepository;
    private District district;
    public DistrictService(DistrictRepository districtRepository) {
        super(districtRepository);

    }

    @Override
    public EntityModel create(EntityModel model) {
        S save = districtRepository.save(model);
        return super.create(model);
    }
}
