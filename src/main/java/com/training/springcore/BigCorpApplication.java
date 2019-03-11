package com.training.springcore;

import com.training.springcore.config.BigCorpApplicationConfig;
import com.training.springcore.model.ApplicationInfo;
import com.training.springcore.service.SiteService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
public class BigCorpApplication {

    public static void main(String[] args) {
        BigCorpApplication application = new BigCorpApplication();
        application.run();
    }

    public void run() {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // par la variable environnement ( voir slide )
        // deb - ou changer manuellement  de profil
        context.getEnvironment().setActiveProfiles("prod");
        context.register(BigCorpApplicationConfig.class);
        context.refresh();
        // fin
        System.out.println("Application startup");

        SiteService siteservice = context.getBean(SiteService.class);
        System.out.println(siteservice.findById("siteA"));

        SiteService siteservice2 = context.getBean(SiteService.class);
        System.out.println(siteservice2.findById("siteA"));

       ApplicationInfo applicationInfo = context.getBean(ApplicationInfo.class);
        System.out.println("==========================================================");
        System.out.println("Application [" + applicationInfo.getName() + "] - version : "
                + applicationInfo.getVersion());
        System.out.println("plus d'informations sur " + applicationInfo.getWebSiteUrl());
        System.out.println("==========================================================");



    }
}

