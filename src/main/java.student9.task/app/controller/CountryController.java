package app.controller;

import app.models.*;
import app.services.CountryService;
import app.services.CountryServiceimpl;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/country")
public class CountryController extends BaseAbstractController<Country, CountryService> {
    protected CountryService countryService;
    protected Country country;

    @Autowired
    public CountryController(CountryService countryService) {
        super(countryService);
        this.countryService = countryService;
    }

    @JsonView(Views.NameView.class)
    @GetMapping("/getregions/{id}")
    public ResponseEntity<List<Region>> getRegions(@PathVariable("id") Long id){return countryService.getRegions(id);}
}