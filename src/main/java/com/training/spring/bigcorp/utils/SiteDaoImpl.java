package com.training.spring.bigcorp.utils;


import com.training.spring.bigcorp.model.Site;
import com.training.spring.bigcorp.repository.SiteDao;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

@Repository
public class SiteDaoImpl implements SiteDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void persist(Site site) {
        em.persist(site);
    }

    @Override
    public Site findById(String s) {
        return em.find(Site.class, s);
    }

    @Override
    public List<Site> findAll() {
        return em.createQuery("select c from Site c",
                Site.class).getResultList();
    }

    @Override
    public void delete(Site site) {

        em.remove(site);

    }

}
