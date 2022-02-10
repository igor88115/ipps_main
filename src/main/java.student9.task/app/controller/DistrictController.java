package app.controller;

import app.models.District;
import app.models.EntityModel;
import app.models.Views;
import app.services.DistrictService;
import app.services.PoiServiceExcel;
import app.services.PoiServiceWord;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        List list = super.list();
        System.out.println(list.get(0).getClass());
        return super.list();
    }

    @Override
    public Optional getById(Long id) {
        return super.getById(id);
    }

    @PostMapping
    @JsonView(Views.MainView.class)
    public EntityModel create(@RequestBody District district){
        return districtService.create(district);
    };
    @PutMapping()
    public District update(@RequestBody District district) {
        return (District) districtService.update(district);
    }

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
    @GetMapping("/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=districts" + ".xlsx";
        response.setHeader(headerKey, headerValue);
        List<District> districtList = this.baseEntityService.findAll();
        PoiServiceExcel poiService = new PoiServiceExcel(districtList);
        poiService.export(response);
    }
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
