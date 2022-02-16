package app.services;

import app.models.Locality;
import app.repository.LocalityRepository;
import org.springframework.stereotype.Service;


@Service
public class LocalityServiceimpl extends EntityModelServiceimpl<LocalityRepository, Locality>implements LocalityService{

    public LocalityServiceimpl(LocalityRepository localityRepositoryy) {
        super(localityRepositoryy);
    }
}
