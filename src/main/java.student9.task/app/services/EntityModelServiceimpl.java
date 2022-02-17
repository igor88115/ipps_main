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
import java.util.Optional;


public class EntityModelServiceimpl <S extends MainRepository, T extends EntityModel> implements EntityModelService<S, T>{

    @PersistenceContext
    private EntityManager entityManager;

    protected S mainRepository;

    public EntityModelServiceimpl(S mainRepository) {
        this.mainRepository = mainRepository;
    }




    @Override
    public List<T> findAll(String name) {
        List<T> result;
        if (name != null) {
            Filter filter = entityManager.unwrap(Session.class).enableFilter("nameFilter");
            filter.setParameter("name", name + "%");
            result = this.mainRepository.findAll();
            entityManager.unwrap(Session.class).disableFilter("nameFilter");
        }else {
            result = this.mainRepository.findAll();
        }
        return result;
    }





    @Override
    public ResponseEntity<Optional> findById(long id) {
        if (mainRepository.findById(id).isPresent())
            return ResponseEntity.status(HttpStatus.FOUND).body(mainRepository.findById(id));
        else return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<T> create(EntityModel model) {
        Date date = new Date();
        model.setDateCreate(date);
        model.setDateModificate(date);
        model.setStatus("good");
        System.out.println(model.getClass());
        return ResponseEntity.status(HttpStatus.CREATED).body((T)this.mainRepository.save(model));
    }

    @Override
    public <T extends EntityModel> ResponseEntity<T> update(T model) {
        Optional<T> modelDb = this.mainRepository.findById(model.getId());
        Date date = new Date();
        if (modelDb.isPresent() && model.getStatus() != "deleted") {
            if (model.getName() != null) {
                modelDb.get().setName(model.getName());
            }
            if (model.getDescription() != null) {
                modelDb.get().setDescription(model.getDescription());
            }
            modelDb.get().setDateModificate(date);
            ResponseEntity<T> body = ResponseEntity.status(HttpStatus.OK).body((T) this.mainRepository.save(modelDb.get()));
            return body;
        } else return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity delete(long id){
        Optional<T> model = mainRepository.findById(id);
        if (model.isPresent()) {
            Date date = new Date();
            model.get().setStatus("deleted");
            model.get().setDateRemove(date);
            this.mainRepository.save(model.get());
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @Override
    public  <T extends EntityModel > ResponseEntity<?> exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=entity" + ".xlsx";
        response.setHeader(headerKey, headerValue);
        List<T> modelList = this.mainRepository.findByStatus("good");
        PoiServiceExcel poiService = new PoiServiceExcel();
        poiService.export(response, modelList);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    public <T extends EntityModel > ResponseEntity<?> exportToWord(HttpServletResponse response) throws IOException {
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=entity" + ".doc";
        response.setHeader(headerKey, headerValue);
        List<T> modelList = this.mainRepository.findByStatus("good");
        PoiServiceWord poiServiceWord = new PoiServiceWord();
        poiServiceWord.export(response, modelList);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Page> findPages(Pageable pageable){
        Page page = this.mainRepository.findByStatus("good", pageable);
        if (page.hasContent()) return ResponseEntity.status(HttpStatus.OK).body(page);
        else return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}