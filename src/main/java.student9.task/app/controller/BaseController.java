package app.controller;

import app.models.DTOModel;
import app.models.EntityModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface BaseController<T extends EntityModel> {

    @GetMapping
    ResponseEntity<List<DTOModel>> list(@RequestParam(required = false, defaultValue = "") String query);

    @GetMapping("{entity}")
    public ResponseEntity<DTOModel> getById(@PathVariable Optional<T> entity);


    @DeleteMapping("{entity}")
    public ResponseEntity delete(@PathVariable Optional<T> entity);

    @GetMapping("/export/excel")
    public ResponseEntity<?> exportToExcel(HttpServletResponse response) throws IOException;

    @GetMapping("/export/word")
    public ResponseEntity<?> exportToWord(HttpServletResponse response) throws IOException;

    @GetMapping("page")
    public ResponseEntity<Page<T>> findPaginated(Pageable p);

    @PostMapping
    ResponseEntity<DTOModel> create(@RequestBody T model);

    @PutMapping
    ResponseEntity<DTOModel> update(@RequestBody T model);

}