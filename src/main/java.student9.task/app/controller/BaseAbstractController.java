package app.controller;

import app.models.EntityModel;
import app.repository.MainRepository;
import app.services.EntityModelService;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class BaseAbstractController<T extends EntityModel, S extends EntityModelService> implements BaseController<T> {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public Iterable<T> findCommentWithEmail(String name) {
        Filter filter = entityManager.unwrap(Session.class).enableFilter("nameFilter");
        filter.setParameter("name", name+"%");
        Iterable<T> iterable = this.baseEntityService.findAllFilter();
        entityManager.unwrap(Session.class).disableFilter("nameFilter");
        return iterable;
    }

    S baseEntityService;

    public BaseAbstractController(S baseEntityService) {
        this.baseEntityService = baseEntityService;
    }

    @Override
    public ResponseEntity<List<T>> list(String name) {
        return this.baseEntityService.findAll(name);
    }


    @Override
    public ResponseEntity<Optional<T>> getById(Long id) {
        return this.baseEntityService.findById(id);
    }

    @Override
    public ResponseEntity delete(Long id) {
        return this.baseEntityService.delete(id);
    }


    @Override
    public ResponseEntity<?> exportToExcel(HttpServletResponse response) throws IOException {
        return this.baseEntityService.exportToExcel(response);
    }

    @Override
    public ResponseEntity<?> exportToWord(HttpServletResponse response) throws IOException {
        return this.baseEntityService.exportToWord(response);
    }

    @Override
    public ResponseEntity<Page<T>> findPaginated(Pageable pageable) {
        return this.baseEntityService.findPages(pageable);
    }

    @Override
    public ResponseEntity<T> create(@RequestBody T model) {
        return baseEntityService.create(model);
    }

    @Override
    public ResponseEntity<T> update(@RequestBody T model) {
        return baseEntityService.update(model);
    }

}
