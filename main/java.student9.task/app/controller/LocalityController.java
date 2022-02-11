package app.controller;


import app.models.District;
import app.models.EntityModel;
import app.models.Locality;
import app.services.LocalityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/locality")
public class LocalityController extends BaseAbstractController {
    protected LocalityService localityService;

    @Autowired
    public LocalityController(LocalityService localityService) {
        super(localityService);
        this.localityService = localityService;
    }

    @PostMapping
    public EntityModel create(@RequestBody Locality locality){
        return localityService.create(locality);
    };

    @PutMapping()
    public EntityModel update(@RequestBody Locality locality) {
        return localityService.update(locality);
    }

}