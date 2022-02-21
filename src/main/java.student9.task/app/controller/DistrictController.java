package app.controller;

import app.dto.DTOModelView;
import app.models.District;
import app.models.Region;
import app.services.DistrictService;
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
@RequestMapping("/api/v1/district")
public class DistrictController extends BaseAbstractController<District, DistrictService>{


    public DistrictController(DistrictService districtService) {
        super(districtService);
    }

    @GetMapping("/by_region/{entity}")
    public ResponseEntity<List<DTOModelView>> getLocalities(@PathVariable Optional<Region> entity) {
        if (entity.isPresent()) {
            List<District> districtList = entity.get().getDistrictList();
            List<DTOModelView> dtoModelViewList
                    =  new ModelMapper().map(districtList, new TypeToken<List<DTOModelView>>() {}.getType());
            return ResponseEntity.status(HttpStatus.OK).body(dtoModelViewList);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}