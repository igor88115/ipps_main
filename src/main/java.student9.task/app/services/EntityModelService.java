package app.services;

import app.models.EntityModel;
import app.repository.MainRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;


public class EntityModelService <S extends MainRepository> {

    protected S mainRepository;

    public EntityModelService(S mainRepository) {
        this.mainRepository = mainRepository;
    }

    public <T extends EntityModel> List<T> findAll() {
        return mainRepository.findByStatus("good");
    }

    public Optional findById(Long id) {
        return mainRepository.findById(id);
    }


    public <T extends EntityModel> T create(T model) {
        Date date = new Date();
        model.setDateCreate(date);
        model.setDateModificated(date);
        model.setStatus("good");
        System.out.println(model.getClass());
        return (T) this.mainRepository.save(model);
    }

    public <T extends EntityModel> T update(T model) {
        Optional<T> modelDb = this.mainRepository.findById(model.getId());
        Date date = new Date();
        if (modelDb.isPresent() && model.getStatus()!="deleted"){
            if (model.getName()!=null){modelDb.get().setName(model.getName());}
            if (model.getDescription()!=null){modelDb.get().setDescription(model.getDescription());}
            modelDb.get().setDateModificated(date);
            return (T) this.mainRepository.save(modelDb.get());
        }else if(modelDb.get().getStatus()!="deleted"){
            EntityModelService entityModelService = new EntityModelService(this.mainRepository);
            return (T) entityModelService.create(model);
        }
        return null;}
    public <T extends EntityModel> void delete(Long id) {
        Optional<T> model = mainRepository.findById(id);
        Date date = new Date();
        model.get().setStatus("deleted");
        model.get().setDateRemove(date);
        this.mainRepository.save(model.get());
    }
    public  <T extends EntityModel> void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=entity" + ".xlsx";
        response.setHeader(headerKey, headerValue);
        List<T> modelList = this.mainRepository.findByStatus("good");
        PoiServiceExcel poiService = new PoiServiceExcel();
        poiService.export(response, modelList);
    }

    public <T extends EntityModel> void exportToWord(HttpServletResponse response) throws IOException {
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=entity" + ".doc";
        response.setHeader(headerKey, headerValue);
        List<T> modelList = this.mainRepository.findByStatus("good");
        PoiServiceWord poiServiceWord = new PoiServiceWord();
        poiServiceWord.export(response, modelList);

    }

    public Page findPages(Pageable pageable) {
        return  this.mainRepository.findByStatus("good", pageable);
    }
}