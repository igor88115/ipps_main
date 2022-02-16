package app.controller;


import app.models.District;
import app.models.Locality;
import app.models.Region;
import app.models.Views;
import app.services.LocalityService;
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
@RequestMapping("/api/v1/locality")
public class LocalityController extends BaseAbstractController<Locality, LocalityService> {
    protected LocalityService localityService;

    @Autowired
    public LocalityController(LocalityService localityService) {
        super(localityService);
        this.localityService = localityService;
    }
    @JsonView(Views.NameView.class)
    @GetMapping("/by_distict/{entity}")
    public ResponseEntity<List<Locality>> getLocalities(@PathVariable Optional<District> entity) {
        if (entity.isPresent()) {
            List<Locality> localityList = entity.get().getLocalityList();
            return ResponseEntity.status(HttpStatus.FOUND).body(localityList);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}