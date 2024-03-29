package app.controller;


import app.dto.DTOModelView;
import app.models.District;
import app.models.Locality;
import app.services.LocalityService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
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
    }

    @GetMapping("/by_district/{entity}")
    public ResponseEntity<List<DTOModelView>> getLocalities(@PathVariable Optional<District> entity) {
        if (entity.isPresent()) {
            List<Locality> localityList = entity.get().getLocalityList();
            List<DTOModelView> dtoModelViewList
                    =  new ModelMapper().map(localityList, new TypeToken<List<DTOModelView>>() {}.getType());
            return ResponseEntity.status(HttpStatus.OK).body(dtoModelViewList);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

}