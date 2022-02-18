package app.services;

import app.models.EntityModel;
import app.repository.MainRepository;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


public class EntityModelServiceimpl<S extends MainRepository, T extends EntityModel> implements EntityModelService<S, T> {



    protected S mainRepository;

    public EntityModelServiceimpl(S mainRepository) {
        this.mainRepository = mainRepository;
    }

    @Override
    public List<T> findAll(String name) {
        Filter filter = entityManager.unwrap(Session.class).enableFilter("nameFilter");
        filter.setParameter("name", name + "%");
        List<T> result = this.mainRepository.findByStatus("good");
        entityManager.unwrap(Session.class).disableFilter("nameFilter");
        return result;
    }

//    @Override
//    public ResponseEntity<Optional> findById(long id) {
//        if (mainRepository.findById(id).isPresent())
//            return ResponseEntity.status(HttpStatus.OK).body(mainRepository.findById(id));
//        else return new ResponseEntity(HttpStatus.NO_CONTENT);
//    }

    @Override
    public T create(EntityModel model) {
        Date date = new Date();
        model.setDateCreate(date);
        model.setDateModificate(date);
        model.setStatus("good");
        return (T) this.mainRepository.save(model);
    }

    @Override
    public T update(T model) {
        Optional<T> modelDb = this.mainRepository.findById(model.getId());
        Date date = new Date();
        if (modelDb.isPresent() && !Objects.equals(modelDb.get().getStatus(), "deleted")) {
            if (model.getName() != null) {
                modelDb.get().setName(model.getName());
            }
            modelDb.get().setDateModificate(date);
            T saved = (T) this.mainRepository.save(modelDb.get());
            return saved;
        } else return null;
    }

    @Override
    public T delete(Optional<T> entity) {
        Date date = new Date();
        entity.get().setStatus("deleted");
        entity.get().setDateRemove(date);
        return (T) this.mainRepository.save(entity.get());
    }

    @Override
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Excel" + ".xlsx";
        response.setHeader(headerKey, headerValue);
        List<T> modelList = this.mainRepository.findByStatus("good");
        PoiServiceExcel poiService = new PoiServiceExcel();
        poiService.export(response, modelList);
    }

    @Override
    public <T extends EntityModel> ResponseEntity<?> exportToWord(HttpServletResponse response) throws IOException {
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Word" + ".doc";
        response.setHeader(headerKey, headerValue);
        List<T> modelList = this.mainRepository.findByStatus("good");
        PoiServiceWord poiServiceWord = new PoiServiceWord();
        poiServiceWord.export(response, modelList);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    public Page findPages(Pageable pageable) {
        return this.mainRepository.findPagesByStatus("good", pageable);
    }
}