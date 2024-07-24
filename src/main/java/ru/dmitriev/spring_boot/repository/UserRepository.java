package ru.dmitriev.spring_boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dmitriev.spring_boot.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
