package app.controller;

import app.models.EntityModel;
import app.services.EntityModelService;
import app.services.EntityModelServiceimpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class BaseAbstractController<T extends EntityModel, S extends EntityModelService> implements BaseController<T> {
    T entity;
    S baseEntityService;

    public BaseAbstractController(S baseEntityService) {
        this.baseEntityService = baseEntityService;
    }
    @Override
    public ResponseEntity<List<T>> list(String name) {
        return this.baseEntityService.findAll(name);
    }


    @Override
    public ResponseEntity<Optional<T>> getById(Long id) {
        return this.baseEntityService.findById(id);
    }

    @Override
    public ResponseEntity delete(Long id) {
        return this.baseEntityService.delete(id);
    }



    @Override
    public ResponseEntity<?> exportToExcel(HttpServletResponse response) throws IOException {
        return this.baseEntityService.exportToExcel(response);
    }

    @Override
    public ResponseEntity<?> exportToWord(HttpServletResponse response) throws IOException {
        return  this.baseEntityService.exportToWord(response);
    }

    @Override
    public ResponseEntity<Page<T>> findPaginated(Pageable pageable) {
        return this.baseEntityService.findPages(pageable);
    }

    @Override
    public ResponseEntity<T> create(@RequestBody T model) {
        return baseEntityService.create(model);
    }
    @Override
    public ResponseEntity<T> update(@RequestBody T model) {
        return baseEntityService.update(model);
    }

}
