package app.services;

import app.models.District;
import app.repository.DistrictRepository;
import org.springframework.stereotype.Service;

@Service
public class DistrictServiceimpl extends EntityModelServiceimpl<DistrictRepository, District> implements DistrictService {

    public DistrictServiceimpl(DistrictRepository districtRepository) {
        super(districtRepository);
    }

}