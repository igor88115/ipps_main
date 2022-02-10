//package app.controller;
//
//
//import app.models.Locality;
//import app.repository.LocalityRepository;
//import app.services.MainService;
//import app.services.PoiServiceExcel;
//import app.services.PoiServiceWord;
//import com.fasterxml.jackson.annotation.JsonView;
//import app.models.Views;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.List;
//
//@RestController
//@RequestMapping("locality")
//public class LocalityController {
//
//    private final LocalityRepository localityRepository;
//    private final MainService mainService;
//
//    @Autowired
//    public LocalityController(@Qualifier("locality") LocalityRepository localityRepository, MainService mainService) {
//        this.localityRepository = localityRepository;
//        this.mainService = mainService;
//    }
//
//    @GetMapping
//    @JsonView(Views.MainView.class)
//    public List<Locality> list() {
//        return localityRepository.findAll();
//    }
//
//
//    @GetMapping("{id}")
//    @JsonView(Views.MainView.class)
//    public Locality getOne(@PathVariable("id") Locality locality) {
//        return locality;
//    }
//
//    @PostMapping
//    @JsonView(Views.MainView.class)
//    public Locality create(@RequestBody Locality locality) {
//        mainService.create(locality);
//        return localityRepository.save(locality);
//    }
//
//    @PutMapping("{id}")
//    public Locality update(@PathVariable("id") Long id, @RequestBody Locality locality) {
//        return localityRepository.findById(id)
//                .map(localityDB -> {
//                    localityDB.setName(locality.getName());
//                    localityDB.setDescription(locality.getDescription());
//                    mainService.update(localityDB);
//                    return localityRepository.save(localityDB);
//                })
//                .orElseGet(() -> {
//                    locality.setId(id);
//                    mainService.create(locality);
//                    return localityRepository.save(locality);
//                });
//    }
//
//    @DeleteMapping("{id}")
//    public void delete(@PathVariable("id") Locality locality) {
//        mainService.delete(locality);
//        localityRepository.save(locality);
//    }
//
//    @GetMapping("/getpage/{pageNo}")
//    @JsonView(Views.MainView.class)
//    public List<Locality> Page(@PathVariable(value = "pageNo") int pageNo) {
//        int pageSize = 2;
//        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
//        Page<Locality> page = this.localityRepository.findAll(pageable);
//        List<Locality> countryList = page.toList();
//        return countryList;
//    }
//    @GetMapping("/export/excel")
//    public void exportToExcel(HttpServletResponse response) throws IOException {
//        response.setContentType("application/octet-stream");
//        String headerKey = "Content-Disposition";
//        String headerValue = "attachment; filename=locality" + ".xlsx";
//        response.setHeader(headerKey, headerValue);
//        List<Locality> localityList = this.localityRepository.findAll();
//        PoiServiceExcel poiService = new PoiServiceExcel(localityList);
//        poiService.export(response);
//    }
//    @GetMapping("/export/word")
//    public void exportToWord(HttpServletResponse response) throws IOException {
//        String headerKey = "Content-Disposition";
//        String headerValue = "attachment; filename=locality" + ".doc";
//        response.setHeader(headerKey, headerValue);
//        List<Locality> localityList =this.localityRepository.findAll();
//        PoiServiceWord poiServiceWord = new PoiServiceWord(localityList);
//        poiServiceWord.export(response);
//    }
//}
