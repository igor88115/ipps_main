package app.services;


import app.models.EntityModel;
import app.repository.MainRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface EntityModelService <S extends MainRepository, T extends EntityModel>{

    List<T> findAll();

    T create(T model);


    T update(T model);

    T delete(T entity);

    void exportToExcel(HttpServletResponse response) throws IOException;

    void exportToWord(HttpServletResponse response) throws IOException;

    Page<T> findPages(Pageable pageable);
}