package pl.coderslab.repository;

import pl.coderslab.entity.Project;

import java.util.Collection;

public interface ProjectRepository {

    void add(Project project);
    void edit(Project project);
    Project findById(Long id);
    void removeById(Long id);
    Collection<Project> findAll();
}
