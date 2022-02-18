package app.controller;


import app.models.Country;
import app.models.DTOModel;
import app.models.Region;
import app.services.Mapper;
import app.services.RegionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/region")
public class RegionController extends BaseAbstractController<Region,RegionService> {

    public RegionController(RegionService regionService) {
        super(regionService);
    }

    @GetMapping("/by_country/{entity}")
    public ResponseEntity<List<DTOModel>> getLocalities(@PathVariable Optional<Country> entity) {
        if (entity.isPresent() && !Objects.equals(entity.get().getStatus(), "deleted")) {
            List<Region> regionList = entity.get().getRegionList();
            List<DTOModel> dtoModels = Mapper.listToDTO(regionList);
            return ResponseEntity.status(HttpStatus.OK).body(dtoModels);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}