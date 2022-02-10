package app.services;

import app.models.District;
import app.models.Locality;
import app.repository.DistrictRepository;
import app.repository.LocalityRepository;
import org.springframework.stereotype.Service;


@Service
public class LocalityService extends EntityModelService {
    private LocalityRepository localityRepositoryy;
    private Locality locality;

    public LocalityService(LocalityRepository localityRepository) {
        super(localityRepository);

    }
}