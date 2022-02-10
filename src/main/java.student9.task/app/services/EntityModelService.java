package app.services;

import app.models.EntityModel;
import app.repository.MainRepository;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;


public class EntityModelService <S extends MainRepository> {

    protected S mainRepository;


    public EntityModelService(S mainRepository) {
        this.mainRepository = mainRepository;
    }

    public <T extends EntityModel> List<T> findAll() {
        return mainRepository.findAll();
    }

    public Optional findById(Long id) {
        return mainRepository.findById(id);
    }


    public <T extends EntityModel> T create(T model) {
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        model.setDate_create(sqlDate);
        model.setDate_modificate(sqlDate);
        model.setStatus("good");
        System.out.println(model.getClass());
        return (T) mainRepository.save(model);
    }

    public <T extends EntityModel> T update(T model) {
        Optional<T> modelDb = mainRepository.findById(model.getId());
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        if (modelDb.isPresent() && model.getStatus()!="deleted"){
            modelDb.get().setName(model.getName());
            modelDb.get().setDescription(model.getDescription());
            modelDb.get().setDate_modificate(sqlDate);
            return (T) mainRepository.save(modelDb.get());
        }else if(modelDb.get().getStatus()!="deleted"){
            EntityModelService entityModelService = new EntityModelService(this.mainRepository);
            return (T) entityModelService.create(model);
        }
    return null;}
    public <T extends EntityModel> void delete(Long id) {
        Optional<T> model = mainRepository.findById(id);
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        model.get().setStatus("deleted");
        model.get().setDate_remove(sqlDate);
        mainRepository.save(model.get());
    }
}