package app.controller;

import app.models.DTOModel;
import app.models.EntityModel;
import app.models.Region;
import app.util.Views;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface BaseController<T extends EntityModel> {

    @GetMapping
    ResponseEntity<List<DTOModel>> list(String name);

    @GetMapping("{entity}")
    public ResponseEntity <Optional<T>> getById(Optional<T> entity);


    @DeleteMapping("{id}")
    @JsonView(Views.MainView.class)
    public ResponseEntity delete(@PathVariable("id") Long id);

    @GetMapping("/export/excel")
    public ResponseEntity<?> exportToExcel(HttpServletResponse response) throws IOException;

    @GetMapping("/export/word")
    public ResponseEntity<?> exportToWord(HttpServletResponse response) throws IOException;

    @GetMapping("page")
    public ResponseEntity<Page<T>> findPaginated(Pageable p);

    @PostMapping
    @JsonView(Views.MainView.class)
    ResponseEntity<T> create(@RequestBody T model);

    @PutMapping
    @JsonView(Views.MainView.class)
    ResponseEntity<T> update(@RequestBody T model);

}