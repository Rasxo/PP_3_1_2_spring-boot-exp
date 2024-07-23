package ru.dmitriev.spring_boot.service;

import ru.dmitriev.spring_boot.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUserById(Long id);

    void addUser(User user);

    void updateUser(Long id, User user);

    void deleteUser(Long id);
}
