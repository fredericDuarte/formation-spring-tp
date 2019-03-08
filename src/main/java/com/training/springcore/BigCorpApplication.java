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
        /*  classic instance */
        //   SiteService siteService = new SiteServiceImpl();
        //   System.out.println(siteService.findById("siteA"));

        /* with IOC (TP1)  */
        //ObjectFactory fact = new ObjectFactory();
        //System.out.println(fact.createSiteService().findById("siteA"));

        /*  with param beans.xml (TP2) */
        SiteService siteservice = context.getBean(SiteService.class);
        System.out.println(siteservice.findById("siteA"));
        SiteService siteservice2 = context.getBean(SiteService.class);
        System.out.println(siteservice2.findById("siteA"));

        FixedMeasureService fixedService = context.getBean(FixedMeasureService.class);
        System.out.println(fixedService.toString());

  /*      Captor capt = new Captor("siteC", PowerSource.FIXED);
        Instant start = Instant.parse("2018-09-01T22:00:00Z");
        Instant end = start.plusSeconds(60 * 60 * 24);
        System.out.println(fixedService.readMeasures(capt, start, end, MeasureStep.FIFTEEN_MINUTES));

        SimulatedMeasureService simuService = context.getBean(SimulatedMeasureService.class);
        Captor capt2 = new Captor("siteC", PowerSource.SIMULATED);
        Instant start2= Instant.parse("2018-09-01T22:00:00Z");
        Instant end2 = start.plusSeconds(60 * 60 * 24);
        System.out.println(simuService.readMeasures(capt2, start2, end2, MeasureStep.FIFTEEN_MINUTES));

        RealMeasureService realService = context.getBean(RealMeasureService.class);
        Captor capt3 = new Captor("siteC", PowerSource.REAL);
        Instant start3 = Instant.parse("2018-09-01T22:00:00Z");
        Instant end3 = start.plusSeconds(60 * 60 * 24);
        System.out.println(realService .readMeasures(capt3, start3, end3, MeasureStep.FIFTEEN_MINUTES));*/

    }
}

