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

   // @Bean
    // ** nouveau instance à chaque appel -->
   // @Scope("prototype")
    // ou bien
    //@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
   // ** instance une seule fois
   //@Lazy
    public SiteService siteService() {
        return new SiteServiceImpl(captorService());
    }

    //@Bean
    public CaptorService captorService() {
        return new CaptorServiceImpl();
    }

}
