package app.controller;

import app.models.District;
import app.models.Locality;
import app.models.Region;
import app.models.Views;
import app.services.DistrictService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/district")
public class DistrictController extends BaseAbstractController<District, DistrictService>{
    protected DistrictService districtService;

    @Autowired
    public DistrictController(DistrictService districtService) {
        super(districtService);
        this.districtService = districtService;
    }

    @JsonView(Views.NameView.class)
    @GetMapping("/by_regiron/{entity}")
    public ResponseEntity<List<District>> getLocalities(@PathVariable Optional<Region> entity) {
        if (entity.isPresent()) {
            List<District> districtList = entity.get().getDistrictList();
            return ResponseEntity.status(HttpStatus.FOUND).body(districtList);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}