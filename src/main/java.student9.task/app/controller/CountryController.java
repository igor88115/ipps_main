package app.controller;

import app.services.*;
import com.fasterxml.jackson.annotation.JsonView;
import app.models.Country;
import app.models.Views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import app.repository.CountryRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/country")
public class CountryController  extends BaseAbstractController {
    protected CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        super(countryService);
        this.countryService = countryService;
    }

//
//    @GetMapping()
//    @JsonView(Views.MainView.class)
//    public List<Country> Page(@PathVariable(value = "pageNo") int pageNo) {
//        int pageSize = 2;
//        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
//        Page<Country> page = this.countryRepository.findAll(pageable);
//        List<Country> countryList = page.toList();
//        return countryList;
//
}
