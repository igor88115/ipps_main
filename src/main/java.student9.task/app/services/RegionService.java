package app.services;

import app.models.District;
import app.models.Region;

import java.util.List;
import java.util.Optional;

public interface RegionService {

    public List<District> getDistricts(Long id);

}
