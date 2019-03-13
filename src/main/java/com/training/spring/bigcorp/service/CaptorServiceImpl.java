package com.training.spring.bigcorp.service;

import com.training.spring.bigcorp.model.Captor;
import com.training.spring.bigcorp.model.PowerSource;
import com.training.spring.bigcorp.model.Site;
import com.training.spring.bigcorp.repository.CaptorDao;
import com.training.spring.bigcorp.service.measure.MeasureService;
import com.training.springcore.aspect.Monitored;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class CaptorServiceImpl implements CaptorService {


    private MeasureService fixed;
    private MeasureService simu;
    private MeasureService real;


    private CaptorDao captorDao;

    public CaptorServiceImpl() {
    }

    @Autowired
    public CaptorServiceImpl(@Qualifier("fixedMeasure") MeasureService fixed,
                             @Qualifier("simuMeasure") MeasureService simu,
                             @Qualifier("realMeasure") MeasureService real, CaptorDao captorDao ) {
        this.fixed = fixed;
        this.simu = simu;
        this.real = real;
        this.captorDao = captorDao;
    }

    @Monitored
    @Override
    public Set<Captor> findBySite(String siteId) {
        return captorDao.findBySiteId(siteId).stream().collect(Collectors.toSet());
    }

    @Override
    public String toString() {
        return "CaptorServiceImpl{" +
                "fixed=" + fixed +
                ", simu=" + simu +
                ", real=" + real +
                '}';
    }
}
