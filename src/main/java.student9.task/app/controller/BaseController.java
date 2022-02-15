package app.controller;

import app.models.EntityModel;
import app.models.Views;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface BaseController<T extends EntityModel> {
    @JsonView(Views.MainView.class)
    @GetMapping
    public ResponseEntity<List<T>> list(@RequestParam(required = false) String name);

    @GetMapping("{id}")
    public ResponseEntity<Optional<T>> getById(@PathVariable("id") Long id);

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable("id") Long id);

    @GetMapping("/export/excel")
    public ResponseEntity<?> exportToExcel(HttpServletResponse response) throws IOException;

    @GetMapping("/export/word")
    public ResponseEntity<?> exportToWord(HttpServletResponse response) throws IOException;

    @GetMapping("page")
    public ResponseEntity<Page<T>> findPaginated(Pageable p);

    @PostMapping
    Object create(@RequestBody T model);

    @PutMapping
    Object update(@RequestBody T model);

}