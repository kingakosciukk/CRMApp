package pl.coderslab.repository;

import pl.coderslab.entity.User;

import java.util.Collection;

public interface UserRepository {

    void add(User user);
    void edit(User user);
    User findById(Long id);
    void removeById(Long id);
    Collection <User> findAll();


}
