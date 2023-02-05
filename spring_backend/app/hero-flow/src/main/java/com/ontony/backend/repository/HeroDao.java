package com.ontony.backend.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ontony.backend.models.Hero;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class HeroDao {
    private final EntityManager em;

    public List<Hero> findHeroesByTerm(String term) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Hero> cq = cb.createQuery(Hero.class);

        Root<Hero> hero = cq.from(Hero.class);
        Predicate namePredicate = cb.like(hero.get("name"), "%" + term + "%");
        cq.where(namePredicate);

        TypedQuery<Hero> query = em.createQuery(cq);
        return query.getResultList();
    }

    public List<Hero> findHeroesByPagination(Integer size, Integer page) {
        Query query = em.createQuery("SELECT h FROM Hero h ORDER BY id DESC");
        query.setFirstResult((page - 1) * size);
        query.setMaxResults(size);
        return query.getResultList();
    }
}
