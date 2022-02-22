package app.controller;

import app.models.Country;
import app.services.CountryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/country")
public class CountryController extends BaseAbstractController<Country, CountryService> {

    public CountryController(CountryService countryService) {
        super(countryService);
    }

}