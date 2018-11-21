package pl.coderslab.repository.Impl;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.entity.User;
import pl.coderslab.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;
import java.util.List;

@Component
@Transactional
public class UserImpl implements UserRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void add(final User user) {
        em.persist(user);
    }
    @Override
    public void edit(final User user) {
        em.merge(user);
    }
    @Override
    public User findById(final Long id) {
        final User user = em.find(User.class, id);
        return user;
    }
    @Override
    public void removeById(final Long id) {
        final User user = em.find(User.class, id);
        if(user != null) {
            em.remove(user);
        }
    }

    @Override
    public Collection<User> findAll() {
        final Query query = em.createQuery("select u from User u");

        final List<User> users = query.getResultList();
        return users;
    }
}
