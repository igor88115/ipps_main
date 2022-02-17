package app.services;

import app.models.DTOModel;
import app.models.EntityModel;
import org.springframework.stereotype.Component;

import java.util.Date;


public class Mapper {

    public static <T extends EntityModel> DTOModel toDto(T entity) {
        long id = entity.getId();
        String name = entity.getName();
        String description = entity.getDescription();
        Date dateCreate = entity.getDateCreate();
        Date dateModificate = entity.getDateModificate();

        return new DTOModel(id, name, description, dateCreate, dateModificate);
    }
}