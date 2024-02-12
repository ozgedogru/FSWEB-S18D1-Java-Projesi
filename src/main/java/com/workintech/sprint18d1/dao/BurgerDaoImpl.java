package com.workintech.sprint18d1.dao;

import com.workintech.sprint18d1.entity.Burger;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
@Transactional
public class BurgerDaoImpl implements BurgerDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Burger burger) {
        entityManager.persist(burger);
    }

    @Override
    public Burger findById(Integer id) {
        return entityManager.find(Burger.class, id);
    }

    @Override
    public List<Burger> findAll() {
        TypedQuery<Burger> query = entityManager.createQuery("SELECT b FROM Burger b", Burger.class);
        return query.getResultList();
    }

    @Override
    public List<Burger> findByPrice(double price) {
        TypedQuery<Burger> query = entityManager.createQuery("SELECT b FROM Burger b WHERE b.price > :price ORDER BY b.price DESC", Burger.class);
        query.setParameter("price", price);
        return query.getResultList();
    }

    @Override
    public List<Burger> findByBreadType(String breadType) {
        TypedQuery<Burger> query = entityManager.createQuery("SELECT b FROM Burger b WHERE b.breadType = :breadType ORDER BY b.name ASC", Burger.class);
        query.setParameter("breadType", breadType);
        return query.getResultList();
    }

    @Override
    public List<Burger> findByContent(String content) {
        TypedQuery<Burger> query = entityManager.createQuery("SELECT b FROM Burger b WHERE b.contents LIKE :content", Burger.class);
        query.setParameter("content", "%" + content + "%");
        return query.getResultList();
    }

    @Override
    public void update(Burger burger) {
        entityManager.merge(burger);
    }

    @Override
    public void remove(Integer id) {
        Burger burger = entityManager.find(Burger.class, id);
        if (burger != null) {
            entityManager.remove(burger);
        }
    }
}
