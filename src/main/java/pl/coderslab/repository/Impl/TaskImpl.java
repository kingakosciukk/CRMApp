package pl.coderslab.repository.Impl;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.entity.Task;
import pl.coderslab.repository.TaskRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
@Transactional
public class TaskImpl implements TaskRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void add(final Task task) {
        em.persist(task);
    }
    @Override
    public void edit(final Task task) {
        em.merge(task);
    }
    @Override
    public Task findByTopic(final String topic) {
        final Task task = em.find(Task.class, topic);
        return task;
    }
    @Override
    public void removeByTopic(final String topic) {
        final Task task = em.find(Task.class, topic);
        if(task != null) {
            em.remove(task);
        }
    }
}
