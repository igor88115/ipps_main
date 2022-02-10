package app.controller;

import app.models.EntityModel;
import app.services.EntityModelService;

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
    public <T extends EntityModel> T create(T model) {
        System.out.println(model.getName());
        System.out.println(model.getClass());
        return (T) this.baseEntityService.create(model);
    }
}
