package app.services;

import app.models.DTOModel;
import app.models.EntityModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Mapper {

    public static <T extends EntityModel> DTOModel toDto(T entity) {
        long id = entity.getId();
        String name = entity.getName();
        String description = entity.getDescription();
        Date dateCreate = entity.getDateCreate();
        Date dateModificate = entity.getDateModificate();

        return new DTOModel(id, name, description, dateCreate, dateModificate);
    }

    public static <T extends EntityModel> List<DTOModel> listToDTO(List<T> tList){
        ArrayList<DTOModel> dtoModels = new ArrayList<>();
        for (T entity: tList){
            dtoModels.add(Mapper.toDto(entity));
        }
        return dtoModels;
    }
}