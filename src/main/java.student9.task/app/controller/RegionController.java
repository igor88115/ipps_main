package app.controller;


import app.models.*;
import app.services.RegionService;
import app.util.Views;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/region")
public class RegionController extends BaseAbstractController<Region,RegionService> {

    public RegionController(RegionService regionService) {
        super(regionService);
    }

    @JsonView(Views.NameView.class)
    @GetMapping("/by_country/{entity}")
    public ResponseEntity<List<Region>> getLocalities(@PathVariable Optional<Country> entity) {
        if (entity.isPresent()) {
            List<Region> regionList = entity.get().getRegionList();
            return ResponseEntity.status(HttpStatus.FOUND).body(regionList);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}