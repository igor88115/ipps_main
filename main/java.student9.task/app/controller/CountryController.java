package app.controller;

import app.models.Country;
import app.models.EntityModel;
import app.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/country")
public class CountryController extends BaseAbstractController {
    protected CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        super(countryService);
        this.countryService = countryService;
    }

    @PostMapping
    public EntityModel create(@RequestBody Country country){
        return countryService.create(country);
    };

    @PutMapping()
    public EntityModel update(@RequestBody Country country) {
        return countryService.update(country);
    }
}