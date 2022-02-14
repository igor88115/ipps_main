package app.controller;

import app.models.EntityModel;
import app.models.Views;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface BaseController<T extends EntityModel> {
    @JsonView(Views.MainView.class)
    @GetMapping
    public List list();
    @GetMapping("{id}")
    public Optional getById(@PathVariable("id") Long id);

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id);

    @GetMapping("/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException;

    @GetMapping("/export/word")
    public void exportToWord(HttpServletResponse response) throws IOException;
    @GetMapping("page")
    public Page<T> findPaginated(Pageable p);
    @PostMapping
    Object create(@RequestBody T model);
    @PutMapping
    Object update(@RequestBody T model);


//    @PostMapping
//    public T create(@RequestBody T model);
//
//    @PutMapping()
//    public T update(@RequestBody T model);


}