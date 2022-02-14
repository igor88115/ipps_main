package app.controller;

import app.models.EntityModel;
import app.services.EntityModelService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public List<T> list() {
        return this.baseEntityService.findAll();
    }

    public Optional getById(Long id) {
        return this.baseEntityService.findById(id);
    }

    @Override
    public void delete(Long id) {
        this.baseEntityService.delete(id);
    }

    @Override
    public void exportToExcel(HttpServletResponse response) throws IOException {
        this.baseEntityService.exportToExcel(response);
    }

    @Override
    public void exportToWord(HttpServletResponse response) throws IOException {
        this.baseEntityService.exportToWord(response);
    }

    @Override
    public Page<T> findPaginated(Pageable pageable) {
        return this.baseEntityService.findPages(pageable);
    }

    @Override
    public Object create(@RequestBody T model) {
        return baseEntityService.create(model);
    }
    @Override
    public Object update(@RequestBody T model) {
        return baseEntityService.update(model);
    }

}
