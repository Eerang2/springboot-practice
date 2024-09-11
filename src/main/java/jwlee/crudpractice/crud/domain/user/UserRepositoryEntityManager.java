package jwlee.crudpractice.crud.domain.user;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;


@Repository
@RequiredArgsConstructor
public class UserRepositoryEntityManager {

    private final EntityManager em;

    public User findById(Long id) {
        return em.find(User.class, id);
    }

    public void save(User user) {
        em.persist(user);
        em.flush();
    }
}
