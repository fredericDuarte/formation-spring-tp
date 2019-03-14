package com.training.spring.bigcorp.utils;

import com.training.spring.bigcorp.model.Captor;
import com.training.spring.bigcorp.repository.CaptorCustomDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CaptorCustomDaoImpl implements CaptorCustomDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Captor> findBySiteId(String siteId) {

        return em.createQuery("select c from Captor c inner join c.site s where s.id= :siteId",
                Captor.class)
                .setParameter("siteId", siteId)
                .getResultList();


    }
}
