package com.training.spring.bigcorp;

import com.training.spring.bigcorp.model.ApplicationInfo;
import com.training.spring.bigcorp.service.SiteService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class BigcorpApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(BigcorpApplication.class,
				args);
		ApplicationInfo applicationInfo = context.getBean(ApplicationInfo.class);

		System.out.println("Application startup");

		SiteService siteservice = context.getBean(SiteService.class);
		System.out.println(siteservice.findById("siteA"));

		System.out.println("==========================================================");
		System.out.println("Application [" + applicationInfo.getName() + "] - version : "
				+ applicationInfo.getVersion());
		System.out.println("plus d'informations sur " + applicationInfo.getWebSiteUrl());
		System.out.println("==========================================================");
	}
}
