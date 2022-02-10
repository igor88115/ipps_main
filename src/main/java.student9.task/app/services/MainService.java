package app.services;

import app.models.EntityModel;

import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MainService  <T extends EntityModel> {
    public T create(T entity){
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        entity.setDate_create(sqlDate);
        entity.setDate_modificate(sqlDate);
        entity.setStatus("good");
        return entity;
    }


    public T delete(T entity){
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        entity.setStatus("deleted");
        entity.setDate_remove(sqlDate);
        return entity;
    }
    public T update(T entity){
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        entity.setDate_modificate(sqlDate);
        return entity;
    }
        public T paginate(T entity){
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        entity.setDate_modificate(sqlDate);
        return entity;
    }

}
