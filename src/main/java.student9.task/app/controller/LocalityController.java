package app.controller;


import app.models.DTOModel;
import app.models.District;
import app.models.Locality;
import app.services.LocalityService;
import app.services.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/v1/locality")
public class LocalityController extends BaseAbstractController<Locality, LocalityService> {
    protected LocalityService localityService;

    @Autowired
    public LocalityController(LocalityService localityService) {
        super(localityService);
    }

    @GetMapping("/by_district/{entity}")
    public ResponseEntity<List<DTOModel>> getLocalities(@PathVariable Optional<District> entity) {
        if (entity.isPresent() && !Objects.equals(entity.get().getStatus(), "deleted")) {
            List<Locality> localityList = entity.get().getLocalityList();
            List<DTOModel> dtoModels = Mapper.listToDTO(localityList);
            return ResponseEntity.status(HttpStatus.OK).body(dtoModels);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

}