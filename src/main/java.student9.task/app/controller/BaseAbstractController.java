package app.controller;

import app.models.DTOModel;
import app.models.EntityModel;
import app.services.EntityModelService;
import app.services.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class BaseAbstractController<T extends EntityModel, S extends EntityModelService> implements BaseController<T> {

    S baseEntityService;
    @PersistenceContext
    private EntityManager entityManager;

    public BaseAbstractController(S baseEntityService) {
        this.baseEntityService = baseEntityService;
    }

    @Override
    public ResponseEntity<List<DTOModel>> list(String query) {
        List<T> result = this.baseEntityService.findAll(query);
        if(result.isEmpty())return new ResponseEntity(HttpStatus.NO_CONTENT);
        List<DTOModel> dtoModelList = new ArrayList<>(result.size());
        for(T entity:result){
            dtoModelList.add(Mapper.toDto(entity));
        }


        return ResponseEntity.status(HttpStatus.OK).body(dtoModelList);
    }

    @Override
    public ResponseEntity <DTOModel> getById(Optional<T> entity) {
        if (entity.isPresent() && !Objects.equals(entity.get().getStatus(), "deleted")){
            return ResponseEntity.status(HttpStatus.OK).body(Mapper.toDto(entity.get()));}
        else return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity delete(Optional<T> entity) {
        if (entity.isPresent() && !Objects.equals(entity.get().getStatus(), "deleted")){
            return ResponseEntity.status(HttpStatus.OK).body(this.baseEntityService.delete(entity));
        }else return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity exportToExcel(HttpServletResponse response) throws IOException {
        this.baseEntityService.exportToExcel(response);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    public ResponseEntity exportToWord(HttpServletResponse response) throws IOException {
        this.baseEntityService.exportToWord(response);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Page<T>> findPaginated(Pageable pageable) {
        Page<T> page = this.baseEntityService.findPages(pageable);
        if (page.hasContent()) return ResponseEntity.status(HttpStatus.OK).body(page);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<DTOModel> create(@RequestBody T model) {
        T result = (T) this.baseEntityService.create(model);
        return ResponseEntity.status(HttpStatus.OK).body(Mapper.toDto(result));
    }

    @Override
    public ResponseEntity<DTOModel> update(@RequestBody T model) {
        T result = (T) baseEntityService.update(model);
        if (result == null){return new ResponseEntity(HttpStatus.NO_CONTENT);}
        return ResponseEntity.status(HttpStatus.OK).body(Mapper.toDto(result));
    }
}