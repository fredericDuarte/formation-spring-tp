package com.training.springcore.config;

import com.training.springcore.model.ApplicationInfo;
import com.training.springcore.service.CaptorService;
import com.training.springcore.service.CaptorServiceImpl;
import com.training.springcore.service.SiteService;
import com.training.springcore.service.SiteServiceImpl;
import com.training.springcore.service.measure.FixedMeasureService;
import com.training.springcore.service.measure.RealMeasureService;
import com.training.springcore.service.measure.SimulatedMeasureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

import java.util.Set;

@Profile("prod")
@Configuration
@PropertySource("classpath:application-prod.properties")
public class BigCorpApplicationProdConfig {


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
