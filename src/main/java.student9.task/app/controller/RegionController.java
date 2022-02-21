package app.controller;


import app.models.Country;
import app.dto.DTOModelView;
import app.models.Region;
import app.services.RegionService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
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

    @GetMapping("/by_country/{entity}")
    public ResponseEntity<List<DTOModelView>> getLocalities(@PathVariable Optional<Country> entity) {
        if (entity.isPresent()) {
            List<Region> regionList = entity.get().getRegionList();
            List<DTOModelView> dtoModelViewList
                    =  new ModelMapper().map(regionList, new TypeToken<List<DTOModelView>>() {}.getType());
            return ResponseEntity.status(HttpStatus.OK).body(dtoModelViewList);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}