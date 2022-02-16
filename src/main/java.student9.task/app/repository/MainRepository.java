package app.repository;

import app.models.EntityModel;
import app.models.Region;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;


@NoRepositoryBean
@Qualifier("main")
public interface MainRepository <T extends EntityModel> extends JpaRepository<T, Long>, PagingAndSortingRepository<T, Long> {
    List<T> findAll();
    List<T> findByStatus(String status);
    Page<T> findByStatus(String status, Pageable pageable);
    List<T> findByNameStartsWith(String name);
    Optional<T> findByIdAndStatus(long id, String good);

    @Override
    <S extends T> S saveAndFlush(S entity);
}
