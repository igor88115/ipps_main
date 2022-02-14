package app.controller;

import app.models.District;
import app.models.EntityModel;
import app.models.Locality;
import app.models.Views;
import app.services.DistrictService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/getlocalities/{id}")
    public List<Locality> getLocalities(@PathVariable("id") Long id){return districtService.getLocalities(id);}

}