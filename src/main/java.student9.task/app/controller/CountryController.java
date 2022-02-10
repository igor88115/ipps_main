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


//    @PostMapping
//    @JsonView(Views.MainView.class)
//    public Country create(@RequestBody Country country) {
//        mainService.create(country);
//        return countryRepository.save(country);
//    }
//
//    @PutMapping
//    public Country update(@RequestBody Country country) {
//        return countryRepository.findById(country.getId())
//                .map(countryDB -> {
//                    countryDB.setName(country.getName());
//                    countryDB.setDescription(country.getDescription());
//                    mainService.update(countryDB);
//                    return countryRepository.save(countryDB);
//                })
//                .orElseGet(() -> {
//                    country.setId(id);
//                    mainService.create(country);
//                    return countryRepository.save(country);
//                });
//    }
//
//    @DeleteMapping("{id}")
//    public void delete(@PathVariable("id") Optional<Country> country) {
//        country.isPresent()
//        mainService.delete(country);
//        countryRepository.save(country);
//    }
//
//    @GetMapping()
//    @JsonView(Views.MainView.class)
//    public List<Country> Page(@PathVariable(value = "pageNo") int pageNo) {
//        int pageSize = 2;
//        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
//        Page<Country> page = this.countryRepository.findAll(pageable);
//        List<Country> countryList = page.toList();
//        return countryList;
//    }
//    @GetMapping("/export/excel")
//    public void exportToExcel(HttpServletResponse response) throws IOException {
//        response.setContentType("application/octet-stream");
//        String headerKey = "Content-Disposition";
//        String headerValue = "attachment; filename=countries" + ".xlsx";
//        response.setHeader(headerKey, headerValue);
//        List<Country> countryList = countryRepository.findAll();
//        PoiServiceExcel poiService = new PoiServiceExcel(countryList);
//        poiService.export(response);
//    }
//    @GetMapping("/export/word")
//    public void exportToWord(HttpServletResponse response) throws IOException {
//        String headerKey = "Content-Disposition";
//        String headerValue = "attachment; filename=countries" + ".doc";
//        response.setHeader(headerKey, headerValue);
//        List<Country> countryList =this.countryRepository.findAll();
//        PoiServiceWord poiServiceWord = new PoiServiceWord(countryList);
//        poiServiceWord.export(response);
//    }
}
