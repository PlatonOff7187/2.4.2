package web.dao;

import org.springframework.stereotype.Repository;
import web.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager entityManager;

    @Override
    public Role getRoleByName(String userName) {
        return   entityManager.createQuery("SELECT r FROM Role r WHERE r.userName = :name", Role.class)
                .setParameter("name", userName)
                .getSingleResult();

    }

    @Override
    public void saveRole(Role role) {
      entityManager.persist(role);
    }

    @Override
    public List<Role> getRoles() {
        return entityManager.createQuery("SELECT r FROM Role r", Role.class).getResultList();
    }

    @Override
    public Role getRoleById(int id) {
        TypedQuery<Role> q = entityManager.createQuery(
                "SELECT u FROM Role u WHERE u.id = :id", Role.class
        );
        q.setParameter("id", id);
        return q.getResultList().stream().findAny().orElse(null);
    }
}