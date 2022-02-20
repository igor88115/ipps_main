package app.controller;

import app.models.DTOModel;
import app.models.EntityModel;
import app.services.EntityModelService;
import app.services.*;
import app.util.Status;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.context.annotation.Bean;
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
        StringBuilder stringBuilder = new StringBuilder(query.length()+1);
        stringBuilder.append(query).append("%");
        Filter filter = entityManager.unwrap(Session.class).enableFilter("queryFilter");
        filter.setParameter("query", stringBuilder.toString());
        List<T> result = this.baseEntityService.findAll();
        entityManager.unwrap(Session.class).disableFilter("queryFilter");
        List<DTOModel> dtoModelList
                =  new ModelMapper().map(result, new TypeToken<List<DTOModel>>() {}.getType());
        return ResponseEntity.status(HttpStatus.OK).body(dtoModelList);
    }

//    public List<T> findAll(String name) {
//        Filter filter = entityManager.unwrap(Session.class).enableFilter("nameFilter");
//        filter.setParameter("name", name + "%");
//        List<T> result = this.mainRepository.findByStatus("good");
//        entityManager.unwrap(Session.class).disableFilter("nameFilter");
//        return result;
//    }


    @Override
    public ResponseEntity <DTOModel> getById(Optional<T> entity) {
        if (entity.isPresent()){
            DTOModel result = new ModelMapper().map(entity.get(), DTOModel.class);
            return ResponseEntity.status(HttpStatus.OK).body(result);}
        else return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity delete(Optional<T> entity) {
        if (entity.isPresent() && !Objects.equals(entity.get().getStatus(), Status.DELETED.status)){
            return ResponseEntity.status(HttpStatus.OK).body(this.baseEntityService.delete(entity));
        }else return new ResponseEntity(HttpStatus.NO_CONTENT);
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
    public ResponseEntity<Page> findPaginated(Pageable pageable) {
        Page page = this.baseEntityService.findPages(pageable);
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