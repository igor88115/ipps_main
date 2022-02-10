package app.controller;

import app.models.EntityModel;
import app.services.EntityModelService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class BaseAbstractController<T extends EntityModel, S extends EntityModelService> implements BaseController<EntityModel> {

    S baseEntityService;

    public BaseAbstractController(S baseEntityService) {
        this.baseEntityService = baseEntityService;

    }

    public List<EntityModel> list() {
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
}
