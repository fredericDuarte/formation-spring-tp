package com.training.springcore.service;

import com.training.springcore.model.Captor;
import com.training.springcore.model.Measure;
import com.training.springcore.model.PowerSource;
import com.training.springcore.service.measure.FixedMeasureService;
import com.training.springcore.service.measure.MeasureService;
import com.training.springcore.service.measure.RealMeasureService;
import com.training.springcore.service.measure.SimulatedMeasureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
public class CaptorServiceImpl implements CaptorService {


    private MeasureService fixed;
    private MeasureService simu;
    private MeasureService real;

    public CaptorServiceImpl() {
    }

    @Autowired
    public CaptorServiceImpl(@Qualifier("fixedMeasure") MeasureService fixed,
                             @Qualifier("simuMeasure") MeasureService simu,
                             @Qualifier("realMeasure") MeasureService real) {
        this.fixed = fixed;
        this.simu = simu;
        this.real = real;
    }

    @Override
    public Set<Captor> findBySite(String siteId) {
        Set<Captor> captors = new HashSet<>();
        if (siteId == null) {
            return captors;
        }
        captors.add(new Captor("Capteur A", PowerSource.FIXED));
        return captors;
    }

}
