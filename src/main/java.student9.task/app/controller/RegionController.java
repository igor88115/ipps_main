package app.controller;


import app.models.*;
import app.services.RegionService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/region")
public class RegionController extends BaseAbstractController<Region,RegionService> {
    protected RegionService regionService;

    @Autowired
    public RegionController(RegionService regionService) {
        super(regionService);
        this.regionService = regionService;
    }

    @JsonView(Views.NameView.class)
    @GetMapping("/getdistricts/{id}")
    public List<District> getLocalities(@PathVariable("id") Long id){return this.regionService.getDistricts(id);}
}