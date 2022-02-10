package app.controller;


import app.models.Locality;
import app.repository.LocalityRepository;
import app.services.*;
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
@RequestMapping("locality")
public class LocalityController extends BaseAbstractController {
    protected LocalityService localityService;

    @Autowired
    public LocalityController(LocalityService localityService) {
        super(localityService);
        this.localityService = localityService;
    }


//    @GetMapping("/getpage/{pageNo}")
//    @JsonView(Views.MainView.class)
//    public List<Locality> Page(@PathVariable(value = "pageNo") int pageNo) {
//        int pageSize = 2;
//        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
//        Page<Locality> page = this.localityRepository.findAll(pageable);
//        List<Locality> countryList = page.toList();
//        return countryList;
//    }

}
