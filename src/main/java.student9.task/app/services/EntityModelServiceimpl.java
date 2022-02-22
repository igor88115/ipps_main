package app.services;

import app.models.EntityModel;
import app.repository.MainRepository;
import app.models.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;


public class EntityModelServiceimpl<S extends MainRepository, T extends EntityModel> implements EntityModelService<S, T> {

    protected S mainRepository;

    public EntityModelServiceimpl(S mainRepository) {
        this.mainRepository = mainRepository;
    }

    @Override
    public List<T> findAll() {
        return this.mainRepository.findAllByStatus(Status.GOOD);
    }

    @Override
    public T create(T model) {
        Date date = new Date();
        model.setDateCreate(date);
        model.setDateModificate(date);
        model.setStatus(Status.GOOD);
        return (T) this.mainRepository.save(model);
    }

    @Override
    public T update(T model) {
        Optional<T> modelDb = this.mainRepository.findById(model.getId());
        Date date = new Date();
        if (modelDb.isPresent()) {
            if (model.getName() != null) {
                modelDb.get().setName(model.getName());
            }
            modelDb.get().setDateModificate(date);
            T saved = (T) this.mainRepository.save(modelDb.get());
            return saved;
        } else return null;
    }

    @Override
    public T delete(T entity) {
        Date date = new Date();
        entity.setStatus(Status.DELETED);
        entity.setDateRemove(date);
        return (T) this.mainRepository.save(entity);
    }

    @Override
    public void exportToExcel(HttpServletResponse response) throws IOException {
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Excel.xlsx";
        response.setHeader(headerKey, headerValue);
        List<T> modelList = this.mainRepository.findAllByStatus(Status.GOOD);
        PoiServiceExcel poiService = new PoiServiceExcel();
        poiService.export(response, modelList);
    }

    @Override
    public void exportToWord(HttpServletResponse response) throws IOException {
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Word.doc";
        response.setHeader(headerKey, headerValue);
        List<T> modelList = this.mainRepository.findAllByStatus(Status.GOOD);
        PoiServiceWord poiServiceWord = new PoiServiceWord();
        poiServiceWord.export(response, modelList);
    }

    @Override
    public Page<T> findPages(Pageable pageable) {
        return this.mainRepository.findByStatus(Status.GOOD, pageable);
    }
}