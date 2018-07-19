package dao;

import entity.Project;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ProjectDaoH2 implements ProjectDao {

    @PersistenceContext(unitName = "prod")
    EntityManager entityManager;

    @Override
    public Project get(Long projectId) {
        return entityManager.find(Project.class, projectId);
    }

    @Override
    public List<Project> list() {
        return entityManager.createQuery("select p from Project p").getResultList();
    }

    @Override
    public Project create(Project newProject) {
        entityManager.persist(newProject);
        return newProject;
    }

    @Override
    public void edit(Project editedProject) {
        entityManager.merge(editedProject);
    }

    @Override
    public void delete(Project project) {
        entityManager.remove(project);
    }

    @Override
    public long countActive() {
        return entityManager.createQuery("select count(p.id) " +
                "from Project p where p.startDate is not null and p.endDate is null", Long.class).getSingleResult();
    }

    @Override
    public long countFinished() {
        return entityManager.createQuery("select count(p.id) " +
                "from Project p where p.startDate is not null and p.endDate is not null", Long.class).getSingleResult();

    }
}
