package app.controller;

import app.models.District;
import app.models.EntityModel;
import app.services.DistrictService;
import app.services.PoiServiceWord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("district")
public class DistrictController extends BaseAbstractController{
    protected DistrictService districtService;
    @Autowired
    public DistrictController( DistrictService districtService) {
        super(districtService);
        this.districtService = districtService;
    }

    @Override
    public List<EntityModel> list() {
        return super.list();
    }

    @Override
    public Optional getById(Long id) {
        return super.getById(id);
    }

    @Override
    public District create(EntityModel model) {
        return (District) super.create(model);
    }
    //    @Override
//    public Du create(EntityModel model) {
//        return super.create(model);
//    }

    //    @PostMapping
//    @JsonView(Views.MainView.class)
//    public District create(@RequestBody District district) {
//        mainService.create(district);
//        return districtRepository.save(district);
//    }
//
//    @PutMapping("{id}")
//    public District update(@PathVariable("id") Long id, @RequestBody District district) {
//        return districtRepository.findById(id)
//                .map(districtDB -> {
//                    districtDB.setName(district.getName());
//                    districtDB.setDescription(district.getDescription());
//                    mainService.update(districtDB);
//                    return districtRepository.save(districtDB);
//                })
//                .orElseGet(() -> {
//                    district.setId(id);
//                    mainService.create(district);
//                    return districtRepository.save(district);
//                });
//    }
//
//    @DeleteMapping("{id}")
//    public void delete(@PathVariable("id") District district) {
//        mainService.delete(district);
//        districtRepository.save(district);
//    }
//
//    @GetMapping("/getpage/{pageNo}")
//    @JsonView(Views.MainView.class)
//    public List<District> Page(@PathVariable(value = "pageNo") int pageNo) {
//        int pageSize = 2;
//        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
//        Page<District> page = this.districtRepository.findAll(pageable);
//        List<District> countryList = page.toList();
//        return countryList;
//    }
//
//    @GetMapping("/export/excel")
//    public void exportToExcel(HttpServletResponse response) throws IOException {
//        response.setContentType("application/octet-stream");
//        String headerKey = "Content-Disposition";
//        String headerValue = "attachment; filename=districts" + ".xlsx";
//        response.setHeader(headerKey, headerValue);
//        List<District> districtList = this.districtRepository.findAll();
//        PoiServiceExcel poiService = new PoiServiceExcel(districtList);
//        poiService.export(response);
//    }
    @GetMapping("/export/word")
    public void exportToWord(HttpServletResponse response) throws IOException {
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=districts" + ".doc";
        response.setHeader(headerKey, headerValue);
        List<District> districtList =this.districtService.findAll();
        PoiServiceWord poiServiceWord = new PoiServiceWord(districtList);
        poiServiceWord.export(response);
    }
}
