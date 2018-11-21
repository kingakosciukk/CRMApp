package pl.coderslab.repository;


import pl.coderslab.entity.Task;

public interface TaskRepository {

    void add(Task task);
    void edit(Task task);
    Task findByTopic(String topic);
    void removeByTopic(String topic);
}
