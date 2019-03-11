package com.training.spring.bigcorp.config;


import com.training.spring.bigcorp.model.ApplicationInfo;
import com.training.spring.bigcorp.service.CaptorService;
import com.training.spring.bigcorp.service.CaptorServiceImpl;
import com.training.spring.bigcorp.service.SiteService;
import com.training.spring.bigcorp.service.SiteServiceImpl;
import com.training.spring.bigcorp.service.measure.FixedMeasureService;
import com.training.spring.bigcorp.service.measure.RealMeasureService;
import com.training.spring.bigcorp.service.measure.SimulatedMeasureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

import java.util.Set;


@Configuration
public class BigCorpApplicationConfig {



    @Autowired
    private Environment environment;

    @Bean
    public ApplicationInfo applicationInfo() {
        String name = environment.getRequiredProperty("bigcorp.name");
        Integer version = environment.getRequiredProperty("bigcorp.version", Integer.class);
        Set<String> emails = environment.getRequiredProperty("bigcorp.emails", Set.class);
        String webSiteUrl = environment.getRequiredProperty("bigcorp.webSiteUrl");

        return new ApplicationInfo(name, version, emails, webSiteUrl);
    }

    public SiteService siteService() {
        return new SiteServiceImpl(captorService());
    }


    public CaptorService captorService() {
        return new CaptorServiceImpl(fixed(), simu(), real());
    }

    public FixedMeasureService fixed() {

        return new FixedMeasureService();
    }

    public RealMeasureService real() {

        return new RealMeasureService();
    }

    public SimulatedMeasureService simu() {

        return new SimulatedMeasureService();
    }

}
