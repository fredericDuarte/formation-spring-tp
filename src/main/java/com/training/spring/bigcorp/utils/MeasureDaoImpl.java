package com.training.spring.bigcorp.utils;

import com.training.spring.bigcorp.model.Measure;

import com.training.spring.bigcorp.repository.MeasureDao;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

@Repository
public class MeasureDaoImpl implements MeasureDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void persist(Measure measure) {

        em.persist(measure);
    }

    @Override
    public Measure findById(Long aLong) {
       return em.find(Measure.class, aLong);
    }

    @Override
    public List<Measure> findAll() {

        return em.createQuery("select c from Measure c inner join c.captor s",
                Measure.class).getResultList();
    }

    @Override
    public void delete(Measure id) {
        em.remove(id);

    }



}
