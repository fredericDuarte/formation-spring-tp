package com.training.springcore;

import com.training.springcore.service.CaptorService;
import com.training.springcore.service.CaptorServiceImpl;
import com.training.springcore.service.SiteService;
import com.training.springcore.service.SiteServiceImpl;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;

@ComponentScan
@Configuration
public class BigCorpApplicationConfig {


    public SiteService siteService() {
        return new SiteServiceImpl(captorService());
    }


    public CaptorService captorService() {
       // return new CaptorServiceImpl();

        return new CaptorServiceImpl(fixed, simu, real);
    }

    public FixedMeasureService fixed() {

        return new FixedMeasureService();
    }

    public RealMeasureService real() {

        return new RealMeasureService();
    }

    public SimulatedMeasureService simu() {

        return new SimulateMeasureService();
    }
}
