package app.controller;

import app.models.Country;
import app.models.Region;
import app.models.Views;
import app.services.CountryService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/country")
public class CountryController extends BaseAbstractController<Country, CountryService> {
    protected CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        super(countryService);
        this.countryService = countryService;
    }
}