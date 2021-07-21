package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{
    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager entityManager;


    @Override
    public User getUserByName(String name) {
        return entityManager.createQuery("SELECT u FROM User u WHERE u.name =:j_username", User.class).setParameter("j_username", name).getSingleResult();
    }

    @Override
    public List<User> index() {
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    @Override
    public User show(int id) {
        TypedQuery<User> typeQ = entityManager.createQuery(
                "SELECT u FROM User u WHERE u.id = :id",
                User.class
        );
        typeQ.setParameter("id", id);
        return typeQ.getResultList().stream().findAny().orElse(null);
    }

    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public User update(User user) {

       return entityManager.merge(user);


    }

    @Override
    public void delete(int id) {
        entityManager.remove(show(id));
    }
}
