package ru.dmitriev.spring_boot.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import ru.dmitriev.spring_boot.model.User;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    private EntityManagerFactory entityManagerFactory;

    @PersistenceUnit
    public void setEmf(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public List<User> getAllUsers() {
        EntityManager em = this.entityManagerFactory.createEntityManager();
        try {
            TypedQuery<User> query = em.createQuery("from User", User.class);
            return query.getResultStream().toList();
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public User getUserById(Long id) {
        EntityManager em = this.entityManagerFactory.createEntityManager();
        try {
            return em.find(User.class, id);
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public void addUser(User user) {
        EntityManager em = this.entityManagerFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public void updateUser(Long id, User newUser) {
        EntityManager em = this.entityManagerFactory.createEntityManager();
        try {
            User user = getUserById(id);
            em.detach(user);

            user.setFirstName(newUser.getFirstName());
            user.setLastName(newUser.getLastName());
            user.setEmail(newUser.getEmail());

            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }

    }

    @Override
    public void deleteUser(Long id) {
        EntityManager em = this.entityManagerFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            User user = em.find(User.class, id);
            em.remove(user);
            em.getTransaction().commit();
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }
}
