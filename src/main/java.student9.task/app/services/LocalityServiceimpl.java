package app.services;

import app.models.Locality;
import app.repository.LocalityRepository;
import org.springframework.stereotype.Service;


@Service
public class LocalityServiceimpl extends EntityModelService {
    private LocalityRepository localityRepositoryy;
    private Locality locality;

    public LocalityServiceimpl(LocalityRepository localityRepository) {
        super(localityRepository);

    }
}
