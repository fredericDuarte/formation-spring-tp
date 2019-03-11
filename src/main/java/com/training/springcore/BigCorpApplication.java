package com.training.springcore;

import com.training.spring.bigcorp.ObjectFactory;
import com.training.springcore.model.Captor;
import com.training.springcore.model.MeasureStep;
import com.training.springcore.model.PowerSource;
import com.training.springcore.service.SiteService;
import com.training.springcore.service.measure.FixedMeasureService;
import com.training.springcore.service.measure.RealMeasureService;
import com.training.springcore.service.measure.SimulatedMeasureService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.Instant;


public class BigCorpApplication {

    public static void main(String[] args) {
        BigCorpApplication application = new BigCorpApplication();
        application.run();
    }

    public void run() {

        ApplicationContext context = new AnnotationConfigApplicationContext(BigCorpApplicationConfig.class);
        System.out.println("Application startup");

        SiteService siteservice = context.getBean(SiteService.class);
        System.out.println(siteservice.findById("siteA"));
        SiteService siteservice2 = context.getBean(SiteService.class);
        System.out.println(siteservice2.findById("siteA"));


    }
}

