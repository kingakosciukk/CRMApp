package pl.coderslab.repository.Impl;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.entity.Project;
import pl.coderslab.repository.ProjectRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;
import java.util.List;

@Component
@Transactional
public class ProjectImpl implements ProjectRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void add(final Project project) {
        em.persist(project);
    }
    @Override
    public void edit(final Project project) {
        em.merge(project);
    }
    @Override
    public Project findById(final Long id) {
        final Project project = em.find(Project.class, id);
        return project;
    }
    @Override
    public void removeById(final Long id) {
        final Project project = em.find(Project.class, id);
        if(project != null) {
            em.remove(project);
        }
    }
    @Override
    public Collection<Project> findAll() {

        final Query query = em.createQuery("select p from Project p");

        final List<Project> projects = query.getResultList();
        return projects;
    }
}