package app.controller;

import app.models.EntityModel;
import app.services.EntityModelService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class BaseAbstractController<T extends EntityModel, S extends EntityModelService> implements BaseController<EntityModel> {

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
    public Page<EntityModel> findPaginated(Pageable pageable) {
        return this.baseEntityService.findPages(pageable);
    }

//    @PostMapping
//    public EntityModel create(T model) {
//        return this.baseEntityService.create(model);
//    }
//
//    @PutMapping()
//    public EntityModel update(T model) {
//        return this.baseEntityService.update(model);
//    }
}
