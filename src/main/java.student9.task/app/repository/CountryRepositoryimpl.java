//package app.repository;
//
//import app.models.Country;
//import org.hibernate.Session;
//import org.hibernate.annotations.Filter;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//
//public class CountryRepositoryimpl implements CountryRepositoryCustom{
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @Autowired
//    private CountryRepository countryRepository;
//
//    @Override
//    public Iterable<Country> findCommentWithEmail(String email) {
//        Filter filter = (Filter)entityManager.unwrap(Session.class).enableFilter("filterByEmail");
//        filter.setParameter("email", "arun.menon");
//        Iterable<Country> iterable = commentRepository.findAll();
//        entityManager.unwrap(Session.class).disableFilter("filterByEmail");
//        return iterable;
//    }
//
//
//
//}
