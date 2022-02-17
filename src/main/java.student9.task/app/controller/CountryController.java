package app.controller;

import app.models.Country;
import app.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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