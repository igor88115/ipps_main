//package app.controller;
//
//
//import app.models.Region;
//import app.repository.RegionRepository;
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
//@RequestMapping("region")
//public class RegionController {
//
//    private final RegionRepository regionRepository;
//    private final MainService mainService;
//
//    @Autowired
//    public RegionController(@Qualifier("region") RegionRepository regionRepository, MainService mainService) {
//        this.regionRepository = regionRepository;
//        this.mainService = mainService;
//    }
//
//    @GetMapping
//    @JsonView(Views.MainView.class)
//    public List<Region> list() {
//        return regionRepository.findAll();
//    }
//
//
//    @GetMapping("{id}")
//    @JsonView(Views.MainView.class)
//    public Region getOne(@PathVariable("id") Region region) {
//        return region;
//    }
//
//    @PostMapping
//    @JsonView(Views.MainView.class)
//    public Region create(@RequestBody Region region) {
//        mainService.create(region);
//        return regionRepository.save(region);
//    }
//
//    @PutMapping("{id}")
//    public Region update(@PathVariable("id") Long id, @RequestBody Region region) {
//        return regionRepository.findById(id)
//                .map(regionDB -> {
//                    regionDB.setName(region.getName());
//                    regionDB.setDescription(region.getDescription());
//                    mainService.update(regionDB);
//                    return regionRepository.save(regionDB);
//                })
//                .orElseGet(() -> {
//                    region.setId(id);
//                    mainService.create(region);
//                    return regionRepository.save(region);
//                });
//    }
//
//    @DeleteMapping("{id}")
//    public void delete(@PathVariable("id") Region region) {
//        mainService.delete(region);
//        regionRepository.save(region);
//    }
//
//    @GetMapping("/getpage/{pageNo}")
//    @JsonView(Views.MainView.class)
//    public List<Region> Page(@PathVariable(value = "pageNo") int pageNo) {
//        int pageSize = 2;
//        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
//        Page<Region> page = this.regionRepository.findAll(pageable);
//        List<Region> countryList = page.toList();
//        return countryList;
//    }
//
//    @GetMapping("/export/excel")
//    public void exportToExcel(HttpServletResponse response) throws IOException {
//        response.setContentType("application/octet-stream");
//        String headerKey = "Content-Disposition";
//        String headerValue = "attachment; filename=regions" + ".xlsx";
//        response.setHeader(headerKey, headerValue);
//        List<Region> regionList = regionRepository.findAll();
//        PoiServiceExcel poiService = new PoiServiceExcel(regionList);
//        poiService.export(response);
//    }
//    @GetMapping("/export/word")
//    public void exportToWord(HttpServletResponse response) throws IOException {
//        String headerKey = "Content-Disposition";
//        String headerValue = "attachment; filename=regions" + ".doc";
//        response.setHeader(headerKey, headerValue);
//        List<Region> regionList =this.regionRepository.findAll();
//        PoiServiceWord poiServiceWord = new PoiServiceWord(regionList);
//        poiServiceWord.export(response);
//    }
//}
