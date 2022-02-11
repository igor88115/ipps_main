package app.controller;

import app.models.District;
import app.models.EntityModel;
import app.services.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/district")
public class DistrictController extends BaseAbstractController{
    protected DistrictService districtService;

    @Autowired
    public DistrictController(DistrictService districtService) {
        super(districtService);
        this.districtService = districtService;
    }

    @PostMapping
    public EntityModel create(@RequestBody District district){
        return districtService.create(district);
    };

    @PutMapping()
    public EntityModel update(@RequestBody District district) {
        return districtService.update(district);
    }

}