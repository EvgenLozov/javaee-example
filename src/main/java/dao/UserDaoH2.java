package dao;

import dao.UserDao;
import entity.Status;
import entity.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class UserDaoH2 implements UserDao {

    @PersistenceContext(unitName = "prod")
    EntityManager entityManager;

    @Override
    public User get(Long userId) {
        return entityManager.find(User.class, userId);
    }

    @Override
    public List<User> list() {
        return entityManager.createQuery("select u from User u").getResultList();
    }

    @Override
    public User create(User newUser) {
        entityManager.persist(newUser);
        return newUser;
    }

    @Override
    public void edit(User editedUser) {
        entityManager.merge(editedUser);
    }

    @Override
    public void delete(User user) {
        entityManager.remove(user);
    }

    @Override
    public long countWithStatus(Status status) {
        TypedQuery<Long> query = entityManager.createQuery("select count(u.id) from User u where u.status = :status", Long.class);
        return query.setParameter("status", status).getSingleResult();
    }
}
