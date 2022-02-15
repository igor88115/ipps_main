package app.services;


import app.models.EntityModel;
import app.repository.MainRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public interface EntityModelService <S extends MainRepository, T extends EntityModel>{
    ResponseEntity findAll(String name);

    ResponseEntity<Optional> findById(Long id);

    ResponseEntity create(EntityModel model);

    <T extends EntityModel> ResponseEntity<T> update(T model);

    ResponseEntity delete(Long id);

    <T2 extends EntityModel > ResponseEntity<?> exportToExcel(HttpServletResponse response) throws IOException;

    <T2 extends EntityModel > ResponseEntity<?> exportToWord(HttpServletResponse response) throws IOException;

    ResponseEntity<Page> findPages (Pageable pageable);
}
