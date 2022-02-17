package app.services;


import app.models.EntityModel;
import app.repository.MainRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface EntityModelService <S extends MainRepository, T extends EntityModel>{
    List<T> findAll(String name);

    ResponseEntity<Optional> findById(long id);

    ResponseEntity<T> create(EntityModel model);

    <T2 extends EntityModel> ResponseEntity<T2> update(T2 model);

    ResponseEntity delete(long id);

    <T2 extends EntityModel > ResponseEntity<?> exportToExcel(HttpServletResponse response) throws IOException;

    <T2 extends EntityModel > ResponseEntity<?> exportToWord(HttpServletResponse response) throws IOException;

    ResponseEntity<Page> findPages(Pageable pageable);
}
