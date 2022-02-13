package app.controller;


import app.models.EntityModel;
import app.models.Locality;
import app.models.Region;
import app.services.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/region")
public class RegionController extends BaseAbstractController {
    protected RegionService regionService;

    @Autowired
    public RegionController(RegionService regionService) {
        super(regionService);
        this.regionService = regionService;
    }

    @PostMapping
    public EntityModel create(@RequestBody Region region){
        return regionService.create(region);
    };

    @PutMapping()
    public EntityModel update(@RequestBody Locality locality) {
        return regionService.update(locality);
    }

}