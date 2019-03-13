package com.training.spring.bigcorp.utils;

import com.training.spring.bigcorp.model.Captor;

import com.training.spring.bigcorp.repository.CaptorDao;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

@Repository
public class CaptorDaoImpl implements CaptorDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void persist(Captor captor) {
        em.persist(captor);

    }

    @Override
    public Captor findById(String id) {
        return em.find(Captor.class, id);
    }

    @Override
    public List<Captor> findAll() {

        return em.createQuery("select c from Captor c inner join c.site s",
                Captor.class).getResultList();
    }


    @Override
    public List<Captor> findBySiteId(String siteId) {
        return em.createQuery("select c from Captor c inner join c.site s where s.id =:siteId",
                Captor.class)
                .setParameter("siteId", siteId)
                .getResultList();
    }

    @Override
    public void delete(Captor captor) {

        em.remove(captor);
    }



}
