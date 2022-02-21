package app.repository;

import app.models.EntityModel;
import app.util.Status;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;


@NoRepositoryBean
@Qualifier("main")
public interface MainRepository <T extends EntityModel> extends JpaRepository<T, Long> {
    List<T> findAll();
    List<T> findByStatus(String status);
    Page<T> findByStatus(String status, Pageable pageable);
    @Override
    <S extends T> S saveAndFlush(S entity);

    @Override
    T getById(Long aLong);

    Optional<T> findByStatus(Status status);

    @Override
    default Optional<T> findById(Long aLong) {
        return findByStatus(Status.GOOD);
    }
}
