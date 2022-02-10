package app.controller;


import app.models.Region;
import app.repository.RegionRepository;
import app.services.LocalityService;
import app.services.PoiServiceExcel;
import app.services.PoiServiceWord;
import app.services.RegionService;
import com.fasterxml.jackson.annotation.JsonView;
import app.models.Views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("region")
public class RegionController extends BaseAbstractController {
    protected RegionService regionService;

    @Autowired
    public RegionController(RegionService regionService) {
            super(regionService);
            this.regionService = regionService;
        }

//    @GetMapping("/getpage/{pageNo}")
//    @JsonView(Views.MainView.class)
//    public List<Region> Page(@PathVariable(value = "pageNo") int pageNo) {
//        int pageSize = 2;
//        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
//        Page<Region> page = this.regionRepository.findAll(pageable);
//        List<Region> countryList = page.toList();
//        return countryList;
//    }

}
