package app.controller;

import app.dto.DTOModelView;
import app.models.EntityModel;
import app.services.EntityModelService;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class BaseAbstractController<T extends EntityModel, S extends EntityModelService> implements BaseController<T> {

    S baseEntityService;
    @PersistenceContext
    private EntityManager entityManager;

    public BaseAbstractController(S baseEntityService) {
        this.baseEntityService = baseEntityService;
    }

    @Override
    public ResponseEntity<List<DTOModelView>> list(String query) {
        StringBuilder stringBuilder = new StringBuilder(query);
        stringBuilder.append("%");
        Filter filter = entityManager.unwrap(Session.class).enableFilter("queryFilter");
        filter.setParameter("query", stringBuilder.toString());
        List<T> result = this.baseEntityService.findAll();
        entityManager.unwrap(Session.class).disableFilter("queryFilter");
        List<DTOModelView> dtoModelViewList
                =  new ModelMapper().map(result, new TypeToken<List<DTOModelView>>() {}.getType());
        return ResponseEntity.status(HttpStatus.OK).body(dtoModelViewList);
    }

    @Override
    public ResponseEntity <DTOModelView> getById(Optional<T> entity) {
        if (entity.isPresent()){
            DTOModelView result = new ModelMapper().map(entity.get(), DTOModelView.class);
            return ResponseEntity.status(HttpStatus.OK).body(result);}
        else return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity delete(Optional<T> entity) {
        if (entity.isPresent()){
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
    public ResponseEntity<Page<DTOModelView>> findPaginated(Pageable pageable) {
        Page<T> page = this.baseEntityService.findPages(pageable);
        if (!page.hasContent()) return new ResponseEntity(HttpStatus.NO_CONTENT);
        List<DTOModelView> dtoModelViewList
                =  new ModelMapper().map(page.getContent(), new TypeToken<List<DTOModelView>>() {}.getType());
        Page<DTOModelView> dtoModels = new PageImpl<>(dtoModelViewList, page.getPageable(), dtoModelViewList.size());
        return ResponseEntity.status(HttpStatus.OK).body(dtoModels);
    }

    @Override
    public ResponseEntity<DTOModelView> create(@RequestBody T model) {
        T result = (T) this.baseEntityService.create(model);
        return ResponseEntity.status(HttpStatus.OK).body(new ModelMapper().map(result, DTOModelView.class));
    }

    @Override
    public ResponseEntity<DTOModelView> update(@RequestBody T model) {
        T result = (T) this.baseEntityService.update(model);
        if (result == null){return new ResponseEntity(HttpStatus.NO_CONTENT);}
        return ResponseEntity.status(HttpStatus.OK).body(new ModelMapper().map(result, DTOModelView.class));
    }
}