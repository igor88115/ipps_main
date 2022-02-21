package app.repository;

import app.models.EntityModel;
import app.models.Status;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;


@NoRepositoryBean
@Qualifier("main")
public interface MainRepository <T extends EntityModel> extends JpaRepository<T, Long> {
    List<T> findAll();
    List<T> findAllByStatus(Status status);
    Page<T> findByStatus(Status status, Pageable pageable);
    @Override
    <S extends T> S saveAndFlush(S entity);

    @Override
    T getById(Long aLong);

    Optional<T> findByStatus(Status status);

//    @Override
//    default <S extends T> List<S> findAll(Example<S> example){
//        return (List<S>) findAllByStatus(Status.GOOD);
//    };


//    @Override
//    default <S extends T> Optional<S> findOne(Example<S> example){
//        return (Optional<S>) findByStatus(Status.GOOD);
//    };
    Optional<T> findDistinctByStatus(Status status);
    @Override
    default Optional<T> findById(Long aLong) {
        if (findById(aLong).get().getStatus() == Status.DELETED) return Optional.empty();
        return findDistinctByStatus(Status.GOOD);
    }
}
