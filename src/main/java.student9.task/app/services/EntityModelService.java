package app.services;

import app.models.EntityModel;
import app.repository.MainRepository;

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


    public <T extends EntityModel> T create (T model) {
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        model.setDate_create(sqlDate);
        model.setDate_modificate(sqlDate);
        model.setStatus("good");
//        System.out.println(mainRepository.save(model));
        return (T) mainRepository.save(model);
    }
}
