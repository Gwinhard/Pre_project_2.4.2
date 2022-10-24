package web.dao;

import org.springframework.stereotype.Repository;
import web.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class RoleDaoImpl implements RoleDao {



    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Role getRoleByName(String roleName) {
        return (Role) entityManager.createQuery("select r from Role r where r.role = :roleName")
                .setParameter("roleName", roleName).getSingleResult();
    }

    @Override
    public Role getRoleById(long id) {
        return (Role) entityManager.createQuery("select r from Role r where r.id = :id")
                .setParameter("id", id).getSingleResult();
    }

    @Override
    public Set<Role> getAllRoles() {
        List<Role> list = entityManager.createQuery("select r from Role r", Role.class)
                .getResultList();

        return new HashSet<>(list);
    }

}
