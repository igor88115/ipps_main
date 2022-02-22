package app.controller;

import app.dto.DTOModelView;
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
    ResponseEntity<List<DTOModelView>> list(@RequestParam(required = false, defaultValue = "") String query);

    @GetMapping("{entity}")
    public ResponseEntity<DTOModelView> getById(@PathVariable Optional<T> entity);

    @DeleteMapping("{entity}")
    public ResponseEntity delete(@PathVariable Optional<T> entity);

    @GetMapping("/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException;

    @GetMapping("/export/word")
    public void exportToWord(HttpServletResponse response) throws IOException;

    @GetMapping("page")
    public ResponseEntity<Page<DTOModelView>> findPaginated(Pageable p);

    @PostMapping
    ResponseEntity<DTOModelView> create(@RequestBody T model);

    @PutMapping
    ResponseEntity<DTOModelView> update(@RequestBody T model);

}